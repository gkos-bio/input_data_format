# Namespace prefixes and resources in CTTV ontology
**These are all the resources specified in the current CTTV core ontology:**

## CTTV core: For internal database mapping:
resource | prefix | url | comment | Where to use in JSON
:-------:|:------:|:---:|:-------:|:-------------------:
cttv:DatabaseProvenance | cttv | http://www.targetvalidation.org/cttv_core/DatabaseProvenance | Database provenance. Mapped to {provenance}{type} in CTTV json schema. | CTTV core: For internal database mapping
cttv:LiteratureProvenance | cttv | http://www.targetvalidation.org/cttv_core/LiteratureProvenance | Literature provenance. Mapped to {provenance}{type} in CTTV json schema. | CTTV core: For internal database mapping
cttv:ExpertProvenance | cttv | http://www.targetvalidation.org/cttv_core/ExpertProvenance | Expert provenance. Mapped to {provenance}{type} in CTTV json schema. | CTTV core: For internal database mapping
cttv:GWAS_SNP_to_trait_association | cttv | http://www.targetvalidation.org/cttv_core/GWAS_SNP_to_trait_association | - | CTTV core: For internal database mapping
cttv:gene_to_disease_association | cttv | http://www.targetvalidation.org/cttv_core/gene_to_disease_association | - | CTTV core: For internal database mapping
cttv:disease_to_phenotype_association | cttv | http://www.targetvalidation.org/cttv_core/disease_to_phenotype_association | - | CTTV core: For internal database mapping
## CTTV experiment: For association_context OR activity fields:
resource | prefix | url | comment | Where to use in JSON
:-------:|:------:|:---:|:-------:|:-------------------:
cttvexp:protein_nucleic_acid_complex | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/protein_nucleic_acid_complex | Target is a complex consisting of both protein and nucleic-acid components (e.g., ribosome) | CTTV experiment: For association_context OR activity fields
cttvexp:protein_complex_homopolymer | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/protein_complex_homopolymer | target is a homopolymeric protein complex. | CTTV experiment: For association_context OR activity fields
cttvexp:increased_transcript_level | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/increased_transcript_level | Transcript level is increased in a RNA experiment. | CTTV experiment: For association_context OR activity fields
cttvexp:chimeric_protein | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/chimeric_protein | Target is a fusion of two different proteins, either a synthetic construct or naturally occurring fusion protein | CTTV experiment: For association_context OR activity fields
cttvexp:gene_in_LD_region | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/gene_in_LD_region | target is a set of genes mapped to a specific LD region. | CTTV experiment: For association_context OR activity fields
cttvexp:transcript | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/transcript | Target is a single transcript. Cannot ascertain whether the evidence is about an isoform. | CTTV experiment: For association_context OR activity fields
cttvexp:selectivity_group | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/selectivity_group | Target is a pair of proteins for which the selectivity has been assessed  | CTTV experiment: For association_context OR activity fields
cttvexp:gene_variant | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/gene_variant | target is a gene variant. | CTTV experiment: For association_context OR activity fields
cttvexp:protein_complex | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/protein_complex | protein complex - this is a superclass. Target is a defined protein complex, consisting of multiple subunits \n | CTTV experiment: For association_context OR activity fields
cttvexp:protein_isoform | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/protein_isoform | - | CTTV experiment: For association_context OR activity fields
cttvexp:protein | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/protein | Target is a single protein chain  | CTTV experiment: For association_context OR activity fields
cttvexp:partial_loss_of_function | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/partial_loss_of_function | partial loss of function detected in a genetics experiment. | CTTV experiment: For association_context OR activity fields
cttvexp:cttv_activity | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/cttv_activity | - | CTTV experiment: For association_context OR activity fields
cttvexp:unknown | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/unknown | biological activity of target in disease is difficult to predict / unknown. | CTTV experiment: For association_context OR activity fields
cttvexp:experiment | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/experiment | - | CTTV experiment: For association_context OR activity fields
cttvexp:gain_of_function | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/gain_of_function | gain of function detected in a genetics experiment. | CTTV experiment: For association_context OR activity fields
cttvexp:protein_family | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/protein_family | Target is a group of closely related proteins\n | CTTV experiment: For association_context OR activity fields
cttvexp:up | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/up | - | CTTV experiment: For association_context OR activity fields
cttvexp:gene_evidence | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/gene_evidence | superclass of gene evidence types. Please choose one of its children classes. | CTTV experiment: For association_context OR activity fields
cttvexp:decreased_translational_product_level | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/decreased_translational_product_level | Decreased traslation / decreased protein quantity measured in an experiment. | CTTV experiment: For association_context OR activity fields
cttvexp:drug_negative_modulator | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/drug_negative_modulator | Negatively effects the normal functioning of a protein e.g., receptor antagonist, inverse agonist or negative allosteric modulator | CTTV experiment: For association_context OR activity fields
cttvexp:increased_translational_product_level | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/increased_translational_product_level | Increased traslation / increased protein quantity measured in an experiment. | CTTV experiment: For association_context OR activity fields
cttvexp:protein_complex_heteropolymer | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/protein_complex_heteropolymer | target is a heteropolymeric protein complex. | CTTV experiment: For association_context OR activity fields
cttvexp:gene_allele | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/gene_allele | target is a gene allele | CTTV experiment: For association_context OR activity fields
cttvexp:gene_in_epigenetic_regulation_complex | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/gene_in_epigenetic_regulation_complex | target is a set of genes predicted to participate in an epigenetic regulation network. | CTTV experiment: For association_context OR activity fields
cttvexp:protein_complex_group | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/protein_complex_group | Target is a poorly defined protein complex, where subunit composition is unclear (e.g., GABA-A receptor) | CTTV experiment: For association_context OR activity fields
cttvexp:protein_evidence | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/protein_evidence | superclass of protein evidence types. Please choose one of its children classes. | CTTV experiment: For association_context OR activity fields
cttvexp:down | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/down | - | CTTV experiment: For association_context OR activity fields
cttvexp:pro_protein | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/pro_protein | target is a pro protein that will undergo further modifications to become mature. | CTTV experiment: For association_context OR activity fields
cttvexp:decreased_transcript_level | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/decreased_transcript_level | Transcript level is reduced in a RNA experiment. | CTTV experiment: For association_context OR activity fields
cttvexp:drug_positive_modulator | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/drug_positive_modulator | Positively effects the normal functioning of a protein e.g., receptor agonist, positive allosteric modulator, ion channel activator | CTTV experiment: For association_context OR activity fields
cttvexp:loss_of_function | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/loss_of_function | loss of function detected in a genetics experiment. | CTTV experiment: For association_context OR activity fields
cttvexp:transcript_evidence | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/transcript_evidence | superclass of transcript evidence types. Please choose one of its children classes. | CTTV experiment: For association_context OR activity fields
cttvexp:transcript_isoform | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/transcript_isoform | The target is a transcript isoform. | CTTV experiment: For association_context OR activity fields
cttvexp:gene | cttvexp | http://www.targetvalidation.org/cttv_core/experiment/gene | target is a single gene | CTTV experiment: For association_context OR activity fields
