

## An evidence string example: Association between a protein and a disease via curated Reactome pathway evidence

*Bijay Jassal, Samiul Hasan*

###Reactome disease annotation: [Defective SLC26A2 causes chondrodysplasias](http://www.reactome.org/PathwayBrowser/#REACT_267687.1)

The **SLC26A1 and 2 genes** encode sulfate transporter proteins that facilitate sulfate uptake into cells, critical in cartilage for sulfation of proteoglycans and extracellular matrix organization. **Defects in SLC26A2** result in impaired SO4(2-) transport leading to insufficient sulfation of cartilage proteoglycans. Defective SLC26A2 is implicated in the pathogenesis of a spectrum of autosomal recessive human chondrodysplasias. Severity of symptoms range from **mild (achondrogenesis type 1B (ACG-1B; MIM:600972)** to **intermediate (atelosteogenesis type II; MIM256050)** to **severe (achondrogenesis type 1B; MIM:600972)** (Superti-Furga et al. 1996, Dwyer et al. 2010).

The SLC26A1 and 2 genes encode proteins that facilitate sulfate uptake into cells. SLC26A2 is ubiquitously expressed and encodes a sulfate transporter (Diastrophic dysplasia protein, DTD, DTDST) (Hastbacka et al. 1994). This transporter provides sulfate for proteoglycan sulfation which is needed for cartilage development. Defects in SLC26A2 result in **impaired SO4(2-) transport** leading to insufficient sulfation of cartilage proteoglycans. The severest form of achondrodysplasia, **achondrogenesis type 1B (ACG-1B; MIM:600972)**, is caused by **SLC26A2 mutations such as G255E, A585Vfs*6, L483P, R178X and N425D** (Hastbacka et al. 1994, Karniski 2001, Superti-Furga et al. 1996).

###From above Reactome annotation, CTTV evidence string needs to capture:

**Note:** This example is only for the disease outcome *achondrogenesis type 1B*. One would need to create a separate evidence instance for *atelosteogenesis type II*.

1. **Target:** The protein SLC26A2
3. **Evidence:** SLC26A2 having 5 disease-associated mutations (for achondrogenesis type 1B)
4. **Disease:** The disease outcome 'achondrogenesis type 1B (ACG-1B; MIM:600972)' [A child term of chondrodysplasias]

### Resources used in this example:

**Note:** All namespaces, other than "[miriam:cttv:, miriam:cttvexp](../../json_schema/cttv_uris_namespaces.md):" already exist in [identifers.org / MIRIAM registry](http://www.ebi.ac.uk/miriam/main/collections/). We will request addition of the cttv-specific terms to MIRIAM registry once the CTTV platform is in beta development.

- **miriam:cttvexp:protein**:
	- 	**miriam:uniprot:P50443** (SLC26A2)

- **miriam:cttvexp:loss_of_function** because normal biological activity of SLC26A2 is impaired due to mutations.

- [**Evidence codes:**](../../json_schema/evidence_codes.md)
	1. **miriam:obo:ECO_0000205** Evidence code for: curator inference


- [**Provenance types:**](../../json_schema/cttv_uris_namespaces.md) Provenance types are literature, expert and database since this is curated literature evidence from the Reactome database.

- **EFO terms:**
	1. **miriam:obo:ECO_0001113** Evidence code for: point mutation evidence
	2. **miriam:orphanet:Orphanet_93298:** EFO term for 'achondrogenesis type 1B (ACG-1B; MIM:600972)'

### Reactome entry points: http://www.reactome.org/PathwayBrowser/#REACT_267687.1
