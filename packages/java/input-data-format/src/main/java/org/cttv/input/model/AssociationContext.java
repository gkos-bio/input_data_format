package org.cttv.input.model;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public enum AssociationContext {

    Gene ("miriam:cttvexp:gene"),
    Gene_allele ("miriam:cttvexp:gene_allele"),
    Gene_in_ld_region ("miriam:cttvexp:gene_in_ld_region"),
    Gene_variant ("miriam:cttvexp:gene_variant"),
    Gene_in_epigenetic_regulation_complex ("miriam:cttvexp:gene_in_epigenetic_regulation_complex"),
    Transcript ("miriam:cttvexp:transcript"),
    Transcript_isoform ("miriam:cttvexp:transcript_isoform"),
    Protein ("miriam:cttvexp:protein"),
    Protein_complex_homopolymer ("miriam:cttvexp:protein_complex_homopolymer"),
    Protein_complex_heteropolymer ("miriam:cttvexp:protein_complex_heteropolymer"),
    Protein_isoform ("miriam:cttvexp:protein_isoform"),
    Protein_signaling_pathway ("miriam:cttvexp:protein_signaling_pathway");

    private String context;

    AssociationContext(String context) {
        this.context = context;
    }

    public String getContext() {
        return context;
    }
}
