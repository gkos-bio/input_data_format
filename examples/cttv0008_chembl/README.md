## An example of an evidence string that links "biological target" to its disease indication via drug.

### Evidence string triple:

1. **Target:** A heteropolymeric protein complex (alpha adrenergic receptor complex)
3. **Evidence chain:**
	- **Link 0:** The drug **Prazosin**, which exhibits activity against alpha1 adrenergic receptors
	- **Link 1:** The marketed disease indication of **Prazosin**, Cardiovascular disease
4. **Disease:** Cardiovascular disease

### Resources used in this example:

**Note:** All namespaces, other than "[miriam:cttv:, miriam:cttvexp](../../json_schema/cttv_uris_namespaces.md):" already exist in [identifers.org / MIRIAM registry](http://www.ebi.ac.uk/miriam/main/collections/). We will request addition of the cttv-specific terms to MIRIAM registry once the CTTV platform is in beta development.

- **miriam:cttvexp:protein_complex_heteropolymer** comprised of 3 protein subunits:
	1. 	**miriam:uniprot:P25100**
	1. 	**miriam:uniprot:P35348**
	1. 	**miriam:uniprot:P35368**
- **miriam:cttvexp:down** because the drug is an antagonist

- [**Evidence codes:**](../../json_schema/evidence_codes.md)
	1. **miriam:obo:ECO_0000360** Evidence code for: biological target-disease 
	association via drug
	1. **miriam:obo:ECO_0000205** Evidence code for: curator inference

- [**Provenance types:**](../../json_schema/cttv_uris_namespaces.md) Provenance types are literature, expert and database since this is curated literature evidence from the ChEMBL database.
	
- **miriam:chembl:CHEMBL2:** The ChEMBL resource ID for Prazosin

- The disease indication:

	1. **miriam:efo:EFO_0000537** The EFO resource ID for hypertension, the marketed drug
	indication (I did a Google search for this)
	1. **miriam:obo:UBERON_0004535** The EFO resource ID for cardiovascular system
	
### ChEMBL entry points:
- **ChEMBL molecule information:** https://www.ebi.ac.uk/chembl/compound/inspect/CHEMBL2
- **ChEMBL target information:** https://www.ebi.ac.uk/chembl/target/inspect/CHEMBL2094251