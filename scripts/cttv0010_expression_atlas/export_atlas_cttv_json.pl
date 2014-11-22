#!/usr/bin/env perl
#

=pod

=head1 NAME

export_atlas_cttv_json.pl -- Create JSON report summarising all Expression Atlas human disease - gene associations for CTTV.

=head1 DESCRIPTION

This script creates a JSON document called
expression_atlas_cttv_evidence_strings.json which summarises differential
expression results for each gene found to be differentially expressed in all
experiments about human disease currently in Expression Atlas (http://www.ebi.ac.uk/gxa).

=head1 SYNOPSIS

bsub export_atlas_cttv_json.pl

=cut

use strict;
use warnings;
use 5.10.0; # Use Perl 5.10.0 features.

use sparql;
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

# Auto flush buffer.
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

# Get an array of AtlasContrastDetails objects.
my $allContrastDetails = get_atlas_contrast_details;

my $efoUrisToIdOrgUris = map_efo_uris_to_identifiers_uris( $allContrastDetails );

# Get the details for contrasts that have "disease" in the characteristics.
# Return a hash sorting contrast details objects by disease EFO URI.
# We also check during this process that array designs used have mappings to
# Ensembl Homo sapiens. If not, we skip the contrast.
my $disease2contrastDetails = get_disease_contrast_details( $allContrastDetails, $efoUrisToIdOrgUris );

# Get the contrast names and experiment types from the XML config files. Do it
# before going through each gene, so that we don't end up opening the same file
# hundreds of times (once per gene).
my ( $expAcc2contrastID2contrastName, $expAcc2expType ) = get_contrast_names_and_exp_type( $disease2contrastDetails );

# Query Atlas for all the differentially expressed genes.
my $geneID2expAcc2contrast2stats = get_differential_genes_from_atlasdb();

# Take all the genes that are DE in disease contrasts. The following hash looks like the following:
# $h->{ geneID }->{ disease URI } = [ contrastDetails1, contrastDetails2 ]
my $geneID2disease2contrastDetails = get_all_disease_genes( $geneID2expAcc2contrast2stats, $disease2contrastDetails );

# Create all the association records.
my $allAssociations = create_all_associations( 
	$efoUrisToIdOrgUris,
	$geneID2expAcc2contrast2stats, 
	$geneID2disease2contrastDetails, 
	$expAcc2contrastID2contrastName,
	$expAcc2expType,
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


=head1 SUBROUTINES

=over 2

=item map_efo_uris_to_identifiers_uris

Returns a hash mapping EFO URIs to their corresponding http://identifiers.org URIs.

=cut

sub map_efo_uris_to_identifiers_uris {

	my ( $allContrastDetails ) = @_;
	
	$logger->info( "Mapping EFO URIs to http://identifiers.org URIs..." );

	# First, map all URIs to 1 to create a hash of unique URIs.
	my $urisHash = {};

	# Get the property value URIs from the contrast details.
	foreach my $contrastDetails ( @{ $allContrastDetails } ) {

		my $characteristics = $contrastDetails->get_characteristics;
		my $factors = $contrastDetails->get_factors;

		foreach my $propertyHash ( $characteristics, $factors ) {
			
			foreach my $assayGroupType ( "test", "reference" ) {

				foreach my $propertyType ( keys %{ $propertyHash->{ $assayGroupType } } ) {

					my $propertyValue = $propertyHash->{ $assayGroupType }->{ $propertyType };

					my $efoUris = $contrastDetails->get_efo_uris( $propertyType, $propertyValue );

					if( defined( $efoUris ) ) {
						foreach my $efoUri ( @{ $efoUris } ) {
							$urisHash->{ $efoUri } = 1;
						}
					}
				}
			}
		}
	}
	
	# Next need to get the http://identifiers.org URIs for each Zoomage URI.
	foreach my $zoomageUri ( keys %{ $urisHash } ) {	
		$urisHash->{ $zoomageUri } = query_identifiers_org( $zoomageUri );
	}

	$logger->info( "Finished mapping EFO URIs to http://identifiers.org URIs." );
	
	# Return the completed hash.
	return $urisHash;
}


=item query_identifiers_org

Given an EFO (or other) URI, runs a query against the http://idenfiers.org
SPARQL endpoint and returns the http://identifiers.org URI.

=cut

sub query_identifiers_org {

	my ( $uriFromAtlas ) = @_;
	
	# Prefix for the query.
	my $prefix = "PREFIX owl: <http://www.w3.org/2002/07/owl#>";

	# Endpoint to query against.
	my $endpoint = "http://identifiers.org/services/sparql";

	# Query to get URIs.
	my $query =
	"SELECT ?uris WHERE {
	<$uriFromAtlas> owl:sameAs  ?uris
	}";

	# Create a new sparql instance.
	my $sparql = sparql->new();
	
	# Run the query against the endpoint, including the prefix.
	my $result = $sparql->query( $endpoint, $prefix . $query );

	# Empty hash to store http://identifiers.org URIs.
	my $idOrgUris = {};

	# Go through the URIs and find the http://identifiers.org ones.
	foreach my $row ( @{ $result } ) {
		my $uri = $row->{ "uris" };
		
		# Add relevant ones to the hash.
		if( $uri =~ /identifiers\.org/ ) {
			# Remove "<" and ">" from either side of the URI returned.
			$uri =~ s/^<//;
			$uri =~ s/>$//;

			# Add the URI to the hash.
			$idOrgUris->{ $uri } = 1;
		}
	}
	
	# Warn if we didn't get an http://identifiers.org URI and return the
	# original one.
	unless( keys %{ $idOrgUris } ) {
		$logger->warn( "Did not get http://identifiers.org URI for $uriFromAtlas" );
		return $uriFromAtlas;
	}

	# If we got more than one http://identifiers.org URI back then we need to
	# choose one to use.
	if( keys %{ $idOrgUris } > 1 ) {
		my $idOrgUri = choose_id_org_uri( $idOrgUris );
		return $idOrgUri;
	}
	else {
		my ( $idOrgUri ) = keys %{ $idOrgUris };
		return $idOrgUri;
	}
}


=item choose_id_org_uri

Given a hash with http://identifiers.org as keys, choose one of them based on
an allowed list of patterns, and return it.

=cut

sub choose_id_org_uri {

	my ( $uriHash ) = @_;

	my $namespace = "http://identifiers.org";

	# Array of patterns to match against.
	# This will cater for all the cases we come across where we find more than
	# one http://identifiers.org URIs. Add new patterns here and URI will be
	# checked against them.
	my @patternArray = (
		"$namespace\/cl\/",
		"$namespace\/go\/",
		"$namespace\/pato\/",
		"$namespace\/chebi\/",
		"$namespace\/po\/",
		"$namespace\/unit\/",
		"$namespace\/bto\/"
	);
	
	# Find the URI that matches a pattern from the @patternArray, and return it.
	foreach my $uri ( keys %{ $uriHash } ) {
		if( grep $uri =~ $_, @patternArray ) {
			return $uri;
		}
	}

	# If we're still here, we didn't return anything, so die as we couldn't
	# choose a URI.
	my $uris = join "\n", keys %{ $uriHash };
	$logger->logdie( "Could not choose a URI from:\n$uris" );
}



=item get_disease_contrast_details

Returns a hash mapping EFO URIs for all human diseases found to their AtlasContrastDetails object(s).

=cut

sub get_disease_contrast_details {

	my ( $allContrastDetails, $efoUrisToIdOrgUris ) = @_;

	$logger->info( "Looking for disease contrasts..." );
	
	# Get a hash of the human array designs in Atlas.
	my $humanArrayDesigns = get_human_array_designs();
	
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
		
		my $experimentConfig = get_experiment_config( $expAcc );

		next unless $experimentConfig;
		
		# Need to also make sure that this data is from human array designs
		# only. 99% will be, but there are odd cases that aren't e.g. xenograft
		# experiments (E-GEOD-11981).
		if( $experimentConfig->get_atlas_experiment_type =~ /array/ ) {
			
			my $contrastArrayDesign = get_array_design_for_contrast( $experimentConfig, $contrastDetails->get_contrast_id );

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
				my $diseaseEfoUris = $contrastDetails->get_efo_uris( $diseaseType, $diseaseValue );
			
				my $diseaseUriString;

				# If we got any EFO URIs back, make them into http://identifiers.org URIs.
				if( defined( $diseaseEfoUris ) ) {
					my $identifiersOrgUris = [];
					foreach my $diseaseEfoUri ( @{ $diseaseEfoUris } ) {
						my $idOrgUri = $efoUrisToIdOrgUris->{ $diseaseEfoUri };
						push @{ $identifiersOrgUris }, $idOrgUri;
					}
					# Stick them together with commas, to make a key for the
					# hash. This will be split to an array for the JSON report.
					$diseaseUriString = join ",", @{ $identifiersOrgUris };
				}
				# If no EFO URI was found for this disease use the text term
				# instead.
				else {
					$diseaseUriString = $diseaseValue;
				}
				
				# Add the contrast details object to the hash, under the EFO
				# URI for the disease.
				if( exists( $disease2contrastDetails->{ $diseaseUriString } ) ) {

					push @{ $disease2contrastDetails->{ $diseaseUriString } }, $contrastDetails;
				}
				else {
					$disease2contrastDetails->{ $diseaseUriString } = [ $contrastDetails ];
				}
			}
		}	
	}
	
	# Log how many genes we found.
	my $diseaseContrastCount = ( keys %{ $disease2contrastDetails } );
	$logger->info( "Found $diseaseContrastCount diseases." );

	return $disease2contrastDetails;
}


