#!/usr/bin/env perl
#

use strict;
use warnings;

use JSON::XS;
use Log::Log4perl;
use File::Spec;
use DateTime;
use Math::Round;
use URI::Split qw( 
	uri_split
	uri_join
);

# Atlas Common subroutines.
use Common qw( 
	connect_atlas
	get_atlas_site_config
	get_atlas_contrast_details
	get_log_file_header
	get_log_file_name
);
use AtlasConfig::Reader qw(
	parseAtlasConfig
);

$| = 1;

my $logger_config = q(
	log4perl.rootlogger					= INFO, LOG1, SCREEN
	log4perl.appender.SCREEN			= Log::Log4perl::Appender::Screen
	log4perl.appender.SCREEN.stderr		= 0
	log4perl.appender.SCREEN.layout		= Log::Log4perl::Layout::PatternLayout
	log4perl.appender.SCREEN.layout.ConversionPattern = %-5p - %m%n
	log4perl.appender.LOG1				= Log::Log4perl::Appender::File
	log4perl.appender.LOG1.filename		= sub { get_log_file_name( "export_atlas_cttv_json" ) }
	log4perl.appender.LOG1.header_text	= sub { get_log_file_header( "Atlas CTTV evidence strings" ) }
	log4perl.appender.LOG1.mode			= append
	log4perl.appender.LOG1.layout		= Log::Log4perl::Layout::PatternLayout
	log4perl.appender.LOG1.layout.ConversionPattern = %-5p - %m%n
);

# Initialise logger.
Log::Log4perl::init( \$logger_config );
my $logger = Log::Log4perl::get_logger;

# The current date and time.
my $time = DateTime->now;
$time = $time . "Z"; # FIXME: is it OK to hard code the Z?

# Filename to write the JSON report to.
my $jsonOutputFile = "expression_atlas_cttv_evidence_strings.json";

# Get the site config.
my $atlasSiteConfig = get_atlas_site_config;

# Get the filename of the property type EFO mappings.
my $propertyTypeEfoMappingsFile = File::Spec->catfile( $ENV{ "ATLAS_PROD" }, $atlasSiteConfig->get_property_types_efo_mappings );
# Create hash mapping the property types to their EFO URIs.
my $propertyTypesToEfoUris = &make_property_types_to_efo_uris( $propertyTypeEfoMappingsFile );

# Get an array of AtlasContrastDetails objects.
my $allContrastDetails = get_atlas_contrast_details;

# Get the details for contrasts that have "disease" in the characteristics.
# Return a hash sorting contrast details objects by disease EFO URI.
# We also check during this process that array designs used have mappings to
# Ensembl Homo sapiens. If not, we skip the contrast.
my $disease2contrastDetails = &get_disease_contrast_details( $allContrastDetails );

# Get the contrast names and experiment types from the XML config files. Do it
# before going through each gene, so that we don't end up opening the same file
# hundreds of times (once per gene).
my ( $expAcc2contrastID2contrastName, $expAcc2expType ) = &get_contrast_names_and_exp_type( $disease2contrastDetails );

# Query Atlas for all the differentially expressed genes.
my $geneID2expAcc2contrast2stats = &get_differential_genes_from_atlasdb;

# Take all the genes that are DE in disease contrasts. The following hash looks like the following:
# $h->{ geneID }->{ disease URI } = [ contrastDetails1, contrastDetails2 ]
my $geneID2disease2contrastDetails = &get_all_disease_genes( $geneID2expAcc2contrast2stats, $disease2contrastDetails );

# Create all the association records.
my $allAssociations = &create_all_associations( 
	$geneID2expAcc2contrast2stats, 
	$geneID2disease2contrastDetails, 
	$expAcc2contrastID2contrastName,
	$expAcc2expType,
	$propertyTypesToEfoUris,
	$time 
);

# Write the association records to the output file in JSON format.
$logger->info( "Writing JSON report to $jsonOutputFile..." );

