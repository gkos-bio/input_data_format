# Answers to Common Questions

[TOC]

## Preparation
#### 1. How do I prepare for this hackathon (Is it really a hackathon)?
#### 2. What is expected for 8th December integration day?

#### Who is responsible for what?
Sam will explain the "Responsibility assignment matrix"

## 'Evidence code' questions
#### 1. Which evidence codes should I use?
- We are using the **[Evidence Codes Ontology](http://bioportal.bioontology.org/ontologies/ECO).**
- Please provide evidence codes using this syntax: **"miriam:obo:ECO_nnnnnnn"**.
- To see which evidence codes are assigned to your project, **[please click here](../json_schema/evidence_codes.md)**
- If you need to use additional evidence codes to the ones above, please make sure you **[update this github markdown document](../json_schema/evidence_codes.md)** with your new codes
- For requesting new evidence codes, please contact **jamesmalone, cc samiulxhasan**

## 'Creating evidence string JSONs' questions

#### 1. Which fields are mandatory in the JSON schema?
Please look through the [CTTV JSON schema](../json_schema/evidence_string_schema.json) for fields marked as **"required" : true**

#### 2. Should I use ‘null’ when a field is not mandatory?
No. You don't need to create these.

#### 3. Where can I see an example of the type of data I am trying to fit?
A list of examples can be [browsed here](../examples)

#### 4. Do I provide my data as a JSON data service or or as one large array?
For now, please create a JSON array (one large file) and provide us with the URL to download this. Please **[update this github markdown document](../json_schema/evidence_codes.md)** with your download URL. Once a CTTV production environment is in place, we will aim to establish a common fileshare for all groups to upload their JSON files. 

#### 5. How do I validate the data I am providing?
Working on a JSON validation example today that we can show - using one of:
JSON validator
Create a schema from a JSON file
A lint tool for JSON
A lint tool for JSON Schema
Python JSON Schema validator
Antonio's JSON schema validator

#### 6. Do you have a package I can use to write out CTTV-JSON files?
This is in development. You can [pull the latest version from here](../packages)

#### 7. How can I uniquely identify a “target-disease” association JSON in my JSON array?
This is an important requirement as we will need to track whether a unique "target-disease" association has changed properties between release cycles. Please **[update this github markdown document](../json_schema/evidence_codes.md)**
to tell us which combination of fields in your JSON array makes one JSON distinct from another.

#### 8. When do I use an 'evidence chain'?
You use this when there are >1 independent analytical steps used to associate a target with a disease. There are 2 examples you can look at:

- [biological target to disease association via drug - ](../examples/cttv0008_chembl) There are 2 independent analyses in this that has resulted in a chain: 1) **Experimental analysis** carried out to associate a protein/protein complex target to a drug, 2) **Clinical analysis** carried out to associate the drug to its effect in disease

- [gene to disease association via snp - ](../examples/cttv0018_ibd_gwas) There are 2 independent analyses in this that has resulted in a chain: 1) **Computational analysis** carried out to associate a gene target to its nearest nucleotide polymorphism, 2) **Genetics analysis** carried out to associate the nucleotide polymorphism to its effect in disease

## Miriam registry questions

#### 1. What is the Miriam registry and what is its relevance to the CTTV platform?
[Link to MIRIAM registry](http://www.ebi.ac.uk/miriam/main/collections/): This is maintained by the [Biomodels group](http://www.ebi.ac.uk/biomodels-main/) at the EBI.
It provides a URN to URL mapping service. For example:

```javascript
example URN = "miriam:eco:ECO:0001113"

URLs resolved by Miriam = 
"http://www.ebi.ac.uk/ontology-lookup/?termId=ECO%3A0001113",
"http://purl.bioontology.org/ontology/ECO/ECO%3A0001113"
```

By using URNs recognized by MIRIAM, we can point the CTTV web application to pre-resolved URLs.

#### 2. When do I use the “miriam” namespace prefix in the JSON?
- Please use the **'miriam:'** prefix whenever you are referring to an identifiable biological entity that has a valid miriam namespace.
- Examples: **miriam:uniprot:P25100, miriam:obo:ECO_0000360, miriam:chembl:CHEMBL2**
- Check the [miriam registry](http://www.ebi.ac.uk/miriam/main/collections/) to check if it exists.
- These are typically used as **biological_subject** or **biological_object** resources but can be used elsewhere too (e.g. within the **'experimental_evidence_specific'** codeblock).
- When referring to cttv-specific resources, please use **'miriam:'** prefix as well. For example **'miriam:cttvexp:gene'**. We will request addition of the CTTV specific resources to the MIRIAM registry once the software is in beta release.

#### 3. 'miriam:cttv:' and 'miriam:cttvexp:' URNs don't exist in MIRIAM yet. Which specific CTTV URNs can I use?
- You can browse the list of the URNs [here](../json_schema/cttv_uris_namespaces.md). These correspond to the terms in the  [CTTV core ontology](../ontology/cttv_core.owl).
- These are the **'biological_subject'** fields in the JSON where this is used ([full example](../examples/cttv0008_chembl)):
```javascript
        "properties": {
            "association_context": "miriam:cttvexp:protein_complex_heteropolymer",
            "activity": "miriam:cttvexp:drug_negative_modulator"
        }
```

## Experimental factors ontology (EFO) questions
#### 1. What if my disease term does not currently map to EFO?
If you have a disease term which isn't yet mapped to EFO, please:
1. Use the efo code **'miriam:efo:EFO_0000000'** in the JSON **{biological_object}{about}** field.
1. Provide your unmapped term under '**{biological_object}{properties}{experimental_evidence_specific}{unmapped_disease_term}**'
```javascript
    "biological_object": {
        "about": [
            "miriam:efo:EFO_0000000"
        ],
        "properties": {
            "experimental_evidence_specific": {
                "unmapped_disease_term" : "disease X which is not in EFO"
            }
        }
    }
```








