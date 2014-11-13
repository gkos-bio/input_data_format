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
    private Double schemaVersion;

    public EvidenceString(BiologicalSubject biologicalSubject, Evidence evidence, BiologicalObject biologicalObject, Map<String, String> uniqueAssociationFields, Double schemaVersion) {
        this.biologicalSubject = biologicalSubject;
        this.evidence = evidence;
        this.biologicalObject = biologicalObject;
        this.uniqueAssociationFields = uniqueAssociationFields;
        this.schemaVersion = schemaVersion;
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

    @JsonProperty("validated_against_schema_version")
    public Double getSchemaVersion() {
        return schemaVersion;
    }
}