# Convert the association records to JSON.
my $jsonConverter = JSON::XS->new->utf8->pretty( 1 );
my $json = $jsonConverter->encode( $allAssociations );

# Write the file.
open( my $fh, ">", $jsonOutputFile );
print $fh $json;
close( $fh );

$logger->info( "JSON report written successfully." );

# end
#####


#############
# SUBROUTINES
#############




sub make_property_types_to_efo_uris {

	my ( $propertyTypeEfoMappingsFile ) = @_;

	my $propertyTypesToEfoUris = {};

	open( my $fh, "<", $propertyTypeEfoMappingsFile ) 
		or $logger->logdie( "Cannot open $propertyTypeEfoMappingsFile : $!" );

	while( defined( my $line = <$fh> ) ) {
		
		chomp $line;

		# Skip header.
		if( $line =~ /^PROPERTY VALUE/ ) { next; }

		# Split line on tabs.
		my ( $propertyType, $ontoTerm, $ontoUrl ) = split "\t", $line;

		# Skip this one if it's not mapped.
		unless( $ontoTerm && $ontoUrl ) { next; }
		
		# Skip and warn if this term has more than one mapping.
		my $moreThanOne = 0;
		foreach my $ontoElement ( $ontoTerm, $ontoUrl ) {
			if( $ontoElement =~ / / ) {
				$moreThanOne++;
			}
		}
		if( $moreThanOne ) {
			$logger->warn( "$propertyType seems to have more than one ontology mapping in $propertyTypeEfoMappingsFile, please check." );
			next;
		}

		# Otherwise we're all good. Add the mapping to the hash.
		$propertyTypesToEfoUris->{ $propertyType } = $ontoUrl . $ontoTerm;
	}

	return $propertyTypesToEfoUris;
}


# Create a hash mapping disease EFO URIs to AtlasContrastDetails objects.
sub get_disease_contrast_details {

	my ( $allContrastDetails ) = @_;

	$logger->info( "Looking for disease contrasts..." );
	
	# Get a hash of the human array designs in Atlas.
	my $humanArrayDesigns = &get_human_array_designs;
	
	# Empty hashes to store the disease-contrastDetails associations, and the
	# diseases that do not have a match in EFO.
	my $disease2contrastDetails = {};

	# Go through the contrast details.
	foreach my $contrastDetails ( @{ $allContrastDetails } ) {
	
		my $expAcc = $contrastDetails->get_exp_acc;

		# Get the characteristics.
		my $characteristics = $contrastDetails->get_characteristics;

		# Only want the human ones.
		my $organism = $characteristics->{ "test" }->{ "organism" };
		if( !$organism ) { $logger->logdie( "No organism found for $expAcc" ); }
		elsif( $organism !~ /homo sapiens/i ) { next; }
		
		my $experimentConfig = &get_experiment_config( $expAcc );

		next unless $experimentConfig;
		
		# Need to also make sure that this data is from human array designs
		# only. 99% will be, but there are odd cases that aren't e.g. xenograft
		# experiments (E-GEOD-11981).
		if( $experimentConfig->get_atlas_experiment_type =~ /array/ ) {
			
			my $contrastArrayDesign = &get_array_design_for_contrast( $experimentConfig, $contrastDetails->get_contrast_id );

			unless( $humanArrayDesigns->{ $contrastArrayDesign } ) {
				$logger->info( "$contrastArrayDesign in experiment $expAcc is not mapped to Ensembl gene IDs. Skipping." );
				next;
			}
		}

		# See if any of the test assay group characteristic types have
		# "disease" in.
		my @diseaseTypes = grep { /^disease$/i } ( keys %{ $characteristics->{ "test" } } );

		# If we found disease(s)...
		if( @diseaseTypes ) {
			
			# Go through the types (should probably always only be one)...
			foreach my $diseaseType ( @diseaseTypes ) {

				# Get the value for this disease type.
				my $diseaseValue = $characteristics->{ "test" }->{ $diseaseType };

				# Get the EFO URI for this disease value.
				my $diseaseEfoUri = $contrastDetails->get_efo_uri( $diseaseType, $diseaseValue );
			
				# If we got an EFO URI back, make it into CTTV style (e.g. "efo:EFO_0000298")
				if( defined( $diseaseEfoUri ) ) {
					$diseaseEfoUri = &get_cttv_style_uri( $diseaseEfoUri );
				}
				# If no EFO URI was found for this disease use the text term
				# instead.
				else {
					$diseaseEfoUri = $diseaseValue;
				}
				
				# Add the contrast details object to the hash, under the EFO
				# URI for the disease.
				if( exists( $disease2contrastDetails->{ $diseaseEfoUri } ) ) {

					push @{ $disease2contrastDetails->{ $diseaseEfoUri } }, $contrastDetails;
				}
				else {
					$disease2contrastDetails->{ $diseaseEfoUri } = [ $contrastDetails ];
				}
			}
		}	
	}
	
	# Log how many genes we found.
	my $diseaseContrastCount = ( keys %{ $disease2contrastDetails } );
	$logger->info( "Found $diseaseContrastCount diseases." );

	return $disease2contrastDetails;
}


