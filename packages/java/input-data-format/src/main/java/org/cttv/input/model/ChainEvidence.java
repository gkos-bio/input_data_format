package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@JsonInclude(Include.NON_NULL)
public class ChainEvidence {
    private ChainBiologicalSubject biologicalSubject;
    private ChainEvidenceProvenance provenance;
    private ChainBiologicalObject biologicalObject;

    public ChainEvidence(ChainBiologicalSubject biologicalSubject, ChainEvidenceProvenance provenance, ChainBiologicalObject biologicalObject) {
        this.biologicalSubject = biologicalSubject;
        this.provenance = provenance;
        this.biologicalObject = biologicalObject;
    }

    private ChainEvidence(Builder builder) {
        biologicalSubject = builder.biologicalSubject;
        provenance = builder.provenance;
        biologicalObject = builder.biologicalObject;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @JsonProperty("biological_subject")
    public ChainBiologicalSubject getBiologicalSubject() {
        return biologicalSubject;
    }

    public ChainEvidenceProvenance  getProvenance() {
        return provenance;
    }

    @JsonProperty("biological_object")
    public ChainBiologicalObject getBiologicalObject() {
        return biologicalObject;
    }

    public static final class Builder {
        private ChainBiologicalSubject biologicalSubject;
        private ChainEvidenceProvenance provenance;
        private ChainBiologicalObject biologicalObject;

        private Builder() {
        }

        public Builder withBiologicalSubject(ChainBiologicalSubject biologicalSubject) {
            this.biologicalSubject = biologicalSubject;
            return this;
        }

        public Builder withProvenance(ChainEvidenceProvenance provenance) {
            this.provenance = provenance;
            return this;
        }

        public Builder withBiologicalObject(ChainBiologicalObject biologicalObject) {
            this.biologicalObject = biologicalObject;
            return this;
        }

        public ChainEvidence build() {
            return new ChainEvidence(this);
        }
    }
}
