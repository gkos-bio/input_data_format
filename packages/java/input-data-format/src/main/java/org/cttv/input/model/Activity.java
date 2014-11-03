package org.cttv.input.model;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public enum Activity {
    UP ("cttvexp:up"),
    DOWN ("cttvexp:down"),
    UNKNOWN ("cttvexp:unknown"),
    DECRESED_TRANSCRIPT_LEVEL ("cttvexp:decreased_transcript_level"),
    INCRESED_TRANSCRIPT_LEVEL ("cttvexp:increased_transcript_level"),
    DECRESED_TRANSLATIONAL_PRRODUCT_LEVEL ("cttvexp:decreased_translational_product_level"),
    INCREASED_TRANSCRITP_LEVEL ("cttvexp:increased_transcript_level"),
    LOSS_OF_FUNCTION ("cttvexp:loss_of_function"),
    PARTIAL_LOSS_OF_FUNCTION ("cttvexp:partial_loss_of_function"),
    GAIN_OF_FUNCTION ("cttvexp:gain_of_function");

    private String activity;

    Activity(String activity) {
        this.activity = activity;
    }

    public String getActivity() {
        return activity;
    }
}