sub get_array_design_for_contrast {

	my ( $experimentConfig, $contrastID ) = @_;
	
	foreach my $analytics ( @{ $experimentConfig->get_atlas_analytics } ) {

		foreach my $contrast ( @{ $analytics->get_atlas_contrasts } ) {

			if( $contrast->get_contrast_id eq $contrastID ) {

				return $analytics->get_platform;
			}
		}
	}

	# If we're still here, something went wrong, so die.
	$logger->logdie( "Could not get array design for contrast $contrastID in experiment ",
		$experimentConfig->get_experiment_accession );
}


sub get_experiment_config {

	my ( $expAcc ) = @_;

	# Get the path to the Atlas experiments directory.
	my $atlasExpsDir = $ENV{ "ATLAS_EXPS" };
	
	# Create the XML config file path.
	my $atlasXMLfile = File::Spec->catfile( $atlasExpsDir, $expAcc, "$expAcc-configuration.xml" );
	
	# If we cannot read the XML config this means we can't get the
	# experiment type or the contrast name. I think that means we have to
	# die here as we can't continue without these. Running as fg_atlas the
	# script should always have perimssion to read all files, so if it
	# can't that means something is wrong.
	#-------------------------------------------------- FIXME
	# unless( -r $atlasXMLfile ) {
	# 	$logger->logdie( "Cannot read \"$atlasXMLfile\" to get contrast names and experiment type. Cannot continue." );
	# }
	#-------------------------------------------------- 

	# FIXME: remove when read-only dirs in $ATLAS_EXPS are fixed.
	unless( -r $atlasXMLfile ) {
		$logger->warn( "Cannot read \"$atlasXMLfile\" to get contrast names and experiment type. Skipping." );
		return;
	}

	# Parse config.
	my $experimentConfig = parseAtlasConfig( $atlasXMLfile );

	return $experimentConfig;
}


sub get_cttv_style_uri {

	my ( $efoUri ) = @_;

	# Hash mapping namespaces to prefixes.
	my $namespaceMap = {
		"http://purl.obolibrary.org/obo" => "obo",
		"http://www.ebi.ac.uk/efo" => "efo",
		"http://www.orpha.net/ORDO" => 'ordo'
	};
	
	# Split the URI into parts.
	my %parts;
	@parts{ my @keys = qw( schema auth path query frag ) } = uri_split( $efoUri );

	my @splitPath = split "/", $parts{ "path" };
	
	my $id = pop @splitPath;
	
	my $namespace = uri_join( $parts{ "schema" }, $parts{ "auth" }, pop @splitPath );
	
	unless( $namespaceMap->{ $namespace } ) {
		$logger->logdie( "No prefix found for $efoUri. Cannot continue." );
	}

	my $cttvStyleUri = $namespaceMap->{ $namespace } . ":" . $id;

	return $cttvStyleUri;
}


