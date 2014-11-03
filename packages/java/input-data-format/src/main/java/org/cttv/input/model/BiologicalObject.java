package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@JsonInclude(Include.NON_NULL)
public class BiologicalObject {

    private String about;

    private BiologicalObjectProperties properties;

    public BiologicalObject(String about){
        this.about = about;
    }

    public String getAbout() {
        return about;
    }

    public BiologicalObjectProperties getProperties() {
        return properties;
    }

    public void setProperties(BiologicalObjectProperties properties) {
        this.properties = properties;
    }
}