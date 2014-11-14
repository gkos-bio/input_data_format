package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

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
    private Double schemaVersion = 1.1;

    public EvidenceString(BiologicalSubject biologicalSubject, Evidence evidence, BiologicalObject biologicalObject, Map<String, String> uniqueAssociationFields) {
        this.biologicalSubject = biologicalSubject;
        this.evidence = evidence;
        this.biologicalObject = biologicalObject;
        this.uniqueAssociationFields = uniqueAssociationFields;
    }

    public String putUniqueAssociationField(String key, String value){
        if(uniqueAssociationFields ==null){
            uniqueAssociationFields = new HashMap<String, String>();
        }
        return uniqueAssociationFields.put(key, value);
    }

    @JsonProperty(value = "biological_subject", required = true)
    @JsonPropertyDescription("The biological subject is any biological entity that can be expressed with an identifiers.org URI e.g. http://identifiers.org/ensembl/ENSG9023124 or http://identifiers.org/pfam/PF00619")
    public BiologicalSubject getBiologicalSubject() {
        return biologicalSubject;
    }

    @JsonProperty(value = "evidence", required = true)
    public Evidence getEvidence() {
        return evidence;
    }

    @JsonProperty(value = "biological_object", required = true)
    @JsonPropertyDescription("The biological object is any biological entity that can be expressed with a http://identifiers.org/ URN e.g. http://identifiers.org/ensembl/ENSG9023124 or http://identifiers.org/pfam/PF00619")
    public BiologicalObject getBiologicalObject() {
        return biologicalObject;
    }

    @JsonProperty(value = "unique_association_fields", required = true)
    @JsonPropertyDescription("A combination of keys whose values uniquely identify each JSON object. For some data sources, the values for some keys may be comma-separated concatenations")
    public Map<String, String> getUniqueAssociationFields() {
        return uniqueAssociationFields;
    }

    @JsonProperty(value = "validated_against_schema_version", required = true)
    @JsonPropertyDescription("The schema version number against which the JSON object was validated.")
    public Double getSchemaVersion() {
        return schemaVersion;
    }
}
