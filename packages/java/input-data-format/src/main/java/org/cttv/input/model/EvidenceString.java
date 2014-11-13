package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@JsonInclude(Include.NON_NULL)
public class EvidenceString {

    private Map<String, String> uniqueAssociationFields;
    private BiologicalSubject biologicalSubject;
    private Evidence evidence;
    private BiologicalObject biologicalObject;

    public EvidenceString(BiologicalSubject biologicalSubject, Evidence evidence, BiologicalObject biologicalObject) {
        this.biologicalSubject = biologicalSubject;
        this.evidence = evidence;
        this.biologicalObject = biologicalObject;
    }

    private EvidenceString(Builder builder) {
        uniqueAssociationFields = builder.uniqueAssociationFields;
        biologicalSubject = builder.biologicalSubject;
        evidence = builder.evidence;
        biologicalObject = builder.biologicalObject;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String putUniqueAssociationField(String key, String value){
        if(uniqueAssociationFields ==null){
            uniqueAssociationFields = new HashMap<String, String>();
        }
        return uniqueAssociationFields.put(key, value);
    }

    @JsonProperty("biological_subject")
    public BiologicalSubject getBiologicalSubject() {
        return biologicalSubject;
    }

    public Evidence getEvidence() {
        return evidence;
    }

    @JsonProperty("biological_object")
    public BiologicalObject getBiologicalObject() {
        return biologicalObject;
    }

    @JsonProperty("unique_association_fields")
    public Map<String, String> getUniqueAssociationFields() {
        return uniqueAssociationFields;
    }

    public static final class Builder {
        private Map<String, String> uniqueAssociationFields;
        private BiologicalSubject biologicalSubject;
        private Evidence evidence;
        private BiologicalObject biologicalObject;

        private Builder() {
        }

        public Builder withUniqueAssociationFields(Map<String, String> uniqueAssociationFields) {
            this.uniqueAssociationFields = uniqueAssociationFields;
            return this;
        }

        public Builder withBiologicalSubject(BiologicalSubject biologicalSubject) {
            this.biologicalSubject = biologicalSubject;
            return this;
        }

        public Builder withEvidence(Evidence evidence) {
            this.evidence = evidence;
            return this;
        }

        public Builder withBiologicalObject(BiologicalObject biologicalObject) {
            this.biologicalObject = biologicalObject;
            return this;
        }

        public EvidenceString build() {
            return new EvidenceString(this);
        }
    }
}
