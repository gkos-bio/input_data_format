package org.cttv.input.model;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public enum Activity {
    UP ("http://identifiers.org/cttv.activity/up"),
    DOWN ("http://identifiers.org/cttv.activity/down"),
    UNKNOWN ("http://identifiers.org/cttv.activity/unknown"),
    DECREASED_TRANSCRIPT_LEVEL ("http://identifiers.org/cttv.activity/decreased_transcript_level"),
    INCREASED_TRANSCRIPT_LEVEL ("http://identifiers.org/cttv.activity/increased_transcript_level"),
    DECREASED_TRANSLATIONAL_PRODUCT_LEVEL ("http://identifiers.org/cttv.activity/decreased_translational_product_level"),
    INCREASED_TRANSLATIONAL_PRODUCT_LEVEL ("http://identifiers.org/cttv.activity/increased_translational_product_level"),
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