=item get_human_array_designs

Returns a hash with all Homo sapiens microarray designs used in Expression
Atlas as keys.

=cut

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


=item get_experiment_config

Given an experiment accession, parse the experiment's Expression Atlas XML
config into an AtlasConfig::ExperimentConfig object, and return it.

=cut

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


=item get_array_design_for_contrast

Given an Expression Atlas AtlasConfig::ExperimentConfig object and a contrast
ID, return the array design (platform) associated.

=cut

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


=item get_contrast_names_and_exp_type

Go through all the human disease comparisons found, and return two hashes: one
mapping experiment accessions to comparison IDs to comparison names; the other
mapping experiment accessions to Expression Atlas experiment types.

=cut

# Get the contrast names and experiment types from the Atlas XML config files.
sub get_contrast_names_and_exp_type {

	my ( $disease2contrastDetails ) = @_;

	# First get the experiment accessions and all their contrast IDs.
	# This will be a hash like e.g. $h->{ <exp_acc> }->{ <contrast_id> } = 1;
	my $expAcc2contrastID = {};
	
	# Go through the all the diseases and collect all the experiment accessions
	# and contrast IDs. This will provide a comprehensive list of all the XML
	# configs we need to open and what we need to get out of them.
	foreach my $diseaseUriString ( keys %{ $disease2contrastDetails } ) {

		# Go through the contrast details for this disease...
		foreach my $contrastDetails ( @{ $disease2contrastDetails->{ $diseaseUriString } } ) {

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
		
		my $experimentConfig = get_experiment_config( $expAcc );

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
		my $expContrastNames = index_contrast_names_by_id( $allAnalytics, $expAcc );
		
		# Now go through the contrast IDs that we have in the disease contrasts
		# and collect the contrast names.
		foreach my $contrastID ( keys %{ $expAcc2contrastID->{ $expAcc } } ) {

			$expAcc2contrastID2contrastName->{ $expAcc }->{ $contrastID } = $expContrastNames->{ $contrastID };
		}
	}

	return ( $expAcc2contrastID2contrastName, $expAcc2expType );
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


=item get_differential_genes_from_atlasdb

Query the Expression Atlas database for all differentially expressed genes.
Return a hash mapping gene IDs to experiment accessions to comparison names to
log fold-changes and p-values.

=cut

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


=item get_all_disease_genes

Given hashes mapping differentially expressed genes to experiment accessions to
comparisons to statistics, and human diseases to comparisons -- return a hash
mapping gene IDs to disease EFO URI(s) to comparisons.

=cut

sub get_all_disease_genes {

	my ( $geneID2expAcc2contrast2stats, $disease2contrastDetails ) = @_;
	
	$logger->info( "Finding all disease genes..." );

	# First it makes following steps easier if we create a new hash of gene
	# IDs so that the experiment accession and contrast ID come first and then
	# the gene IDs.
	my $expAcc2contrastID2geneIDs = rearrange_de_genes_hash( $geneID2expAcc2contrast2stats );
	
	# Empty hash for the disease genes, their experiment accession(s) and contrast ID(s).
	my $geneID2disease2contrastDetails = {};
	
	# Go through the diseases...
	foreach my $diseaseUriString ( keys %{ $disease2contrastDetails } ) {
		
		# Get the details of all contrasts for this disease.
		my $thisDiseaseContrastDetails = $disease2contrastDetails->{ $diseaseUriString };
		
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
				if( exists( $geneID2disease2contrastDetails->{ $geneID }->{ $diseaseUriString } ) ) {

					push @{ $geneID2disease2contrastDetails->{ $geneID }->{ $diseaseUriString } }, $contrastDetails;
				}
				else {

					$geneID2disease2contrastDetails->{ $geneID }->{ $diseaseUriString } = [ $contrastDetails ];
				}
			}
		}
	}
	
	# Count how many disease genes we found and log this.
	my $diseaseGeneCount = keys %{ $geneID2disease2contrastDetails };
	$logger->info( "Got $diseaseGeneCount disease genes." );

	return $geneID2disease2contrastDetails;
}


