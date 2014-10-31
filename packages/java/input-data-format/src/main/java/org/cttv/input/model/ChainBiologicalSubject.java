package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@JsonInclude(Include.NON_NULL)
public class ChainBiologicalSubject {

    private String about;

    public ChainBiologicalSubject(String about) {
        this.about = about;
    }

    public String getAbout() {
        return about;
    }
}
