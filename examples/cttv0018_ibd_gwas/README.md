## An example of an evidence string that links "gene" to its disease indication via genetics evidence.

### Evidence string triple:

1. **Target:** An Ensembl gene
3. **Evidence chain:**
	- **Link 0:** a SNP (dbsnp:rs9802421) predicted to be associated between the gene and Irritable bowel disease (IBD)
	- **Link 1:** IBD
4. **Disease:** IBD

### Resources used in this example:

**Note:** All namespaces, other than "cttv:, cttvexp:" are from [identifers.org / MIRIAM registry](http://www.ebi.ac.uk/miriam/main/collections/)

- **cttvexp:gene_allele** involving the following gene:
	1. 	**ensembl:ENSG9023124**

- **cttvexp:loss_of_function** because a computational analysis predicted loss of function

- **Evidence codes:**
	1. 	**obo:ECO_0000177:** genomic context evidence (An evidence type that is based on the location of a gene, which gives rise to a gene product, within the genome.)
	1. **obo:ECO_0001113** (point mutation evidence)	
	1. **obo:ECO_0000053** computational combinatorial evidence (A type of combinatorial analysis where data are combined and evaluated by an algorithm.)	



- **Provenance types:** Provenance types are literature, expert and database since this is published GWAS data in the GWAS catalog
	
- **dbsnp:rs9802421** The dbSNP resource ID for the predicted SNP

- The disease indication:

	1. **efo:EFO_0000555** The EFO resource ID for IBD
	
### GWAS catalog entry points:
- **Study in GWAS catalog:** http://www.genome.gov/gwastudies/myStudyLink