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
public class BiologicalSubjectProperties {

//    private String associationContext;
//    private List<String> associatedSubjects;
    private AssociationContext targetType;
    private String activity;
    private Map<String, String> experimentSpecific;

    public BiologicalSubjectProperties(AssociationContext targetType, Activity activity) {
//        this.associationContext = associationContext.getContext();
        this.targetType = targetType;
        this.activity = activity.getActivity();
    }

//    public boolean addAssociatedSubjects(String associatedSubject){
//        if(this.associatedSubjects==null){
//            this.associatedSubjects = new LinkedList<String>();
//        }
//        return this.associatedSubjects.add(associatedSubject);
//    }

    public String putExperimentalEvidenceSpecific(String propertyName, String value){
        if(experimentSpecific ==null){
            experimentSpecific = new HashMap<String, String>();
        }
        return experimentSpecific.put(propertyName, value);
    }

//    @JsonProperty("association_context")
//    public String getAssociationContext() {
//        return associationContext;
//    }
//
//    @JsonProperty("associated_subjects")
//    public List<String> getAssociatedSubjects() {
//        return associatedSubjects;
//    }

    public String getActivity() {
        return activity;
    }

    @JsonProperty("experiment_specific")
    public Map<String, String> getExperimentSpecific() {
        return experimentSpecific;
    }
}
