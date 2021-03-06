# Purpose of this document
This live document is intended to keep track of data/information required of/being used by different CTTV pipeline projects.

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->

**Table of Contents**
  - [1. Rare2Common (CTTV005)](#1-rare2common-cttv005)
  - [2. Networks - Reactome (CTTV006)](#2-networks---reactome-cttv006)
  - [3. Networks - Intact (CTTV006)](#3-networks---intact-cttv006)
  - [4. ChEMBL (CTTV008)](#4-chembl-cttv008)
  - [5. GWAS catalog (CTTV009)](#5-gwas-catalog-cttv009)
  - [6. Tissue specificity (CTTV010)](#6-tissue-specificity-cttv010)
  - [7. Uniprot (CTTV011)](#7-uniprot-cttv011)
  - [8. Variation (CTTV012)](#8-variation-cttv012)
  - [9. IBD_GWAS (CTTV018)](#9-ibd_gwas-cttv018)
  
<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## 1. Rare2Common (CTTV005)
**Tech lead: jamesmalone**

Evidence codes: Doesn't apply

## 2. Networks - Reactome (CTTV006)
**Tech lead: fabregat**

Evidence codes:

Evidence code | Label | Description
--------|--------|--------
http://identifiers.org/eco/ECO:0000205 |curator inference|An evidence type that is based on conclusions drawn by a curator.
http://identifiers.org/eco/ECO:0000001 | inference from background scientific knowledge | A curator inference that links the current annotation to a different evidence-based annotation via background knowledge of the curator.

TD-association structure:

|biological_subject (target) | provenance has evidence chain | biological_object (disease) | example|
|----------------------------|-------------------------------|-----------------------------|--------|
|[child term of protein_evidence as an array](./cttv_uris_namespaces.md)|no|[EFO disease or phenotype as an array](http://www.ebi.ac.uk/efo/)|[click here](../examples/cttv0006_networks_reactome)|

## 3. Networks - Intact (CTTV006)
**Tech lead: maitesin**

Evidence codes:

Evidence code | Label | Description
--------|--------|--------
http://identifiers.org/eco/ECO:0000205 |curator inference|An evidence type that is based on conclusions drawn by a curator.
http://identifiers.org/eco/ECO:0000001 | inference from background scientific knowledge | A curator inference that links the current annotation to a different evidence-based annotation via background knowledge of the curator.

TD-association structure:

|biological_subject (target) | provenance has evidence chain | biological_object (disease) | example|
|----------------------------|-------------------------------|-----------------------------|--------|
|[child term of protein_evidence as an array](./cttv_uris_namespaces.md)|no|[EFO disease or phenotype as an array](http://www.ebi.ac.uk/efo/)|[nearest example (Reactome); click here](../examples/cttv0006_networks_reactome)|


## 4. ChEMBL (CTTV008)
**Tech lead: mark-davies**

Evidence codes:

Evidence code | Label | Description
--------|--------|--------
http://identifiers.org/eco/ECO:0000205 |curator inference|An evidence type that is based on conclusions drawn by a curator.
http://identifiers.org/eco/ECO:0000001 | inference from background scientific knowledge | A curator inference that links the current annotation to a different evidence-based annotation via background knowledge of the curator.|
http://identifiers.org/eco/ECO:0000360 | biological target-disease association via drug |  - |

TD-association structure:

|biological_subject (target) | provenance has evidence chain | biological_object (disease) | example|
|----------------------------|-------------------------------|-----------------------------|--------|
|[child term of protein_evidence as an array](./cttv_uris_namespaces.md)|yes|[EFO disease or phenotype as an array](http://www.ebi.ac.uk/efo/)|[click here](../examples/cttv0008_chembl)|


## 5. GWAS catalog (CTTV009)
**Tech lead: tburdett**

Evidence codes:

Evidence code | Label | Description
--------|--------|--------
http://identifiers.org/eco/ECO:0000177 | genomic context evidence | An evidence type that is based on the location of a gene, which gives rise to a gene product, within the genome.|
http://identifiers.org/eco/ECO:0001113 | point mutation evidence | |
http://identifiers.org/eco/ECO:0000205 | curator inference | An evidence type that is based on conclusions drawn by a curator.|
http://identifiers.org/eco/ECO:0000053 | computational combinatorial evidence | A type of combinatorial analysis where data are combined and evaluated by an algorithm.|

TD-association structure:

|biological_subject (target) | provenance has evidence chain | biological_object (disease) | example|
|----------------------------|-------------------------------|-----------------------------|--------|
|[child term of gene_evidence as an array](./cttv_uris_namespaces.md)|yes|[EFO disease or phenotype as an array](http://www.ebi.ac.uk/efo/)|[nearest GWAS example (click here)](../examples/cttv0018_ibd_gwas)|


## 6. Tissue specificity (CTTV010)
**Tech lead: mkeays**

Evidence codes:

Evidence code | Label | Description
--------|--------|--------
http://identifiers.org/eco/ECO:0000356 | differential gene expression from microarray experiment | A gene is found to show statistically significant increase or decrease in expression in some condition relative to an appropriate control condition, when expression levels are assayed using a microarray platform.|
http://identifiers.org/eco/ECO:0000357 | differential gene expression from RNA-seq experiment | A gene is found to show statistically significant increase or decrease in expression in some condition relative to an appropriate control condition, when expression levels are assayed using an RNA sequencing platform.|
http://identifiers.org/eco/ECO:0000358 | differential geneset expression from microarray experiment | Gene set enrichment analysis (GSEA) shows that a functional classification (e.g. reaction pathway, Gene Ontology term, etc) has statistically significant over-representation in a group of genes showing differential expression when assayed using a microarray platform.|
http://identifiers.org/eco/ECO:0000359 | differential geneset expression from RNA-seq experiment | Gene set enrichment analysis (GSEA) shows that a functional classification (e.g. reaction pathway, Gene Ontology term, etc) has statistically significant over-representation in a group of genes showing differential expression when assayed using an RNA sequencing platform.|

TD-association structure:

|biological_subject (target) | provenance has evidence chain | biological_object (disease) | example|
|----------------------------|-------------------------------|-----------------------------|--------|
|[child term of transcript_evidence as an array](./cttv_uris_namespaces.md)|no|[EFO disease or phenotype as an array](http://www.ebi.ac.uk/efo/)|[click here](../examples/cttv0010_expression_atlas)|


## 7. Uniprot (CTTV011)
**Tech lead: eddturner**

Evidence codes:

Evidence code | Label | Description
--------|--------|--------
http://identifiers.org/eco/ECO:0000205 |curator inference|An evidence type that is based on conclusions drawn by a curator.
http://identifiers.org/eco/ECO:0000001 | inference from background scientific knowledge | A curator inference that links the current annotation to a different evidence-based annotation via background knowledge of the curator.|

TD-association structure:

|biological_subject (target) | provenance has evidence chain | biological_object (disease) | example|
|----------------------------|-------------------------------|-----------------------------|--------|
|[child term of protein_evidence as an array](./cttv_uris_namespaces.md)|no|[EFO disease or phenotype as an array](http://www.ebi.ac.uk/efo/)|[Nearest example - Reactome; click here](../examples/cttv0006_networks_reactome)|


## 8. Variation (CTTV012)
**Tech lead: javild**

Evidence codes:

Evidence code | Label | Description
--------|--------|--------
http://identifiers.org/eco/ECO:0000177 | genomic context evidence | An evidence type that is based on the location of a gene, which gives rise to a gene product, within the genome.|
http://identifiers.org/eco/ECO:0001113 | point mutation evidence | |
http://identifiers.org/eco/ECO:0000205 | curator inference | An evidence type that is based on conclusions drawn by a curator.|
http://identifiers.org/eco/ECO:0000053 | computational combinatorial evidence | A type of combinatorial analysis where data are combined and evaluated by an algorithm.|

TD-association structure:

|biological_subject (target) | provenance has evidence chain | biological_object (disease) | example|
|----------------------------|-------------------------------|-----------------------------|--------|
|[child term of gene_evidence as an array](./cttv_uris_namespaces.md)|yes|[EFO disease or phenotype as an array](http://www.ebi.ac.uk/efo/)|[Nearest GWAS example; click here](../examples/cttv0018_ibd_gwas)|


## 9. IBD_GWAS (CTTV018)
**Tech lead: slwest**

Evidence codes:

Evidence code | Label | Description
--------|--------|--------
http://identifiers.org/eco/ECO:0000177 | genomic context evidence | An evidence type that is based on the location of a gene, which gives rise to a gene product, within the genome.|
http://identifiers.org/eco/ECO:0001113 | point mutation evidence | |
http://identifiers.org/eco/ECO:0000205 | curator inference | An evidence type that is based on conclusions drawn by a curator.|
http://identifiers.org/eco/ECO:0000053 | computational combinatorial evidence | A type of combinatorial analysis where data are combined and evaluated by an algorithm.|

TD-association structure:

|biological_subject (target) | provenance has evidence chain | biological_object (disease) | example|
|----------------------------|-------------------------------|-----------------------------|--------|
|[child term of gene_evidence as an array](./cttv_uris_namespaces.md)|yes|[EFO disease or phenotype as an array](http://www.ebi.ac.uk/efo/)|[click here](../examples/cttv0018_ibd_gwas)|

