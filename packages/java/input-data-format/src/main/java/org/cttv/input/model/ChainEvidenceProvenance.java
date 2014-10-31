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
public class ChainEvidenceProvenance {

    private List<String> evidenceCodes;
    private Map<String, String> experimentalEvidenceSpecific;
    private AssociationScore associationScore;

    public ChainEvidenceProvenance(List<String> evidenceCodes, AssociationScore associationScore) {
        this.evidenceCodes = evidenceCodes;
        this.associationScore = associationScore;
    }

    public String putExperimentalEvidenceSpecific(String propertyName, String value){
        if(experimentalEvidenceSpecific==null){
            experimentalEvidenceSpecific = new HashMap<String, String>();
        }
        return experimentalEvidenceSpecific.put(propertyName, value);
    }

    @JsonProperty("evidence_codes")
    public List<String> getEvidenceCodes() {
        return evidenceCodes;
    }

    @JsonProperty("experimental_evidence_specific")
    public Map<String, String> getExperimentalEvidenceSpecific() {
        return experimentalEvidenceSpecific;
    }

    @JsonProperty("association_score")
    public AssociationScore getAssociationScore() {
        return associationScore;
    }
}
