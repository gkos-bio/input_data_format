package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@JsonInclude(Include.NON_NULL)
public class BiologicalObjectProperties {

    private List<String> biosamples;
    private Map<String, String> experimentalEvidenceSpecific;

    public BiologicalObjectProperties(List<String> biosamples) {
        this.biosamples = biosamples;
    }

    private BiologicalObjectProperties(Builder builder) {
        biosamples = builder.biosamples;
        experimentalEvidenceSpecific = builder.experimentalEvidenceSpecific;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String putExperimentalEvidenceSpecific(String propertyName, String value){
        if(experimentalEvidenceSpecific==null){
            experimentalEvidenceSpecific = new HashMap<String, String>();
        }
        return experimentalEvidenceSpecific.put(propertyName, value);
    }

    public List<String> getBiosamples() {
        return biosamples;
    }

    @JsonProperty("experimental_evidence_specific")
    public Map<String, String> getExperimentalEvidenceSpecific() {
        return experimentalEvidenceSpecific;
    }

    public static final class Builder {
        private List<String> biosamples;
        private Map<String, String> experimentalEvidenceSpecific;

        private Builder() {
        }

        public Builder withBiosamples(List<String> biosamples) {
            this.biosamples = biosamples;
            return this;
        }

        public Builder withExperimentalEvidenceSpecific(Map<String, String> experimentalEvidenceSpecific) {
            this.experimentalEvidenceSpecific = experimentalEvidenceSpecific;
            return this;
        }

        public BiologicalObjectProperties build() {
            return new BiologicalObjectProperties(this);
        }
    }
}