# Get the contrast names and experiment types from the Atlas XML config files.
sub get_contrast_names_and_exp_type {

	my ( $disease2contrastDetails ) = @_;

	# First get the experiment accessions and all their contrast IDs.
	# This will be a hash like e.g. $h->{ <exp_acc> }->{ <contrast_id> } = 1;
	my $expAcc2contrastID = {};
	
	# Go through the all the diseases and collect all the experiment accessions
	# and contrast IDs. This will provide a comprehensive list of all the XML
	# configs we need to open and what we need to get out of them.
	foreach my $diseaseUri ( keys %{ $disease2contrastDetails } ) {

		# Go through the contrast details for this disease...
		foreach my $contrastDetails ( @{ $disease2contrastDetails->{ $diseaseUri } } ) {

			my $expAcc = $contrastDetails->get_exp_acc;
			my $contrastID = $contrastDetails->get_contrast_id;

			$expAcc2contrastID->{ $expAcc }->{ $contrastID } = 1;
		}
	}
	

	# Now we have the list of experiments and their contrasts, we can open each
	# XML config and get the information we need.
	
	# Empty hashes for the contrast names and experiment types for each
	# experiment.
	$_ = {} for my ( $expAcc2contrastID2contrastName, $expAcc2expType );
	
	# Go through the experiments for this disease, open the XML config,
	# and get the experiment type and contrast names.
	foreach my $expAcc ( keys %{ $expAcc2contrastID } ) {
		
		my $experimentConfig = &get_experiment_config( $expAcc );

		unless( $experimentConfig ) {
			delete $expAcc2contrastID->{ $expAcc };
			next;
		}

		# Get the experiment type.
		$expAcc2expType->{ $expAcc } = $experimentConfig->get_atlas_experiment_type;
		
		# Get all the analytics for this experiment.
		my $allAnalytics = $experimentConfig->get_atlas_analytics;

		# Index all the contrast names from this experiment by their contrast
		# ID (in a hash).
		my $expContrastNames = &index_contrast_names_by_id( $allAnalytics, $expAcc );
		
		# Now go through the contrast IDs that we have in the disease contrasts
		# and collect the contrast names.
		foreach my $contrastID ( keys %{ $expAcc2contrastID->{ $expAcc } } ) {

			$expAcc2contrastID2contrastName->{ $expAcc }->{ $contrastID } = $expContrastNames->{ $contrastID };
		}
	}

	return ( $expAcc2contrastID2contrastName, $expAcc2expType );
}


# Read the Homo sapiens Ensembl annotation source from the git repository, and
# return a hash of all the array designs found therein.
sub get_human_array_designs {

	my $humanAnnotationSourceFile = File::Spec->catfile( 
		$ENV{ "ATLAS_PROD" }, 
		"sw", 
		"atlasprod", 
		"bioentity_annotations", 
		"ensembl", 
		"annsrcs", 
		"homo_sapiens" 
	);

	my $humanArrayDesigns = {};

	open(my $fh, "<", $humanAnnotationSourceFile)
		or $logger->logdie( "Cannot open $humanAnnotationSourceFile to get human array design accessions." );

	while( defined( my $line = <$fh> ) ) {
		
		if( $line =~ /^arrayDesign\.(A-\w{4}-\d+)/ ) {
			
			my $arrayDesign = $1;
			
			$humanArrayDesigns->{ $arrayDesign } = 1;
		}
	}
	
	close $fh;

	return $humanArrayDesigns;
}