=item rearrange_de_genes_hash

Given a hash mapping gene IDs to experiment accessions to comparison IDs to
statistics, return a hash mapping experiment accessions to comparison IDs to
gene IDs.

=cut

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


=item create_all_associations

Given all the data gathered previously, create an hash for every gene-disease
association modelling a CTTV JSON record. Collect all these hashes in an array
and return this.

=cut

sub create_all_associations {

	my ( 
		$efoUrisToIdOrgUris,
		$geneID2expAcc2contrast2stats, 
		$geneID2disease2contrastDetails, 
		$expAcc2contrastID2contrastName, 
		$expAcc2expType,
		$time 
	) = @_;
	
	$logger->info( "Creating association records..." );

	my $allAssociations = [];

	foreach my $geneID ( keys %{ $geneID2disease2contrastDetails } ) {

		# Get the stats for this gene.
		my $expAcc2contrastID2stats = $geneID2expAcc2contrast2stats->{ $geneID };

		foreach my $diseaseUriString ( keys %{ $geneID2disease2contrastDetails->{ $geneID } } ) {
			
			# Split the URIs into an array if there's more than one.
			my @diseaseUriArray = split /,/, $diseaseUriString;
			
			foreach my $contrastDetails ( @{ $geneID2disease2contrastDetails->{ $geneID }->{ $diseaseUriString } } ) {

				# Get the experiment accession and the contrast ID for this
				# contrast.
				my $expAcc = $contrastDetails->get_exp_acc;
				my $contrastID = $contrastDetails->get_contrast_id;

				# Get the experiment type.
				my $expType = $expAcc2expType->{ $expAcc };

				# Get the evidence code for this association.
				my $associationEvidenceCode = get_association_evidence_code( $expType );


				# The association score will be the differential expression p-value.
				my $associationScore = $geneID2expAcc2contrast2stats->{ $geneID }->{ $expAcc }->{ $contrastID }->{ "pvalue" };
				$associationScore = sprintf( "%.2e", $associationScore );
				
				# Get the log2 fold-change.
				my $logFC = $geneID2expAcc2contrast2stats->{ $geneID }->{ $expAcc }->{ $contrastID }->{ "logfc" };
				$logFC = nearest( .01, $logFC ); 
				
				# Get the direction.
				my $direction = $logFC > 0 ? "increased_transcript_level" : "decreased_transcript_level";

				# Make an array of organism part(s) and/or cell type(s) and/or
				# cell line(s) for this contrast.
				my $biosamplesArray = create_biosamples_array( $contrastDetails, $efoUrisToIdOrgUris );

				# Make a hash for the factor values, to add to the
				# biological_object's "experiment_specific" section.
				my $factors = get_factor_uris( $contrastDetails, $efoUrisToIdOrgUris );
				# Get the human-readable name for this comparison.
				my $comparisonName = $expAcc2contrastID2contrastName->{ $expAcc }->{ $contrastID };
				# Get the description of the differential expression statistics method.
				my $deMethodDesc = get_de_method_description( $expAcc, $expType );

				# identifiers.org URIs for gene ID, experiment accession,
				# CTTV-specific terms.
				my $geneIDuri = "http://identifiers.org/ensembl/$geneID";
				my $gxaExptIDuri = "http://identifiers.org/gxa.expt/$expAcc";
				my $aeExptIDuri = "http://identifiers.org/arrayexpress/$expAcc";
				my $cttvTargetUri = "http://identifiers.org/cttv.target/transcript";
				my $cttvActivityUri = "http://identifiers.org/cttv.activity/$direction";

				# Create the links to ArrayExpress experiments.
				my $arrayExpressLink = {
					"nice_name" => "ArrayExpress Experiment overview",
					"url" => $aeExptIDuri
				};
				# Create the links to Atlas experiments.
				my $atlasLink = {
					"nice_name" => "Gene expression in Expression Atlas",
					"url" => "http://wwwdev.ebi.ac.uk/gxa/experiments/$expAcc?geneQuery=$geneID"
				};
				# Combine the arrays of links.
				my $aeAtlasLinks = [ $arrayExpressLink, $atlasLink ];
				
				# Some names for tags, define them here to reduce risk of typos.
				my $uniqueAssocTag = "unique_association_fields";
				my $bioSubjTag = "biological_subject";
				my $propertiesTag = "properties";
				my $evidenceTag = "evidence";
				my $assocScoreTag = "association_score";
				my $urlsTag = "urls";
				my $linkoutsTag = "linkouts";
				my $exptSpecTag = "experiment_specific";
				my $biolObjTag = "biological_object";

				
				# Create the hash for the JSON dump using the information gathered above.
				my $associationRecord->{ "validated_against_schema_version" } = get_schema_version();

				# Add unique association fields.
				$associationRecord->{ $uniqueAssocTag }->{ "geneID" } = $geneIDuri;
				$associationRecord->{ $uniqueAssocTag }->{ "study_id" } = $gxaExptIDuri;
				$associationRecord->{ $uniqueAssocTag }->{ "comparison_name" } = $comparisonName;

				# Add the biological subject (the gene).
				$associationRecord->{ $bioSubjTag }->{ "about" } = [ $geneIDuri ];
				$associationRecord->{ $bioSubjTag }->{ $propertiesTag }->{ "target_type" } = $cttvTargetUri;
				$associationRecord->{ $bioSubjTag }->{ $propertiesTag }->{ "activity" } = $cttvActivityUri;

				# Add the evidence.
				# The "association_score" field:
				# The "probability" field -- this is null for Atlas.
				$associationRecord->{ $evidenceTag }->{ $assocScoreTag }->{ "probability" }->{ "value" } = undef;
				$associationRecord->{ $evidenceTag }->{ $assocScoreTag }->{ "probability" }->{ "method" } = undef;
				# The "pvalue" field -- this is the p-value from the
				# differential expression analysis.
				$associationRecord->{ $evidenceTag }->{ $assocScoreTag }->{ "pvalue" }->{ "value" } = $associationScore;
				$associationRecord->{ $evidenceTag }->{ $assocScoreTag }->{ "pvalue" }->{ "method" } = $deMethodDesc;
				# The "provenance_type" field:
				# The "database" field.
				$associationRecord->{ $evidenceTag }->{ "provenance_type" }->{ "database" }->{ "version" } = "NA";
				$associationRecord->{ $evidenceTag }->{ "provenance_type" }->{ "database" }->{ "id" } = "Expression_Atlas";
				# The "urls" and "linkouts" fields:
				$associationRecord->{ $evidenceTag }->{ $urlsTag }->{ $linkoutsTag } = $aeAtlasLinks;
				# The "evidence_specific" fields:
				$associationRecord->{ $evidenceTag }->{ $exptSpecTag }->{ "study_id" } = $gxaExptIDuri;
				$associationRecord->{ $evidenceTag }->{ $exptSpecTag }->{ "comparison_name" } = $comparisonName;
				$associationRecord->{ $evidenceTag }->{ $exptSpecTag }->{ "log2_fold_change" } = $logFC;
				# Last fields, is_associated, the evidence codes, and the date.
				$associationRecord->{ $evidenceTag }->{ "is_associated" } = JSON::XS->true;
				$associationRecord->{ $evidenceTag }->{ "evidence_codes" } = [ $associationEvidenceCode ];
				$associationRecord->{ $evidenceTag }->{ "date_asserted" } = $time;

				# Add the biological object (the disease and samples).
				$associationRecord->{ $biolObjTag }->{ "about" } = \@diseaseUriArray;
				$associationRecord->{ $biolObjTag }->{ "properties" }->{ "biosamples" } = $biosamplesArray;
				$associationRecord->{ $biolObjTag }->{ "properties" }->{ $exptSpecTag } = $factors;
				
				push @{ $allAssociations }, $associationRecord;
			}
		}
	}
	
	# Count the records and log this.
	my $recordCount = @{ $allAssociations };
	$logger->info( "Created $recordCount association records." );

	return $allAssociations;
}


