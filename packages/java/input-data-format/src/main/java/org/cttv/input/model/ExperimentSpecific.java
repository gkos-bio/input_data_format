package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExperimentSpecific {

//    private Map<String, ChainEvidence> evidenceChain;

    private Map<String, String> additionalProperties;

    public ExperimentSpecific() {
        //Nothing here
    }

//    public ChainEvidence putChainEvidence(String key, ChainEvidence chainEvidence){
//        if(evidenceChain==null){
//            evidenceChain = new HashMap<String, ChainEvidence>();
//        }
//        return this.evidenceChain.put(key, chainEvidence);
//    }

    public String putProperty(String propertyName, String value){
        if(additionalProperties ==null){
            additionalProperties = new HashMap<String, String>();
        }
        return additionalProperties.put(propertyName, value);
    }

//    @JsonProperty("evidence_chain")
//    public Map<String, ChainEvidence> getEvidenceChain() {
//        return evidenceChain;
//    }

    @JsonProperty("additional_properties")
    public Map<String, String> getAdditionalProperties() {
        return additionalProperties;
    }
}