# Take arrayref of AtlasConfig::Analytics objects and create a hash with
# contrast IDs as keys and contrast names as values.
sub index_contrast_names_by_id {
	
	my ( $allAnalytics, $expAcc ) = @_;

	# Go through analytics and make a hash containing just the contrast
	# names, indexed by contrast IDs.
	my $expContrastNames = {};

	foreach my $analytics ( @{ $allAnalytics } ) {
		
		# If this isn't a differential analytics (shouldn't happen), skip it.
		unless( $analytics->isa( "AtlasConfig::Analytics::Differential" ) ) {
			$logger->warn( "Found analytics in $expAcc which is not differential, skipping." );
			next;
		}
		
		# Get the contrasts from this analytics object.
		my $contrasts = $analytics->get_atlas_contrasts;
		
		# Go through the contrasts and add the IDs and names to the hash.
		foreach my $contrast ( @{ $contrasts } ) {

			$expContrastNames->{ $contrast->get_contrast_id } = $contrast->get_contrast_name;
		}
	}

	return $expContrastNames;
}


# Get all the differentially expressed genes from the Atlas database. Returns
# gene ID, experiment accession, contrast ID, log2 fold-change, and p-value.
sub get_differential_genes_from_atlasdb {
	
	# SQL query..
	my $query = "
		select distinct IDENTIFIER, EXPERIMENT, CONTRASTID, LOG2FOLD, PVAL
		from VW_DIFFANALYTICS
		inner join EXPERIMENT
		on EXPERIMENT = ACCESSION
		where PRIVATE = 'F'
		order by IDENTIFIER, EXPERIMENT, CONTRASTID";
	
	# Connect to Atlas database and get database handle.
	my $atlasDBH = connect_atlas;

	$logger->info( "Querying Atlas database for differential expression data..." );

	# Get statement handle by preparing query.
	my $atlasSH = $atlasDBH->prepare( $query )
		or $logger->logdie( "Could not prepare query: ", $atlasDBH->errstr );

	# Execute the query.
	$atlasSH->execute or $logger->logdie( "Could not execute query: ", $atlasSH->errstr );

	my $geneID2expAcc2contrast2stats = {};

	# Go through the resulting rows.
	while( my $row = $atlasSH->fetchrow_arrayref ) {

		# Get the gene ID, experiment accession, contrast ID, log fold-change, and p-value.
		my ( $identifier, $expAcc, $contrastID, $logFC, $pvalue ) = @{ $row };

		# Add them to the results hash.
		$geneID2expAcc2contrast2stats->{ $identifier }->{ $expAcc }->{ $contrastID }->{ "logfc" } = $logFC;
		$geneID2expAcc2contrast2stats->{ $identifier }->{ $expAcc }->{ $contrastID }->{ "pvalue" } = $pvalue;
	}

	$atlasDBH->disconnect;

	return $geneID2expAcc2contrast2stats;
}


