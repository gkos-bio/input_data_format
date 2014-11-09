# Purpose of this document
The aim of this document is to centralize answers to key questions about the CTTV evidence string JSON format. It is intended to be read by individuals working on preparing their data to fit this model.

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  

- ['Evidence code' questions](#evidence-code-questions)
    - [1. Which evidence codes should I use?](#1-which-evidence-codes-should-i-use)
- ['Creating evidence string JSONs' questions](#creating-evidence-string-jsons-questions)
    - [1. Which fields are mandatory in the JSON schema?](#1-which-fields-are-mandatory-in-the-json-schema)
    - [2. Should I use ‘null’ when a field is not mandatory?](#2-should-i-use-‘null’-when-a-field-is-not-mandatory)
    - [3. Where can I see an example of the type of data I am trying to fit?](#3-where-can-i-see-an-example-of-the-type-of-data-i-am-trying-to-fit)
    - [4. Do I provide my data as a JSON data service or or as one large array?](#4-do-i-provide-my-data-as-a-json-data-service-or-or-as-one-large-array)
    - [5. How do I validate the data I am providing?](#5-how-do-i-validate-the-data-i-am-providing)
    - [6. Do you have a package I can use to write out CTTV-JSON files?](#6-do-you-have-a-package-i-can-use-to-write-out-cttv-json-files)
    - [7. What is the 'unique_association_fields' codeblock in the JSON?](#7-what-is-the-unique_association_fields-codeblock-in-the-json)
    - [8. When do I use an 'evidence chain'?](#8-when-do-i-use-an-evidence-chain)
    - [9. {evidence}{association_score} is a required field. But what if my dataset doesn't estimate this?](#9-evidenceassociation_score-is-a-required-field-but-what-if-my-dataset-doesnt-estimate-this)
- [Miriam registry questions](#miriam-registry-questions)
    - [1. What is identifiers.org/Miriam registry and what is its relevance to the CTTV platform?](#1-what-is-identifiersorgmiriam-registry-and-what-is-its-relevance-to-the-cttv-platform)
    - [2. When do I use the “http:// identifiers.org” URI prefix in the JSON?](#2-when-do-i-use-the-“http-identifiersorg”-uri-prefix-in-the-json)
    - [3. http:// identifiers.org/cttv.target and http:// identifiers.org/cttv.activity URIs don't exist in MIRIAM yet. Which specific CTTV URIs can I use?](#3-http-identifiersorgcttvtarget-and-http-identifiersorgcttvactivity-uris-dont-exist-in-miriam-yet-which-specific-cttv-uris-can-i-use)
- [Experimental factors ontology (EFO) questions](#experimental-factors-ontology-efo-questions)
    - [1. What if my disease term does not currently map to EFO?](#1-what-if-my-disease-term-does-not-currently-map-to-efo)
    - [2. Where can I get more information about how to map terms to EFO?](#2-where-can-i-get-more-information-about-how-to-map-terms-to-efo)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## 'Evidence code' questions
#### 1. Which evidence codes should I use?
- We are using the **[Evidence Codes Ontology](http://bioportal.bioontology.org/ontologies/ECO).**
- Please provide evidence codes using this URI syntax: **"http://identifiers.org/eco/ECO:nnnnnnn"**.
- To see which evidence codes are assigned to your project, **[please click here](../json_schema/project_tracker.md)**
- If you need to use additional evidence codes to the ones above, please make sure you **[update this github markdown document](../json_schema/project_tracker.md)** with your new codes
- For requesting new evidence codes, please contact **jamesmalone, cc samiulxhasan**

## 'Creating evidence string JSONs' questions

#### 1. Which fields are mandatory in the JSON schema?
Please look through the [CTTV JSON schema](../json_schema/evidence_string_schema.json) for fields marked as **"required" : true**. The only field that is not set as "required" : true is [**'unique_association_fields'**](#7-how-can-i-uniquely-identify-a-“target-disease”-association-json-in-my-json-array).

#### 2. Should I use ‘null’ when a field is not mandatory?
No. You don't need to create these.

#### 3. Where can I see an example of the type of data I am trying to fit?
A list of examples can be [browsed here](../examples)

#### 4. Do I provide my data as a JSON data service or or as one large array?
For now, please create a JSON array (one large file) and provide us with the URL to download this. Please **[update this github markdown document](../json_schema/project_tracker.md)** with your download URL. Once a CTTV production environment is in place, we will aim to establish a common fileshare for all groups to upload their JSON files. 

#### 5. How do I validate the data I am providing?

> 
- You can do this using an **online validator** like [JSON schema lint validator](http://jsonschemalint.com/):
	- Open [JSON schema lint validator](http://jsonschemalint.com/) in your web browser
	- Copy the [CTTV JSON schema](https://github.com/CTTV/input_data_format/blob/master/json_schema/evidence_string_schema.json) into the 'JSON Schema' box
	- Copy the [example ChEMBL JSON instance](https://github.com/CTTV/input_data_format/blob/master/examples/cttv0008_chembl/example.json) into the 'JSON' box
	- Both boxes should light up green if all has worked!
	- Try changing some values in the 'JSON' box and see if the schema still validates it!

> 
- You can do this **locally** using [our python script](../scripts/cttv0001_core_db/json_schema_validator)

> 
- - The [JSON processor jq](http://stedolan.github.io/jq/) is also powerful.

#### 6. Do you have a package I can use to write out CTTV-JSON files?
This is in development. You can [pull the latest version from here](../packages)

#### 7. What is the 'unique_association_fields' codeblock in the JSON?
We need to know whether a given "target-disease" association can be uniquely identified in your database. This is an important requirement as we will need to track whether a unique "target-disease" association has changed properties (e.g. p-values) between release cycles. The **'unique_association_fields'** is, therefore, implemented in the schema to capture an array of key:value pairs in your data that can help with this. If you have an array of values, please provide these **as a comma-separated array**. This will be different between different data providers so please use a set of keys that is **specific to your database and that is consistent between release cycles**. Here are some examples:

```javascript
ChEMBL:

    "unique_association_fields": {
        "chembl_molecules": "http://identifiers.org/chembl.compound/CHEMBL2",
        "chembl_targets": "http://identifiers.org/uniprot/P25100,http://identifiers.org/uniprot/P35348,http://identifiers.org/uniprot/P35368",
        "atc_classification": "http://identifiers.org/atc/C"
    }

ArrayAtlas:

    "unique_association_fields": {
        "geneID": "http://identifiers.org/ensembl/ENSG00000127720",
        "study_id": "http://identifiers.org/gxa.expt/E-MEXP-3628",
        "comparison_name": "'osteosarcoma' vs 'normal'"
    }

Reactome:

    "unique_association_fields": {
        "biological_subjects": "http://identifiers.org/uniprot/P50443",
        "reactome_id": "http://identifiers.org/reactome/REACT_267687.1",
        "biological_objects": "http://identifiers.org/orphanet/Orphanet_93298"
    }

```



#### 8. When do I use an 'evidence chain'?
You use this when there are >1 independent analytical steps used to associate a target with a disease. There are 2 examples you can look at:

> 
- [biological target to disease association via drug - ](../examples/cttv0008_chembl) There are 2 independent analyses in this that has resulted in a chain: 1) **Experimental analysis** carried out to associate a protein/protein complex target to a drug, 2) **Clinical analysis** carried out to associate the drug to its effect in disease

> 
- [gene to disease association via snp - ](../examples/cttv0018_ibd_gwas) There are 2 independent analyses in this that has resulted in a chain: 1) **Computational analysis** carried out to associate a gene target to its nearest nucleotide polymorphism, 2) **Genetics analysis** carried out to associate the nucleotide polymorphism to its effect in disease

#### 9. {evidence}{association_score} is a required field. But what if my dataset doesn't estimate this?

**Probability**:
This is an estimate of the confidence of an assertion (as a probability) in the range 0-1. It is expected that this number will be specific to pipeline projects. If you do provide this value, please also indicate the method used to calculate it using the **{probability}{method}** field (currently free text). The 'probability' itself is a relative value within a data type and is not directly comparable between data types. The following is an example scenario where one could calculate this:

> 
- **Curator uses their own numerical scores (e.g. in the range 1-10) to indicate strength of evidence:** Please normalize the values to the range 0-1. Please check with your curation group whether this is possible.

> 
- **If the above does not apply,** please provide a **'null'** value to indicate to us that such a calculation does not apply to your data.


The methods for scoring will inevitably evolve.

**Pvalue**:

> 
Same rules as for probability above.

**Examples**:
```javascript
GWAS study:

    "association_score": {
        "probability": {
            "value": 0.0202556065553751,
            "method": "Please describe the method"
        },
        "pvalue": {
            "value": 1.316e-44,
            "method": "calculated from GWAS study"
        }
    }
    
ArrayAtlas study:

    "association_score": {
        "probability": {
            "value": null,
            "method": null
        },
        "pvalue": {
            "value": 0.0000418,
            "method": "pvalue from expression array comparison study"
        }
    }

```

## Miriam registry questions

#### 1. What is identifiers.org/Miriam registry and what is its relevance to the CTTV platform?
[Link to MIRIAM registry](http://www.ebi.ac.uk/miriam/main/collections/): This is maintained by the [Biomodels group](http://www.ebi.ac.uk/biomodels-main/) at the EBI.

It is a registry that provides a URI to URL mapping service (through identifiers.org). For example:

```javascript
example URI = "http://identifiers.org/eco/ECO:0001113"

URLs resolved by identifiers.org = 
"http://www.ebi.ac.uk/ontology-lookup/?termId=ECO%3A0001113",
"http://purl.bioontology.org/ontology/ECO/ECO%3A0001113"
```

By using identifer.org URIs, we can point the CTTV web application to pre-resolved URLs.

#### 2. When do I use the “http:// identifiers.org” URI prefix in the JSON?
- Please use the **'http://identifiers.org/namespace'** URI prefix whenever you are referring to an identifiable biological entity.
- Examples: **http://identifiers.org/uniprot/P25100, http://identifiers.org/eco/ECO:0000360, http://identifiers.org/chembl.compound/CHEMBL2**
- Check the [miriam registry](http://www.ebi.ac.uk/miriam/main/collections/) to check if it exists.
- These are typically used as **biological_subject** or **biological_object** resources but can be used elsewhere too (e.g. within the **'experiment_specific'** codeblock).
- When referring to cttv-specific resources, please use **'http://identifiers.org'** URI prefix as well. For example **'http://identifiers.org/cttv.target/gene'**. We will request allocation of the cttv URI namespace to the MIRIAM registry.

#### 3. http:// identifiers.org/cttv.target and http:// identifiers.org/cttv.activity URIs don't exist in MIRIAM yet. Which specific CTTV URIs can I use?
- You can browse the list of the URIs [here](../json_schema/cttv_uris_namespaces.md). These correspond to the terms in the  [CTTV core ontology](../ontology/cttv_core.owl).
- These are the **'biological_subject'** fields in the JSON where this is used ([full example](../examples/cttv0008_chembl)):
```javascript
        "properties": {
            "target_type": "http://identifiers.org/cttv.target/protein_complex_heteropolymer",
            "activity": "http://identifiers.org/cttv.activity/drug_negative_modulator"
        }
```

## Experimental factors ontology (EFO) questions
#### 1. What if my disease term does not currently map to EFO?
If you have a disease term which isn't yet mapped to EFO, please:

1. Use the efo URI **'http://identifiers.org/efo/0000000'** in the JSON **{biological_object}{about}[0]** field.
1. Provide your unmapped term under '**{biological_object}{properties}{experimental_evidence_specific}{unmapped_disease_term}**'
    
```javascript
    "biological_object": {
        "about": [
            "http://identifiers.org/efo/0000000"
        ],
        "properties": {
            "experimental_evidence_specific": {
                "unmapped_disease_term" : "disease X which is not in EFO"
            }
        }
    }
```

#### 2. Where can I get more information about how to map terms to EFO?
See [jamesmalone and tonyburdett's Confluence WIKI](https://www.ebi.ac.uk/seqdb/confluence/display/CTTV/Ontology+Annotation) (Only for those with CTTV Confluence accounts)








