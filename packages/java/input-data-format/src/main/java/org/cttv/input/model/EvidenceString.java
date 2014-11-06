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
    private Provenance provenance;
    private BiologicalObject biologicalObject;

    public EvidenceString(BiologicalSubject biologicalSubject, Provenance provenance, BiologicalObject biologicalObject) {
        this.biologicalSubject = biologicalSubject;
        this.provenance = provenance;
        this.biologicalObject = biologicalObject;
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

    public Provenance getProvenance() {
        return provenance;
    }

    @JsonProperty("biological_object")
    public BiologicalObject getBiologicalObject() {
        return biologicalObject;
    }

    @JsonProperty("unique_association_fields")
    public Map<String, String> getUniqueAssociationFields() {
        return uniqueAssociationFields;
    }
}
