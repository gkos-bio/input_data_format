package org.cttv.input.model;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public enum AssociationContext {

    Gene ("cttvexp:gene"),
    Gene_allele ("cttvexp:gene_allele"),
    Gene_in_ld_region ("cttvexp:gene_in_ld_region"),
    Gene_variant ("cttvexp:gene_variant"),
    Gene_in_epigenetic_regulation_complex ("cttvexp:gene_in_epigenetic_regulation_complex"),
    Transcript ("cttvexp:transcript"),
    Transcript_isoform ("cttvexp:transcript_isoform"),
    Protein ("cttvexp:protein"),
    Protein_complex_homopolymer ("cttvexp:protein_complex_homopolymer"),
    Protein_complex_heteropolymer ("cttvexp:protein_complex_heteropolymer"),
    Protein_isoform ("cttvexp:protein_isoform"),
    Protein_signaling_pathway ("cttvexp:protein_signaling_pathway");

    private String context;

    AssociationContext(String context) {
        this.context = context;
    }

    public String getContext() {
        return context;
    }
}