# Create a hash mapping gene IDs to the disease EFO URI and
# AtlasContrastDetails object(s) where each gene was differentially expressed.
sub get_all_disease_genes {

	my ( $geneID2expAcc2contrast2stats, $disease2contrastDetails ) = @_;
	
	$logger->info( "Finding all disease genes..." );

	# First it makes following steps easier if we create a new hash of gene
	# IDs so that the experiment accession and contrast ID come first and then
	# the gene IDs.
	my $expAcc2contrastID2geneIDs = &rearrange_de_genes_hash( $geneID2expAcc2contrast2stats );
	
	# Empty hash for the disease genes, their experiment accession(s) and contrast ID(s).
	my $geneID2disease2contrastDetails = {};
	
	# Go through the diseases...
	foreach my $disease ( keys %{ $disease2contrastDetails } ) {
		
		# Get the details of all contrasts for this disease.
		my $thisDiseaseContrastDetails = $disease2contrastDetails->{ $disease };
		
		# Go through the contrast details for this disease...
		foreach my $contrastDetails ( @{ $thisDiseaseContrastDetails } ) {
			
			# Get the experiment accession and contrast ID from the contrast
			# details.
			my $expAcc = $contrastDetails->get_exp_acc;
			my $contrastID = $contrastDetails->get_contrast_id;

			# Get the array of identifiers of genes that are differentially
			# expressed in this contrast.
			my $contrastDEgenes = $expAcc2contrastID2geneIDs->{ $expAcc }->{ $contrastID };
			
			# Go through these genes...
			foreach my $geneID ( @{ $contrastDEgenes } ) {
				
				# Add the contrast details object to the hash under this gene identifier
				# and experiment accession.
				if( exists( $geneID2disease2contrastDetails->{ $geneID }->{ $disease } ) ) {

					push @{ $geneID2disease2contrastDetails->{ $geneID }->{ $disease } }, $contrastDetails;
				}
				else {

					$geneID2disease2contrastDetails->{ $geneID }->{ $disease } = [ $contrastDetails ];
				}
			}
		}
	}
	
	# Count how many disease genes we found and log this.
	my $diseaseGeneCount = keys %{ $geneID2disease2contrastDetails };
	$logger->info( "Got $diseaseGeneCount disease genes." );

	return $geneID2disease2contrastDetails;
}


# When we create the hash of "disease genes" it's more convenient if the hash
# of differentially expressed genes is ordered by experiment accession, and
# then contrast ID, with each contrast ID pointing to an array of gene IDs.
# This function performs the re-ordering.
sub rearrange_de_genes_hash {

	my ( $geneID2expAcc2contrast2stats ) = @_;
	
	# Empty hash for the re-sorted hash.
	my $expAcc2contrastID2geneIDs = {};
	
	# Go through the gene IDs...
	foreach my $geneID ( keys %{ $geneID2expAcc2contrast2stats } ) {
		
		# Go through this gene's experiment accessions...
		foreach my $expAcc ( keys %{ $geneID2expAcc2contrast2stats->{ $geneID } } ) {
			
			# Go through this experiment's contrast IDs...
			foreach my $contrastID ( keys %{ $geneID2expAcc2contrast2stats->{ $geneID }->{ $expAcc } } ) {
				
				# Add the gene ID to an array under this experiment accession and contrast ID.
				if( exists( $expAcc2contrastID2geneIDs->{ $expAcc }->{ $contrastID } ) ) {
					
					push @{ $expAcc2contrastID2geneIDs->{ $expAcc }->{ $contrastID } }, $geneID;
				}
				else {

					$expAcc2contrastID2geneIDs->{ $expAcc }->{ $contrastID } = [ $geneID ];
				}
			}
		}
	}

	return $expAcc2contrastID2geneIDs;
}


