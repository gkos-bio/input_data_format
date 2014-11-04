## An example of an evidence string that links "gene" to its disease indication via genetics evidence.

### Evidence string triple:

1. **Target:** An Ensembl gene
3. **Evidence chain:**
	- **Link 0:** a SNP (rs9802421) predicted to be associated between the gene and Irritable bowel syndrome
	- **Link 1:** IBD
4. **Disease:** IBD

### Resources used in this example:

**Note:** All namespaces, other than "[miriam:cttv:, miriam:cttvexp](../../json_schema/cttv_uris_namespaces.md):" already exist in [identifers.org / MIRIAM registry](http://www.ebi.ac.uk/miriam/main/collections/). We will request addition of the cttv-specific terms to MIRIAM registry once the CTTV platform is in beta development.

- **miriam:cttvexp:gene_allele** involving the following gene:
	1. 	**miriam:ensembl:ENSG9023124**
		2. 	**miriam:pfam:PF00719** Affected PFAM domain
		3. 	**miriam:prosite:PS50309** Affected prosite domain
		4. 	**miriam:supfam:SSF47186** Affected superfamily domain

- **miriam:cttvexp:predicted_damaging** because sift prediction is that the mutation is uncertain / tolerated(0.39).

- [**Evidence codes:**](../../json_schema/project_tracker.md)
	1. 	**miriam:obo:ECO_0000177:** genomic context evidence (An evidence type that is based on the location of a gene, which gives rise to a gene product, within the genome.)
	1. **miriam:obo:ECO_0001113** (point mutation evidence)	
	1. **miriam:obo:ECO_0000053** computational combinatorial evidence (A type of combinatorial analysis where data are combined and evaluated by an algorithm.)

- [**Provenance types:**](../../json_schema/cttv_uris_namespaces.md) Provenance types are literature, expert and database since this is (assumed) published GWAS data in the GWAS catalog
	
- **miriam:dbsnp:rs9802421** The dbSNP resource ID for the predicted SNP

- The disease indication:

	1. **miriam:efo:EFO_0000555** The EFO resource ID for Irritable bowel syndrome
	
### GWAS catalog entry points:
- **Study in GWAS catalog (mockup example):** http://www.genome.gov/gwastudies/myStudyLink