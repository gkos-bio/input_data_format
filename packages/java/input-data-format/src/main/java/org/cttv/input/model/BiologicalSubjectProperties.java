package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class BiologicalSubjectProperties {


    private String targetType;
    private String activity;
    private Map<String, String> experimentSpecific;

    public BiologicalSubjectProperties(AssociationContext targetType, Activity activity) {
        this.targetType = targetType.getContext();
        this.activity = activity.getActivity();
    }

    public String putExperimentalEvidenceSpecific(String propertyName, String value){
        if(experimentSpecific ==null){
            experimentSpecific = new HashMap<String, String>();
        }
        return experimentSpecific.put(propertyName, value);
    }

    @JsonProperty(value = "target_type", required = false)
    @JsonFormat(pattern = "^http://identifiers.org/cttv[.]{1,1}target/.+$")
    @JsonPropertyDescription("This field is REQUIRED IF your biological_subject is a CTTV target. i.e. if the regex pattern match in biological_subject is ^http://identifiers.org/ensembl or ^http://identifiers.org/uniprot. It must represent a field in the CTTV core 'target' ontology. See https://github.com/CTTV/input_data_format/blob/master/json_schema/cttv_uris_namespaces.md")
    public String getTargetType() {
        return targetType;
    }

    @JsonProperty(value = "activity", required = false)
    @JsonFormat(pattern = "^http://identifiers.org/cttv[.]{1,1}target/.+$")
    @JsonPropertyDescription("This field is REQUIRED IF your biological_subject is a CTTV target. i.e. if the regex pattern match in biological_subject is ^http://identifiers.org/ensembl or ^http://identifiers.org/uniprot. It must represent a field in the CTTV core 'target' ontology. See https://github.com/CTTV/input_data_format/blob/master/json_schema/cttv_uris_namespaces.md")
    public String getActivity() {
        return activity;
    }

    @JsonProperty(value = "experiment_specific", required = false)
    @JsonPropertyDescription("Use this to add any string-based key:value pairs that you want to use to describe your biological subject.")
    public Map<String, String> getExperimentSpecific() {
        return experimentSpecific;
    }
}
