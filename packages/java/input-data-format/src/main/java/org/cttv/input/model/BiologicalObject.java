package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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

    public List<String> getAbout() {
        return about;
    }

    public BiologicalObjectProperties getProperties() {
        return properties;
    }

    public void setProperties(BiologicalObjectProperties properties) {
        this.properties = properties;
    }
}