package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@JsonInclude(Include.NON_NULL)
public class BiologicalObjectProperties {

    private List<String> biosamples;
    private Map<String, String> experimentSpecific;

    public BiologicalObjectProperties(List<String> biosamples) {
        this.biosamples = biosamples;
    }

    public String putExperimentSpecific(String propertyName, String value){
        if(experimentSpecific ==null){
            experimentSpecific = new HashMap<String, String>();
        }
        return experimentSpecific.put(propertyName, value);
    }

    @JsonProperty(value = "biosamples", required = false)
    @JsonPropertyDescription("An array of affected tissues expressed using its EFO term e.g. http://identifiers.org/uberon/UBERON:0004535, the uberon URI for cardiovascular system")
    //TODO: add minItems and uniqueItems
    public List<String> getBiosamples() {
        return biosamples;
    }

    @JsonProperty(value = "experiment_specific", required = false)
    @JsonPropertyDescription("Use this to add any string-based key:value pairs that you want to use to describe your biological object.")
    public Map<String, String> getExperimentSpecific() {
        return experimentSpecific;
    }
}