=item get_association_evidence_code

Given an Expression Atlas experiment type, return the appropriate ECO URI.

=cut

sub get_association_evidence_code {

	my ( $expType ) = @_;

	if( $expType =~ /microarray/i ) {
		return "http://identifiers.org/eco/ECO:0000356";
	}
	elsif( $expType =~ /rnaseq/i ) {
		return "http://identifiers.org/eco/ECO:0000357";
	}
	else {
		$logger->logdie( "Unknown experiment type: $expType"  );
	}

	# FIXME: We also need to account for the GSEA codes:
	# http://identifiers.org/eco/ECO:0000358 differential geneset expression evidence from microarray experiment
	# http://identifiers.org/eco/ECO:0000359 differential geneset expression evidence from RNA-seq experiment
	# But we are not including this data in the JSON report for now.
}


=item create_biosamples_array

Given a comparison (AtlasContrastDetails object) and a hash mapping EFO URIs to
http://identifiers.org URIs, create and return an array containing the
http://identifiers.org URIs of all the tissues, cell lines, and/or cell types
annotated in the comparison.

=cut

# Get the values for any "organism part", "cell type", and/or "cell line"
# characteristics.
sub create_biosamples_array {

	my ( $contrastDetails, $efoUrisToIdOrgUris ) = @_;

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

			my $efoUris = $contrastDetails->get_efo_uris( $type, $value );

			if( defined( $efoUris ) ) {
				foreach my $efoUri ( @{ $efoUris } ) {
					my $idOrgUri = $efoUrisToIdOrgUris->{ $efoUri };
					push @{ $biosamplesArray }, $idOrgUri;
				}
			}
			else {
				push @{ $biosamplesArray }, $value;
			}
		}
	}

	return $biosamplesArray;
}


