package org.cttv.input.model;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public enum Activity {
    UP ("http://identifiers.org/cttv.activity/up"),
    DOWN ("http://identifiers.org/cttv.activity/down"),
    UNKNOWN ("http://identifiers.org/cttv.activity/unknown"),
    DECRESED_TRANSCRIPT_LEVEL ("http://identifiers.org/cttv.activity/decreased_transcript_level"),
    INCRESED_TRANSCRIPT_LEVEL ("http://identifiers.org/cttv.activity/increased_transcript_level"),
    DECRESED_TRANSLATIONAL_PRRODUCT_LEVEL ("http://identifiers.org/cttv.activity/decreased_translational_product_level"),
    INCREASED_TRANSCRITP_LEVEL ("http://identifiers.org/cttv.activity/increased_transcript_level"),
    LOSS_OF_FUNCTION ("http://identifiers.org/cttv.activity/loss_of_function"),
    PARTIAL_LOSS_OF_FUNCTION ("http://identifiers.org/cttv.activity/partial_loss_of_function"),
    GAIN_OF_FUNCTION ("http://identifiers.org/cttv.activity/gain_of_function");

    private String activity;

    Activity(String activity) {
        this.activity = activity;
    }

    public String getActivity() {
        return activity;
    }
}
