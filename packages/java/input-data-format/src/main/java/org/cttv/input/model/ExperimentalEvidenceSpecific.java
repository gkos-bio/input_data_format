package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public class ExperimentalEvidenceSpecific {

    private Map<String, ChainEvidence> evidenceChain;

    private Map<String, String> properties;

    public ExperimentalEvidenceSpecific() {
        //Nothing here
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
}