=item get_factor_uris

Given a comparison (AtlasContrastDetails object) and a hash mapping EFO URIs to
http://identifiers.org URIs -- return a hash mapping the assay group types
(test and reference) to property value http://identifiers.org URIs.

=cut

sub get_factor_uris {

	my ( $contrastDetails, $efoUrisToIdOrgUris ) = @_;

	my $factors = $contrastDetails->get_factors;

	my $factorUris = {};
	
	# Go through the assay groups.
	foreach my $assayGroupType ( "test", "reference" ) {
		
		# Get the factors for this assay group.
		my $agFactors = $factors->{ $assayGroupType };
		
		# Go through the factor types.
		foreach my $factorType ( keys %{ $agFactors } ) {
			
			my $value = $agFactors->{ $factorType };
			
			# Get the Zoomaged URI for the factor value.
			my $valueEfoUris = $contrastDetails->get_efo_uris( $factorType, $value );
			
			# If there is one, try and get an http://identifiers.org URI for
			# it.
			my $idOrgValueUri;
			if( defined( $valueEfoUris ) ) {
				foreach my $valueEfoUri ( @{ $valueEfoUris } ) {
					$idOrgValueUri = $efoUrisToIdOrgUris->{ $valueEfoUri };
				}
			}
			# Otherwise just use the free text value.
			else {
				$idOrgValueUri = $value;
			}

			$factorUris->{ $factorType }->{ $assayGroupType } = $idOrgValueUri;
		}
	}
	
	my $factorUrisForJson = {};

	my $factorCounter = 1;
	foreach my $factorType ( keys %{ $factorUris } ) {

		my $testUri = $factorUris->{ $factorType }->{ "test" };
		my $refUri = $factorUris->{ $factorType }->{ "reference" };
		
		$testUri //= "null";
		$refUri //= "null";

		unless( $testUri eq $refUri ) {
			
			my $testKey = "test_group_factor_" . $factorCounter;
			my $refKey = "reference_group_factor_" . $factorCounter;
			
			# Change nulls to undefs so that they come out correctly in the JSON.
			if( $testUri eq "null" ) {
				$testUri = undef;
			}
			if( $refUri eq "null" ) {
				$refUri = undef;
			}

			$factorUrisForJson->{ $testKey } = $testUri;
			$factorUrisForJson->{ $refKey } = $refUri;

			$factorCounter++;
		}
	}
			
	# Return the factor URIs ready to go in the JSON
	# report.
	return $factorUrisForJson;
}


