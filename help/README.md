# Answers to Common Questions

[TOC]

## Preparation
#### How do I prepare for this hackathon (Is it really a hackathon)?
#### What is expected for 8th December integration day?

#### Who is responsible for what?
Sam will explain the "Responsibility assignment matrix"

## Evidence string questions

#### Which fields are mandatory in the JSON schema?
Please look through the [CTTV JSON schema](../json_schema/evidence_string_schema.json) for fields marked as **"required" : true**

#### Should I use ‘null’ when a field is not mandatory?
No. Just avoid including these in the JSON.

#### Which evidence codes should I use?
- We are using the [Evidence Codes Ontology](http://bioportal.bioontology.org/ontologies/ECO).
- Please provide evidence codes using this syntax: "miriam:obo:ECO_nnnnnnn".
- To see which evidence codes are assigned to your project, [please click here](../json_schema/evidence_codes.md)
- If you need to use additional evidence codes to the ones above, please make sure you [update this document](../json_schema/evidence_codes.md) with your new codes
- For requesting new evidence codes, please contact jamesmalone, cc samiulxhasan

#### Where can I see an example of the type of data I am trying to fit?
A list of examples can be [browsed here](../examples)

#### When do I use the “miriam” namespace prefix in the JSON?
- Please use the 'miriam:' prefix whenever you are referring to an identifiable biological entity that has a valid miriam namespace.
- Examples: **miriam:uniprot:P25100, miriam:obo:ECO_0000360, miriam:chembl:CHEMBL2**
- Check the [miriam registry](http://www.ebi.ac.uk/miriam/main/collections/) to check if it exists.
- These are typically used as biological_subject or biological_object resources but can be used elsewhere too.
- When referring to cttv-specific resources, please use 'miriam:' prefix as well. For example **'miriam:cttvexp:gene'**. We will request addition of the CTTV specific resources to the MIRIAM registry once the software is in beta release.

#### What are all the “miriam:cttv:, miriam:cttvexp:” specific resources I can use?
- You can browse the list of these terms [here](../json_schema/cttv_uris_namespaces.md).
- These terms are mapped to the [CTTV core ontology](../ontology/cttv_core.owl).

#### Do I provide my data as a JSON data service or or as one large array?
#### How do I validate the data I am providing?
#### Do you have a package I can use to write out CTTV-JSON files?
#### How can I uniquely identify a “target-disease” association in my JSON?
outside of json schema. what combination of fields make this json distinct from another?

## Experimental factors ontology (EFO) questions
#### What if my disease term does not currently map to EFO?
If you have a disease term which isn't yet mapped to EFO, please:
1. Use the efo code 'miriam:efo:EFO_0000000' in the JSON biological_object
1. Provide your unmapped term under {biological_object}{properties}{experimental_evidence_specific}{unmapped_disease_term}
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