# Create all the gene-disease association records. We will end up with one
# record per contrast, per gene.
sub create_all_associations {

	my ( 
		$geneID2expAcc2contrast2stats, 
		$geneID2disease2contrastDetails, 
		$expAcc2contrastID2contrastName, 
		$expAcc2expType,
		$propertyTypesToEfoUris,
		$time 
	) = @_;
	
	$logger->info( "Creating association records..." );

	my $allAssociations = [];

	foreach my $geneID ( keys %{ $geneID2disease2contrastDetails } ) {

		# Get the stats for this gene.
		my $expAcc2contrastID2stats = $geneID2expAcc2contrast2stats->{ $geneID };

		foreach my $disease ( keys %{ $geneID2disease2contrastDetails->{ $geneID } } ) {
			
			foreach my $contrastDetails ( @{ $geneID2disease2contrastDetails->{ $geneID }->{ $disease } } ) {

				my $expAcc = $contrastDetails->get_exp_acc;
				my $contrastID = $contrastDetails->get_contrast_id;

				# Get the evidence code for this association.
				my $associationEvidenceCode = &get_association_evidence_code( $expAcc2expType->{ $expAcc } );

				# Create the links to ArrayExpress experiments.
				my $arrayExpressLink = {
					"nice_name" => "ArrayExpress Experiment overview",
					"url" => "http://www.ebi.ac.uk/arrayexpress/$expAcc"
				};
				# Create the links to Atlas experiments.
				my $atlasLink = {
					"nice_name" => "Gene expression in Expression Atlas",
					"url" => "http://wwwdev.ebi.ac.uk/gxa/experiments/$expAcc?geneQuery=$geneID"
				};

				# Combine the arrays of links.
				my $aeAtlasLinks = [ $arrayExpressLink, $atlasLink ];
				
				# The association score will be the differential expression p-value.
				my $associationScore = $geneID2expAcc2contrast2stats->{ $geneID }->{ $expAcc }->{ $contrastID }->{ "pvalue" };
				$associationScore = sprintf( "%.2e", $associationScore );
				
				# Get the log2 fold-change.
				my $logFC = $geneID2expAcc2contrast2stats->{ $geneID }->{ $expAcc }->{ $contrastID }->{ "logfc" };
				$logFC = nearest( .01, $logFC ); 
				
				# Get the direction.
				my $direction = $logFC > 0 ? "up" : "down";

				# Make an array of organism part(s) and/or cell type(s) and/or
				# cell line(s) for this contrast.
				my $biosamplesArray = &create_biosamples_array( $contrastDetails );

				# Make a hash for all the characteristics and factors, to add
				# to the biological_object's "experimental_evidence_specific"
				# section.
				my $characteristicsAndFactors = &merge_characteristics_and_factors( $contrastDetails, $propertyTypesToEfoUris );

				
				# Create the hash for the JSON dump using the information gathered above.
				# Add the biological subject (the gene).
				my $associationRecord->{ "biological_subject" }->{ "about" } = "ensembl:$geneID";
				$associationRecord->{ "biological_subject" }->{ "properties" }->{ "association_context" } = "cttvexp:transcript";
				$associationRecord->{ "biological_subject" }->{ "properties" }->{ "activity" } = "cttvexp:$direction";

				# Add the provenance...
				$associationRecord->{ "provenance" }->{ "date_asserted" } = $time;
				$associationRecord->{ "provenance" }->{ "is_associated" } = 1;
				$associationRecord->{ "provenance" }->{ "type" }->{ "database" }->{ "true" } = 1;
				$associationRecord->{ "provenance" }->{ "type" }->{ "database" }->{ "id" } = "Expression_Atlas";
				$associationRecord->{ "provenance" }->{ "type" }->{ "database" }->{ "version" } = "NA";
				$associationRecord->{ "provenance" }->{ "evidence_codes" } = [ $associationEvidenceCode ];
				$associationRecord->{ "provenance" }->{ "urls" }->{ "linkouts" } = $aeAtlasLinks;
				$associationRecord->{ "provenance" }->{ "association_score" }->{ "p-value" } = $associationScore;
				$associationRecord->{ "provenance" }->{ "experimental_evidence_specific" } = {
					"study_id" => $expAcc,
					"comparison_name" => $expAcc2contrastID2contrastName->{ $expAcc }->{ $contrastID },
					"log2_fold_change" => $logFC
				};
				
				# Add the biological object (the disease and samples).
				$associationRecord->{ "biological_object" }->{ "about" } = $disease;
				$associationRecord->{ "biological_object" }->{ "properties" }->{ "biosamples" } = $biosamplesArray;
				$associationRecord->{ "biological_object" }->{ "properties" }->{ "experimental_evidence_specific" } = $characteristicsAndFactors;
				
				push @{ $allAssociations }, $associationRecord;
			}
		}
	}
	
	# Count the records and log this.
	my $recordCount = @{ $allAssociations };
	$logger->info( "Created $recordCount association records." );

	return $allAssociations;
}


