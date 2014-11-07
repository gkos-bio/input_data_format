## An example of an evidence string that links "gene" to its disease indication via genetics evidence.

### Evidence string triple:

1. **Target:** An Ensembl gene
3. **Evidence chain:**
	- **Link 0:** a SNP (rs9802421) predicted to be associated between the gene and Irritable Bowel Disease
	- **Link 1:** IBD
4. **Disease:** IBD

### Resources used in this example:

**Note:** All URIs, other than "[http://identifiers.org/cttv.target, http://identifiers.org/cttv.activity](../../json_schema/cttv_uris_namespaces.md):" already exist in [identifers.org / MIRIAM registry](http://www.ebi.ac.uk/miriam/main/collections/). We will request addition of the cttv URIs to MIRIAM registry.

- **http://identifiers.org/cttv.target/gene_allele** involving the following gene:
	1. 	**http://identifiers.org/ensembl/ENSG9023124**
		2. 	**http://identifiers.org/pfam/PF00719** Affected PFAM domain
		3. 	**http://identifiers.org/prosite/PS50309** Affected prosite domain
		4. 	**http://identifiers.org/supfam/SSF47186** Affected superfamily domain

- **http://identifiers.org/cttv.activity/predicted_damaging** because sift prediction is that the mutation is uncertain / tolerated(0.39).

- [**Evidence codes:**](../../json_schema/project_tracker.md)
	1. 	**http://identifiers.org/obo:ECO_0000177/** genomic context evidence (An evidence type that is based on the location of a gene, which gives rise to a gene product, within the genome.)
	1. **http://identifiers.org/obo/ECO_0001113** (point mutation evidence)	
	1. **http://identifiers.org/obo/ECO_0000053** computational combinatorial evidence (A type of combinatorial analysis where data are combined and evaluated by an algorithm.)

- [**Provenance types:**](../../json_schema/cttv_uris_namespaces.md) Provenance types are literature, expert and database since this is (assumed) published GWAS data in the GWAS catalog
	
- **http://identifiers.org/dbsnp/rs9802421** The dbSNP URI for the predicted SNP

- The disease indication:

	1. **http://identifiers.org/efo/EFO_0003767** The EFO URI for Irritable Bowel Disease
