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

    public List<String> getBiosamples() {
        return biosamples;
    }

    @JsonProperty("experiment_specific")
    public Map<String, String> getExperimentSpecific() {
        return experimentSpecific;
    }
}