=item get_de_method_description

Given an experiment accession and an experiment type, return the description of
the differential expression statistics.

=cut

sub get_de_method_description {

	my ( $expAcc, $expType ) = @_;

 	my $platform = "";
	if( $expType =~ /microarray/i ) { 
		$platform = "microarray"; 
	}
	elsif( $expType =~ /rnaseq/i ) { 
		$platform = "rna-seq"; 
	}
	else {
		$logger->logdie( "Unknown experiment type: $expType" );
	}

	# Get the Atlas production directory.
	my $atlasProdDir = $ENV{ "ATLAS_PROD" };

	# Create the analysis methods file name.
	my $analysisMethodsFilename = $expAcc . "-analysis-methods.tsv";

	# Create the path analysis methods file.
	my $analysisMethodsPath = File::Spec->catfile(
		$atlasProdDir,
		"analysis",
		"differential",
		$platform,
		"experiments",
		$expAcc,
		$analysisMethodsFilename
	);

	open( my $fh, "<", $analysisMethodsPath )
		or $logger->logdie( "Cannot open $analysisMethodsPath to get differential expression statistics method" );

	while( defined( my $line = <$fh> ) ) {
	
		chomp $line;
		
		my @splitLine = split "\t", $line;
		
		if( $splitLine[ 0 ] =~ /differential expression/i ) {
		
			( my $description = $splitLine[ 1 ] ) =~ s/<.+?>//g;
			
			return $description;
		}
	}

	# If we're still here, die because we haven't returned the description.
	$logger->logdie( "Did not find differential expression statistics method description for $expAcc" );
}


=item get_schema_version

Returns the schema version required to validate.

=cut

sub get_schema_version {
	return 1.1;
}

=back

=AUTHOR Maria Keays <mkeays@ebi.ac.uk>

