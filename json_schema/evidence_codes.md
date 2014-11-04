## Evidence codes used by different CTTV pipeline projects

Tech leads, please update this document if you start using any new evidence codes.

### 1. Rare2Common (CTTV005)
**Tech lead: jamesmalone**

Evidence codes: NA since this is an ontology project.

Additional information:

|Download URL|Fields used to uniquely identify target-disease associations|
|------------|------------------------------------------------------------|
|http://     |

### 2. Networks - Reactome (CTTV006)
**Tech lead: fabregat**

Evidence codes:

Evidence code | Label | Description
--------|--------|--------
obo:ECO_0000205 |curator inference|An evidence type that is based on conclusions drawn by a curator.
obo:ECO_0000001 | inference from background scientific knowledge | A curator inference that links the current annotation to a different evidence-based annotation via background knowledge of the curator.

Additional information:

|Download URL|Fields used to uniquely identify target-disease associations|
|------------|------------------------------------------------------------|
|http://     |

### 3. Networks - Intact (CTTV006)
**Tech lead: maitesin**

Evidence codes:

Evidence code | Label | Description
--------|--------|--------
obo:ECO_0000205 |curator inference|An evidence type that is based on conclusions drawn by a curator.
obo:ECO_0000001 | inference from background scientific knowledge | A curator inference that links the current annotation to a different evidence-based annotation via background knowledge of the curator.

Additional information:

|Download URL|Fields used to uniquely identify target-disease associations|
|------------|------------------------------------------------------------|
|http://     |

### 4. ChEMBL (CTTV008)
**Tech lead: mark-davies**

Evidence codes:

Evidence code | Label | Description
--------|--------|--------
obo:ECO_0000205 |curator inference|An evidence type that is based on conclusions drawn by a curator.
obo:ECO_0000001 | inference from background scientific knowledge | A curator inference that links the current annotation to a different evidence-based annotation via background knowledge of the curator.|
obo:ECO_0000360 | biological target-disease association via drug |  - |

Additional information:

|Download URL|Fields used to uniquely identify target-disease associations|
|------------|------------------------------------------------------------|
|http://     |

### 5. GWAS catalog (CTTV009)
**Tech lead: tburdett**

Evidence codes:

Evidence code | Label | Description
--------|--------|--------
obo:ECO_0000177 | genomic context evidence | An evidence type that is based on the location of a gene, which gives rise to a gene product, within the genome.|
obo:ECO_0001113 | point mutation evidence | |
obo:ECO_0000205 | curator inference | An evidence type that is based on conclusions drawn by a curator.|
obo:ECO_0000053 | computational combinatorial evidence | A type of combinatorial analysis where data are combined and evaluated by an algorithm.|

Additional information:

|Download URL|Fields used to uniquely identify target-disease associations|
|------------|------------------------------------------------------------|
|http://     |

### 6. Tissue specificity (CTTV010)
**Tech lead: mkeays**

Evidence codes:

Evidence code | Label | Description
--------|--------|--------
obo:ECO_0000356 | differential gene expression from microarray experiment | A gene is found to show statistically significant increase or decrease in expression in some condition relative to an appropriate control condition, when expression levels are assayed using a microarray platform.|
obo:ECO_0000357 | differential gene expression from RNA-seq experiment | A gene is found to show statistically significant increase or decrease in expression in some condition relative to an appropriate control condition, when expression levels are assayed using an RNA sequencing platform.|
obo:ECO_0000358 | differential geneset expression from microarray experiment | Gene set enrichment analysis (GSEA) shows that a functional classification (e.g. reaction pathway, Gene Ontology term, etc) has statistically significant over-representation in a group of genes showing differential expression when assayed using a microarray platform.|
obo:ECO_0000359 | differential geneset expression from RNA-seq experiment | Gene set enrichment analysis (GSEA) shows that a functional classification (e.g. reaction pathway, Gene Ontology term, etc) has statistically significant over-representation in a group of genes showing differential expression when assayed using an RNA sequencing platform.|

Additional information:

|Download URL|Fields used to uniquely identify target-disease associations|
|------------|------------------------------------------------------------|
|http://www.ebi.ac.uk/~mkeays/cttv/expression_atlas_cttv_evidence_strings.json|1. Entrez gene: **{biological_subject}{about}[0]**, 2. studyID: **{provenance}{experimental_evidence_specific}{study_id}**, 3. Comparison name: **{provenance}{experimental_evidence_specific}{comparison_name}**|

### 7. Uniprot (CTTV011)
**Tech lead: eddturner**

Evidence codes:

Evidence code | Label | Description
--------|--------|--------
obo:ECO_0000205 |curator inference|An evidence type that is based on conclusions drawn by a curator.
obo:ECO_0000001 | inference from background scientific knowledge | A curator inference that links the current annotation to a different evidence-based annotation via background knowledge of the curator.|

Additional information:

|Download URL|Fields used to uniquely identify target-disease associations|
|------------|------------------------------------------------------------|
|http://     |

### 8. Variation (CTTV012)
**Tech lead: javild**

Evidence codes:

Evidence code | Label | Description
--------|--------|--------
obo:ECO_0000177 | genomic context evidence | An evidence type that is based on the location of a gene, which gives rise to a gene product, within the genome.|
obo:ECO_0001113 | point mutation evidence | |
obo:ECO_0000205 | curator inference | An evidence type that is based on conclusions drawn by a curator.|
obo:ECO_0000053 | computational combinatorial evidence | A type of combinatorial analysis where data are combined and evaluated by an algorithm.|

Additional information:

|Download URL|Fields used to uniquely identify target-disease associations|
|------------|------------------------------------------------------------|
|http://     |

### 9. IBD_GWAS (CTTV018)
**Tech lead: slwest**

Evidence codes:

Evidence code | Label | Description
--------|--------|--------
obo:ECO_0000177 | genomic context evidence | An evidence type that is based on the location of a gene, which gives rise to a gene product, within the genome.|
obo:ECO_0001113 | point mutation evidence | |
obo:ECO_0000205 | curator inference | An evidence type that is based on conclusions drawn by a curator.|
obo:ECO_0000053 | computational combinatorial evidence | A type of combinatorial analysis where data are combined and evaluated by an algorithm.|

Additional information:

|Download URL|Fields used to uniquely identify target-disease associations|
|------------|------------------------------------------------------------|
|http://     |
