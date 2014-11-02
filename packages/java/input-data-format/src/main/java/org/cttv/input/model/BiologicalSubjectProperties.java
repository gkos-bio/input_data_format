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
public class BiologicalSubjectProperties {

    private String associationContext;
    private List<String> associatedSubjects;
    private String activity;
    private Map<String, String> experimentalEvidenceSpecific;

    public BiologicalSubjectProperties(AssociationContext associationContext, List<String> associatedSubjects, String activity) {
        this.associationContext = associationContext.getContext();
        this.associatedSubjects = associatedSubjects;
        this.activity = activity;
    }

    public String putExperimentalEvidenceSpecific(String propertyName, String value){
        if(experimentalEvidenceSpecific==null){
            experimentalEvidenceSpecific = new HashMap<String, String>();
        }
        return experimentalEvidenceSpecific.put(propertyName, value);
    }

    @JsonProperty("association_context")
    public String getAssociationContext() {
        return associationContext;
    }

    @JsonProperty("associated_subjects")
    public List<String> getAssociatedSubjects() {
        return associatedSubjects;
    }

    public String getActivity() {
        return activity;
    }

    @JsonProperty("experimental_evidence_specific")
    public Map<String, String> getExperimentalEvidenceSpecific() {
        return experimentalEvidenceSpecific;
    }
}
