## An example of a TD-association that links "biological target" to its disease indication via drug.

### Evidence string triple:

1. **Target:** A heteropolymeric protein complex (alpha adrenergic receptor complex)
3. **Evidence chain:**
	- **Link 0:** The drug **Prazosin**, which exhibits activity against alpha1 adrenergic receptors
	- **Link 1:** The marketed disease indication of **Prazosin**, Cardiovascular disease
4. **Disease:** Cardiovascular disease

### Resources used in this example:

**Note:** All URIs, other than "[http://identifiers.org/cttv.target, http://identifiers.org/cttv.activity](../../json_schema/cttv_uris_namespaces.md):" already exist in [identifers.org / MIRIAM registry](http://www.ebi.ac.uk/miriam/main/collections/). We will request addition of the cttv URIs to MIRIAM registry.

- **http://identifiers.org/cttv.target/protein_complex_heteropolymer** comprised of 3 protein subunits:
	1. 	**http://identifiers.org/uniprot/P25100**
	1. 	**http://identifiers.org/uniprot/P35348**
	1. 	**http://identifiers.org/uniprot/P35368**
- **http://identifiers.org/cttv.activity/down** because the drug is an antagonist

- [**Evidence codes:**](../../json_schema/project_tracker.md)
	1. **http://identifiers.org/eco/ECO:0000360** Evidence code for: biological target-disease 
	association via drug
	1. **http://identifiers.org/eco/ECO:0000205** Evidence code for: curator inference

- [**Provenance types:**](../../json_schema/cttv_uris_namespaces.md) Provenance types are literature, expert and database since this is curated literature evidence from the ChEMBL database.
	
- **http://identifiers.org/chembl.compound/CHEMBL2** The ChEMBL URI for Prazosin

- The disease indication:

	1. **http://identifiers.org/efo/0000537** The EFO URI for hypertension, the marketed drug
	indication (I did a Google search for this)
	1. **http://identifiers.org/uberon/UBERON:0004535** The EFO URI for cardiovascular system
	
### ChEMBL entry points:
- **ChEMBL molecule information:** http://identifiers.org/chembl.compound/CHEMBL2
- **ChEMBL target information:** http://identifiers.org/chembl.target/CHEMBL2094251
