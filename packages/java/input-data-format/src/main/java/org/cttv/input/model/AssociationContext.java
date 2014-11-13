package org.cttv.input.model;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public enum AssociationContext {

    Gene ("http://identifiers.org/cttv.target/gene"),
    Gene_allele ("http://identifiers.org/cttv.target/gene_allele"),
    Gene_in_ld_region ("http://identifiers.org/cttv.target/gene_in_ld_region"),
    Gene_variant ("http://identifiers.org/cttv.target/gene_variant"),
    Gene_in_epigenetic_regulation_complex ("http://identifiers.org/cttv.target/gene_in_epigenetic_regulation_complex"),
    Transcript ("http://identifiers.org/cttv.target/transcript"),
    Transcript_isoform ("http://identifiers.org/cttv.target/transcript_isoform"),
    Protein ("http://identifiers.org/cttv.target/protein"),
    Protein_complex_homopolymer ("http://identifiers.org/cttv.target/protein_complex_homopolymer"),
    Protein_complex_heteropolymer ("http://identifiers.org/cttv.target/protein_complex_heteropolymer"),
    Protein_isoform ("http://identifiers.org/cttv.target/protein_isoform"),
    Protein_signaling_pathway ("http://identifiers.org/cttv.target/protein_signaling_pathway");

    private String context;

    AssociationContext(String context) {
        this.context = context;
    }

    public String getContext() {
        return context;
    }
}
