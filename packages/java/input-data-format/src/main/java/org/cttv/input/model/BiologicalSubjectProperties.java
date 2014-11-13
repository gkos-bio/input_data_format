package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.LinkedList;
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

    public BiologicalSubjectProperties(AssociationContext associationContext, Activity activity) {
        this.associationContext = associationContext.getContext();
        this.activity = activity.getActivity();
    }

    private BiologicalSubjectProperties(Builder builder) {
        associationContext = builder.associationContext;
        associatedSubjects = builder.associatedSubjects;
        activity = builder.activity;
        experimentalEvidenceSpecific = builder.experimentalEvidenceSpecific;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public boolean addAssociatedSubjects(String associatedSubject){
        if(this.associatedSubjects==null){
            this.associatedSubjects = new LinkedList<String>();
        }
        return this.associatedSubjects.add(associatedSubject);
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

    public static final class Builder {
        private String associationContext;
        private List<String> associatedSubjects;
        private String activity;
        private Map<String, String> experimentalEvidenceSpecific;

        private Builder() {
        }

        public Builder withAssociationContext(String associationContext) {
            this.associationContext = associationContext;
            return this;
        }

        public Builder withAssociatedSubjects(List<String> associatedSubjects) {
            this.associatedSubjects = associatedSubjects;
            return this;
        }

        public Builder withActivity(String activity) {
            this.activity = activity;
            return this;
        }

        public Builder withExperimentalEvidenceSpecific(Map<String, String> experimentalEvidenceSpecific) {
            this.experimentalEvidenceSpecific = experimentalEvidenceSpecific;
            return this;
        }

        public BiologicalSubjectProperties build() {
            return new BiologicalSubjectProperties(this);
        }
    }
}
