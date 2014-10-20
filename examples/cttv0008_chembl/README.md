An example of an evidence string that links "biological target" to its disease indication via drug.
==============

Evidence string main components:
--------------
- Subject: A protein complex heteropolymer
- Intermediate link: Prazosin (An adrenergic alpha1-antagonist)
- Object: Cardiovascular disease

Resources used in this example:
--------------
- cttvexp:protein_complex_heteropolymer comprised of 3 protein subunits:
	"uniprot:P25100"
	"uniprot:P35348"
	"uniprot:P35368"
- cttvexp:down #because the drug is an antagonist

- Evidence codes:
	"obo:ECO_0000360" #Evidence code for: biological target-disease 
	association via drug
	"obo:ECO_0000205" #Evidence code for: curator inference

- Provenance types:
	provenance types are literature, expert and database since this is curated
	literature evidence from the ChEMBL database.
	
- ChEMBL small molecule link:
	chembl:CHEMBL2 #The ChEMBL resource ID for Prazosin

- The disease indication:
	efo:EFO_0000537 #The EFO resource ID for hypertension, the marketed drug
	indication (Did a Google search for this)
	obo:UBERON_0004535 #The EFO resource ID for cardiovascular system
	
ChEMBL entry points:
--------------
- ChEMBL molecule information: https://www.ebi.ac.uk/chembl/compound/inspect/CHEMBL2
- ChEMBL target information: https://www.ebi.ac.uk/chembl/target/inspect/CHEMBL2094251