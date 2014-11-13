package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExperimentalEvidenceSpecific {

    private Map<String, ChainEvidence> evidenceChain;

    private Map<String, String> properties;

    public ExperimentalEvidenceSpecific() {
        //Nothing here
    }

    private ExperimentalEvidenceSpecific(Builder builder) {
        evidenceChain = builder.evidenceChain;
        properties = builder.properties;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public ChainEvidence putChainEvidence(String key, ChainEvidence chainEvidence){
        if(evidenceChain==null){
            evidenceChain = new HashMap<String, ChainEvidence>();
        }
        return this.evidenceChain.put(key, chainEvidence);
    }

    public String putProperty(String propertyName, String value){
        if(properties==null){
            properties = new HashMap<String, String>();
        }
        return properties.put(propertyName, value);
    }

    @JsonProperty("evidence_chain")
    public Map<String, ChainEvidence> getEvidenceChain() {
        return evidenceChain;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public static final class Builder {
        private Map<String, ChainEvidence> evidenceChain;
        private Map<String, String> properties;

        private Builder() {
        }

        public Builder withEvidenceChain(Map<String, ChainEvidence> evidenceChain) {
            this.evidenceChain = evidenceChain;
            return this;
        }

        public Builder withProperties(Map<String, String> properties) {
            this.properties = properties;
            return this;
        }

        public ExperimentalEvidenceSpecific build() {
            return new ExperimentalEvidenceSpecific(this);
        }
    }
}