# Return the relevant ECO evidence code based on the experiment type.
sub get_association_evidence_code {

	my ( $expType ) = @_;

	if( $expType =~ /microarray/i ) {
		return "obo:ECO_0000356";
	}
	elsif( $expType =~ /rnaseq/i ) {
		return "obo:ECO_0000357";
	}
	else {
		$logger->logdie( "Unknown experiment type: $expType"  );
	}

	# FIXME: We also need to account for the GSEA codes:
	# obo:ECO_0000358 differential geneset expression evidence from microarray experiment
	# obo:ECO_0000359 differential geneset expression evidence from RNA-seq experiment
	# But we are not including this data in the JSON report for now.
}


# Get the values for any "organism part", "cell type", and/or "cell line"
# characteristics.
sub create_biosamples_array {

	my ( $contrastDetails ) = @_;

	my $expAcc = $contrastDetails->get_exp_acc;
	my $characteristics = $contrastDetails->get_characteristics;

	# We will look for organism part, cell line, cell type.
	my @wantedTypes = ( "organism part", "cell line", "cell type" );

	my $biosamplesArray = [];

	# Look for the wanted types in this set of attributes. If we find it,
	# get the value, get the EFO URI for this value, and add that the the array.
	foreach my $type ( keys %{ $characteristics->{ "test" } } ) {

		if( grep $_ eq $type, @wantedTypes ) {

			my $value = $characteristics->{ "test" }->{ $type };

			my $efoUri = $contrastDetails->get_efo_uri( $type, $value );

			if( defined( $efoUri ) ) {
				$efoUri = &get_cttv_style_uri( $efoUri );
				push @{ $biosamplesArray }, $efoUri;
			}
			else {
				push @{ $biosamplesArray }, $value;
			}
		}
	}

	return $biosamplesArray;
}


# Create a hash containing the types and values for characteristics and factors
# for the test and reference assay group.
sub merge_characteristics_and_factors {

	my ( $contrastDetails, $propertyTypesToEfoUris ) = @_;

	# Empty hash to store the factors and characteristics.
	my $allAttributes = {};

	my $characteristics = $contrastDetails->get_characteristics;
	my $factors = $contrastDetails->get_factors;
	
	# Go through the assay groups.
	foreach my $assayGroupType ( "test", "reference" ) {
		
		# Go through the characteristics and factors.
		foreach my $attributeHash ( $characteristics, $factors ) {
			
			# Get the characteristics or factors for this assay group.
			my $agAttributes = $attributeHash->{ $assayGroupType };
			
			# Go through the characteristic or factor types.
			foreach my $type ( keys %{ $agAttributes } ) {
				
				my $value = $agAttributes->{ $type };

				my $valueEfoUri = $contrastDetails->get_efo_uri( $type, $value );

				if( defined( $valueEfoUri ) ) {
					$valueEfoUri = &get_cttv_style_uri( $valueEfoUri );
				}
				else {
					$valueEfoUri = $value;
				}

				# Get the EFO URI for the type, in CTTV style.
				my $typeEfoUri = $propertyTypesToEfoUris->{ $type };

				if( defined( $typeEfoUri ) ) {
					$typeEfoUri = &get_cttv_style_uri( $typeEfoUri );
				}
				else {
					$typeEfoUri = $type;
				}

				# Append "_group" to the assay group type (test or reference)
				# for the JSON report.
				my $jsonAssayGroupType = $assayGroupType . "_group";
				
				# If we haven't added this one already, add it to the hash
				# under the assay group type.
				unless( grep $_ eq $typeEfoUri, ( keys %{ $allAttributes->{ $jsonAssayGroupType } } ) ) {

					$allAttributes->{ $jsonAssayGroupType }->{ $typeEfoUri } = $valueEfoUri;
				}
			}
		}
	}

	# Return the merged factors and characteristics ready to go in the JSON
	# report.
	return $allAttributes;
}
