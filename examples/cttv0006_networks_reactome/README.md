

## An evidence string example: Association between a protein complex and a disease via curated Reactome pathway evidence

###Reactome disease annotation: [Defective SLC26A2 causes chondrodysplasias](http://www.reactome.org/PathwayBrowser/#REACT_267687.1)

*Bijay Jassal*

The **SLC26A1 and 2 genes** encode sulfate transporter proteins that facilitate sulfate uptake into cells, critical in cartilage for sulfation of proteoglycans and extracellular matrix organization. **Defects in SLC26A2** result in impaired SO4(2-) transport leading to insufficient sulfation of cartilage proteoglycans. Defective SLC26A2 is implicated in the pathogenesis of a spectrum of autosomal recessive human chondrodysplasias. Severity of symptoms range from **mild (achondrogenesis type 1B (ACG-1B; MIM:600972)** to **intermediate (atelosteogenesis type II; MIM256050)** to **severe (achondrogenesis type 1B; MIM:600972)** (Superti-Furga et al. 1996, Dwyer et al. 2010).

The SLC26A1 and 2 genes encode proteins that facilitate sulfate uptake into cells. SLC26A2 is ubiquitously expressed and encodes a sulfate transporter (Diastrophic dysplasia protein, DTD, DTDST) (Hastbacka et al. 1994). This transporter provides sulfate for proteoglycan sulfation which is needed for cartilage development. Defects in SLC26A2 result in **impaired SO4(2-) transport** leading to insufficient sulfation of cartilage proteoglycans. The severest form of achondrodysplasia, **achondrogenesis type 1B (ACG-1B; MIM:600972)**, is caused by **SLC26A2 mutations such as G255E, A585Vfs*6, L483P, R178X and N425D** (Hastbacka et al. 1994, Karniski 2001, Superti-Furga et al. 1996).

###From above Reactome annotation, CTTV evidence string needs to capture:

*Samiul Hasan*

**Note:**
- This example is only for the disease outcome *achondrogenesis type 1B*. One would need to create a separate evidence instance for *atelosteogenesis type II*.
- In Reactome, sets are treated as proteins (mostly from protein families) that can carry out the same function. In this example, both SLC26A1 and SLC26A2 are a set. However, the mutations occur in SLC26A2 only.


1. **Target:** The protein SLC26A2
3. **Evidence chain:**
	- **Link 0:** SLC26A2 having 5 disease-associated mutations (for achondrogenesis type 1B)
	- **Link 1:** The activity of this impaired protein in a pathway reaction
4. **Disease:** The disease outcome 'achondrogenesis type 1B (ACG-1B; MIM:600972)' [A child term of chondrodysplasias]

### Resources used in this example:

- **cttvexp:protein**:
	- 	**uniprot:P50443** (SLC26A2)

- **cttvexp:loss_of_function** because normal biological activity of SLC26A2 is impaired due to mutations.

- **Evidence codes:**
	1. **obo:ECO_0000205** Evidence code for: curator inference


- **Provenance types:** Provenance types are literature, expert and database since this is curated literature evidence from the Reactome database.

- **EFO terms:**
	1. **obo:ECO_0001113** Evidence code for: point mutation evidence
	2. **orphanet:Orphanet_93298:** EFO term for 'achondrogenesis type 1B (ACG-1B; MIM:600972)'

### Reactome entry points: http://www.reactome.org/PathwayBrowser/#REACT_267687.1
