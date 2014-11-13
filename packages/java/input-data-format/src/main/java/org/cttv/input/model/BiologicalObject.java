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

    private ExperimentSpecific experimentSpecific;

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

    public ExperimentSpecific getExperimentSpecific() {
        return experimentSpecific;
    }

    public void setExperimentSpecific(ExperimentSpecific experimentSpecific) {
        this.experimentSpecific = experimentSpecific;
    }
}