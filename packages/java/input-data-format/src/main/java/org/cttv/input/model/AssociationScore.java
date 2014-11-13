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

    private AssociationScore(Builder builder) {
        probability = builder.probability;
        pValue = builder.pValue;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Double getProbability() {
        return probability;
    }

    @JsonProperty("p-value")
    public Double getpValue() {
        return pValue;
    }

    public static final class Builder {
        private Double probability;
        private Double pValue;

        private Builder() {
        }

        public Builder withProbability(Double probability) {
            this.probability = probability;
            return this;
        }

        public Builder withPValue(Double pValue) {
            this.pValue = pValue;
            return this;
        }

        public AssociationScore build() {
            return new AssociationScore(this);
        }
    }
}
