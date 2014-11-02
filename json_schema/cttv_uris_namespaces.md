# Namespace prefixes and resources in CTTV ontology
**These are all the resources specified in the current CTTV core ontology:**

## CTTV core: For internal database mapping:
resource | comment | Where to use in JSON | prefix | url
:-------:|:------:|:---:|:-------:|:-------------------:
cttv:DatabaseProvenance | Database provenance. Mapped to {provenance}{type} in CTTV json schema. | CTTV core: For internal database mapping | cttv | http://www.targetvalidation.org/cttv_core/DatabaseProvenance
cttv:LiteratureProvenance | Literature provenance. Mapped to {provenance}{type} in CTTV json schema. | CTTV core: For internal database mapping | cttv | http://www.targetvalidation.org/cttv_core/LiteratureProvenance
cttv:ExpertProvenance | Expert provenance. Mapped to {provenance}{type} in CTTV json schema. | CTTV core: For internal database mapping | cttv | http://www.targetvalidation.org/cttv_core/ExpertProvenance
cttv:GWAS_SNP_to_trait_association | - | CTTV core: For internal database mapping | cttv | http://www.targetvalidation.org/cttv_core/GWAS_SNP_to_trait_association
cttv:gene_to_disease_association | - | CTTV core: For internal database mapping | cttv | http://www.targetvalidation.org/cttv_core/gene_to_disease_association
cttv:disease_to_phenotype_association | - | CTTV core: For internal database mapping | cttv | http://www.targetvalidation.org/cttv_core/disease_to_phenotype_association
## CTTV experiment: For association_context OR activity fields:
resource | comment | Where to use in JSON | prefix | url
:-------:|:------:|:---:|:-------:|:-------------------:
cttvexp:protein_nucleic_acid_complex | Target is a complex consisting of both protein and nucleic-acid components (e.g., ribosome) | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/protein_nucleic_acid_complex
cttvexp:protein_complex_homopolymer | target is a homopolymeric protein complex. | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/protein_complex_homopolymer
cttvexp:increased_transcript_level | Transcript level is increased in a RNA experiment. | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/increased_transcript_level
cttvexp:chimeric_protein | Target is a fusion of two different proteins, either a synthetic construct or naturally occurring fusion protein | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/chimeric_protein
cttvexp:gene_in_LD_region | target is a set of genes mapped to a specific LD region. | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/gene_in_LD_region
cttvexp:transcript | Target is a single transcript. Cannot ascertain whether the evidence is about an isoform. | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/transcript
cttvexp:selectivity_group | Target is a pair of proteins for which the selectivity has been assessed  | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/selectivity_group
cttvexp:gene_variant | target is a gene variant. | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/gene_variant
cttvexp:protein_complex | protein complex - this is a superclass. Target is a defined protein complex, consisting of multiple subunits \n | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/protein_complex
cttvexp:protein_isoform | - | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/protein_isoform
cttvexp:protein | Target is a single protein chain  | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/protein
cttvexp:partial_loss_of_function | partial loss of function detected in a genetics experiment. | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/partial_loss_of_function
cttvexp:cttv_activity | - | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/cttv_activity
cttvexp:unknown | biological activity of target in disease is difficult to predict / unknown. | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/unknown
cttvexp:experiment | - | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/experiment
cttvexp:gain_of_function | gain of function detected in a genetics experiment. | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/gain_of_function
cttvexp:protein_family | Target is a group of closely related proteins\n | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/protein_family
cttvexp:up | - | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/up
cttvexp:gene_evidence | superclass of gene evidence types. Please choose one of its children classes. | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/gene_evidence
cttvexp:decreased_translational_product_level | Decreased traslation / decreased protein quantity measured in an experiment. | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/decreased_translational_product_level
cttvexp:drug_negative_modulator | Negatively effects the normal functioning of a protein e.g., receptor antagonist, inverse agonist or negative allosteric modulator | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/drug_negative_modulator
cttvexp:increased_translational_product_level | Increased traslation / increased protein quantity measured in an experiment. | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/increased_translational_product_level
cttvexp:protein_complex_heteropolymer | target is a heteropolymeric protein complex. | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/protein_complex_heteropolymer
cttvexp:gene_allele | target is a gene allele | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/gene_allele
cttvexp:gene_in_epigenetic_regulation_complex | target is a set of genes predicted to participate in an epigenetic regulation network. | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/gene_in_epigenetic_regulation_complex
cttvexp:protein_complex_group | Target is a poorly defined protein complex, where subunit composition is unclear (e.g., GABA-A receptor) | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/protein_complex_group
cttvexp:protein_evidence | superclass of protein evidence types. Please choose one of its children classes. | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/protein_evidence
cttvexp:down | - | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/down
cttvexp:pro_protein | target is a pro protein that will undergo further modifications to become mature. | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/pro_protein
cttvexp:decreased_transcript_level | Transcript level is reduced in a RNA experiment. | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/decreased_transcript_level
cttvexp:drug_positive_modulator | Positively effects the normal functioning of a protein e.g., receptor agonist, positive allosteric modulator, ion channel activator | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/drug_positive_modulator
cttvexp:loss_of_function | loss of function detected in a genetics experiment. | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/loss_of_function
cttvexp:transcript_evidence | superclass of transcript evidence types. Please choose one of its children classes. | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/transcript_evidence
cttvexp:transcript_isoform | The target is a transcript isoform. | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/transcript_isoform
cttvexp:gene | target is a single gene | CTTV experiment: For association_context OR activity fields | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/gene
