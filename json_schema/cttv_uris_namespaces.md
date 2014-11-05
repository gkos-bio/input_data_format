# Namespace prefixes and resources in CTTV ontology

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  

  - [CTTV target ontology](#cttv-target-ontology)
  - [CTTV biological activity (in disease) ontology](#cttv-biological-activity-in-disease-ontology)
  - [CTTV core: For internal CTTV-core-ontology mapping:](#cttv-core-for-internal-cttv-core-ontology-mapping)
  - [CTTV experiment: For association_context OR activity fields:](#cttv-experiment-for-association_context-or-activity-fields)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## CTTV target ontology
The figure below shows the various definitions of 'target' as used in the CTTV core ontology. One of these terms needs to be used in the **{biological_subject}{association_context}** field of the JSON file to specify what kind of target you are describing:

![CTTV target ontology](../help/images/target_ontology.png)

#### URNs corresponding to the above target ontology:

```javascript
miriam:cttvexp:gene_evidence
    miriam:cttvexp:gene
    miriam:cttvexp:gene_allele
    miriam:cttvexp:gene_in_epigenetic_regulation_complex
    miriam:cttvexp:gene_in_LD_region
    miriam:cttvexp:gene_variant
miriam:cttvexp:protein_evidence
    miriam:cttvexp:chimeric_protein
    miriam:cttvexp:pro_protein
    miriam:cttvexp:protein
    miriam:cttvexp:protein_complex
        miriam:cttvexp:protein_complex_group
        miriam:cttvexp:protein_complex_heteropolymer
        miriam:cttvexp:protein_complex_homopolymer
        miriam:cttvexp:protein_nucleic_acid_complex
    miriam:cttvexp:protein_family
    miriam:cttvexp:protein_isoform
    miriam:cttvexp:selectivity_group
miriam:cttvexp:transcript_evidence
    miriam:cttvexp:transcript
    miriam:cttvexp:transcript_isoform
```

## CTTV biological activity (in disease) ontology
The figure below shows the various definitions of 'biological activity' as used in the CTTV core ontology. One of these terms needs to be used in the **{biological_subject}{activity}** field of the JSON file to specify what kind of biological activity was observed in the target-disease association:

![CTTV activity ontology](../help/images/activity_ontology.png)

#### URNs corresponding to the above biological activity ontology:

```javascript
miriam:cttvexp:down
    miriam:cttvexp:decreased_transcript_level
    miriam:cttvexp:decreased_translational_product_level
    miriam:cttvexp:drug_negative_modulator
    miriam:cttvexp:loss_of_function
    miriam:cttvexp:partial_loss_of_function
miriam:cttvexp:unknown
    miriam:cttvexp:predicted_damaging    
miriam:cttvexp:up
    miriam:cttvexp:drug_positive_modulator
    miriam:cttvexp:gain_of_function
    miriam:cttvexp:increased_transcript_level
    miriam:cttvexp:increased_translational_product_level
```

**These are all the resources specified in the current CTTV core ontology:**

## CTTV core: For internal CTTV-core-ontology mapping:
resource | comment | Where to use in JSON | prefix | url
:-------:|:------:|:---:|:-------:|:-------------------:
**miriam:cttv:DatabaseProvenance** | **Database provenance. Mapped to {provenance}{type} in CTTV json schema.** | CTTV core: For internal CTTV-core-ontology mapping | cttv | http://www.targetvalidation.org/cttv_core/DatabaseProvenance
**miriam:cttv:LiteratureProvenance** | **Literature provenance. Mapped to {provenance}{type} in CTTV json schema.** | CTTV core: For internal CTTV-core-ontology mapping | cttv | http://www.targetvalidation.org/cttv_core/LiteratureProvenance
**miriam:cttv:ExpertProvenance** | **Expert provenance. Mapped to {provenance}{type} in CTTV json schema.** | CTTV core: For internal CTTV-core-ontology mapping | cttv | http://www.targetvalidation.org/cttv_core/ExpertProvenance
**miriam:cttv:GWAS_SNP_to_trait_association** | - | CTTV core: For internal CTTV-core-ontology mapping | cttv | http://www.targetvalidation.org/cttv_core/GWAS_SNP_to_trait_association
**miriam:cttv:gene_to_disease_association** | - | CTTV core: For internal CTTV-core-ontology mapping | cttv | http://www.targetvalidation.org/cttv_core/gene_to_disease_association
**miriam:cttv:disease_to_phenotype_association** | - | CTTV core: For internal CTTV-core-ontology mapping | cttv | http://www.targetvalidation.org/cttv_core/disease_to_phenotype_association
## CTTV experiment: For association_context OR activity fields:
resource | comment | Where to use in JSON | prefix | url
:-------:|:------:|:---:|:-------:|:-------------------:
**miriam:cttvexp:partial_loss_of_function** | **partial loss of function detected in a genetics experiment.** | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/partial_loss_of_function
**miriam:cttvexp:cttv_activity** | - | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/cttv_activity
**miriam:cttvexp:unknown** | **biological activity of target in disease is difficult to predict / unknown.** | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/unknown
**miriam:cttvexp:experiment** | - | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/experiment
**miriam:cttvexp:gain_of_function** | **gain of function detected in a genetics experiment.** | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/gain_of_function
**miriam:cttvexp:protein_nucleic_acid_complex** | **Target is a complex consisting of both protein and nucleic-acid components (e.g., ribosome)** | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/protein_nucleic_acid_complex
**miriam:cttvexp:protein_complex_homopolymer** | **target is a homopolymeric protein complex.** | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/protein_complex_homopolymer
**miriam:cttvexp:increased_transcript_level** | **Transcript level is increased in a RNA experiment.** | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/increased_transcript_level
**miriam:cttvexp:chimeric_protein** | **Target is a fusion of two different proteins, either a synthetic construct or naturally occurring fusion protein** | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/chimeric_protein
**miriam:cttvexp:gene_in_LD_region** | **target is a set of genes mapped to a specific LD region.** | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/gene_in_LD_region
**miriam:cttvexp:selectivity_group** | **Target is a pair of proteins for which the selectivity has been assessed** | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/selectivity_group
**miriam:cttvexp:transcript** | **Target is a single transcript. Cannot ascertain whether the evidence is about an isoform.** | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/transcript
**miriam:cttvexp:gene_variant** | **target is a gene variant.** | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/gene_variant
**miriam:cttvexp:protein_complex** | **protein complex - this is a superclass. Target is a defined protein complex, consisting of multiple subunits \n** | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/protein_complex
**miriam:cttvexp:protein_isoform** | - | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/protein_isoform
**miriam:cttvexp:protein** | **Target is a single protein chain** | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/protein
**miriam:cttvexp:protein_family** | **Target is a group of closely related proteins\n** | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/protein_family
**miriam:cttvexp:up** | - | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/up
**miriam:cttvexp:gene_evidence** | **superclass of gene evidence types. Please choose one of its children classes.** | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/gene_evidence
**miriam:cttvexp:decreased_translational_product_level** | **Decreased traslation / decreased protein quantity measured in an experiment.** | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/decreased_translational_product_level
**miriam:cttvexp:drug_negative_modulator** | **Negatively effects the normal functioning of a protein e.g., receptor antagonist, inverse agonist or negative allosteric modulator** | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/drug_negative_modulator
**miriam:cttvexp:increased_translational_product_level** | **Increased traslation / increased protein quantity measured in an experiment.** | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/increased_translational_product_level
**miriam:cttvexp:protein_complex_heteropolymer** | **target is a heteropolymeric protein complex.** | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/protein_complex_heteropolymer
**miriam:cttvexp:gene_allele** | **target is a gene allele** | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/gene_allele
**miriam:cttvexp:predicted_damaging** | - | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/predicted_damaging
**miriam:cttvexp:gene_in_epigenetic_regulation_complex** | **target is a set of genes predicted to participate in an epigenetic regulation network.** | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/gene_in_epigenetic_regulation_complex
**miriam:cttvexp:protein_complex_group** | **Target is a poorly defined protein complex, where subunit composition is unclear (e.g., GABA-A receptor)** | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/protein_complex_group
**miriam:cttvexp:protein_evidence** | **superclass of protein evidence types. Please choose one of its children classes.** | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/protein_evidence
**miriam:cttvexp:down** | - | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/down
**miriam:cttvexp:pro_protein** | **target is a pro protein that will undergo further modifications to become mature.** | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/pro_protein
**miriam:cttvexp:decreased_transcript_level** | **Transcript level is reduced in a RNA experiment.** | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/decreased_transcript_level
**miriam:cttvexp:drug_positive_modulator** | **Positively effects the normal functioning of a protein e.g., receptor agonist, positive allosteric modulator, ion channel activator** | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/drug_positive_modulator
**miriam:cttvexp:loss_of_function** | **loss of function detected in a genetics experiment.** | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/loss_of_function
**miriam:cttvexp:transcript_evidence** | **superclass of transcript evidence types. Please choose one of its children classes.** | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/transcript_evidence
**miriam:cttvexp:transcript_isoform** | **The target is a transcript isoform.** | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/transcript_isoform
**miriam:cttvexp:gene** | **target is a single gene** | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/gene
