package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@JsonInclude(Include.NON_NULL)
public class BiologicalSubject {

    private List<String> about;

    private BiologicalSubjectProperties properties;

    public BiologicalSubject(List<String> about, BiologicalSubjectProperties properties) {
        this.about = about;
        this.properties = properties;
    }

    public List<String> getAbout() {
        return about;
    }

    public BiologicalSubjectProperties getProperties() {
        return properties;
    }
}
