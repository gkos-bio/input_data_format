package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@JsonInclude(Include.NON_NULL)
public class AssociationScore {
    private Double probability;
    private Double pValue;

    public AssociationScore(Double probability, Double pValue) {
        this.probability = probability;
        this.pValue = pValue;
    }

    public AssociationScore() {
        this.probability = 1.0d;
        this.pValue = 0.0d;
    }

    public Double getProbability() {
        return probability;
    }

    @JsonProperty("p-value")
    public Double getpValue() {
        return pValue;
    }
}
