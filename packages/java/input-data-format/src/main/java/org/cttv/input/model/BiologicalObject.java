package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@JsonInclude(Include.NON_NULL)
public class BiologicalObject {

    private List<String> about;
    private BiologicalObjectProperties properties;

    public BiologicalObject(String about){
        this.about = new LinkedList<String>();
        this.about.add(about);
    }

    public boolean addAbout(String about){
        return this.about.add(about);
    }

    @JsonProperty(value = "about", required = true)
    @JsonPropertyDescription("An array of http://identifiers.org/ URNs. For example, if denoting a set of EFO phenotypes: [http://identifiers.org/efo/0000537, http://identifiers.org/efo/0000522]")
    //TODO: Add minItems and uniqueItems
    public List<String> getAbout() {
        return about;
    }


    @JsonProperty(value = "properties", required = false)
    public BiologicalObjectProperties getProperties() {
        return properties;
    }

    public void setProperties(BiologicalObjectProperties properties) {
        this.properties = properties;
    }
}