package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@JsonInclude(Include.NON_NULL)
public class BiologicalObject {

    private List<String> about;

    private BiologicalObjectProperties properties;

    public BiologicalObject(List<String> about, BiologicalObjectProperties properties) {
        this.about = about;
        this.properties = properties;
    }

    public List<String> getAbout() {
        return about;
    }

    public BiologicalObjectProperties getProperties() {
        return properties;
    }
}