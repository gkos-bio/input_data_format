package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@JsonInclude(Include.NON_NULL)
public class ProvenanceType {
    private ProvenanceLiterature literature;
    private ProvenanceExpert expert;
    private ProvenanceDatabase database;

    public ProvenanceType(ProvenanceLiterature literature, ProvenanceExpert expert, ProvenanceDatabase database) {
        this.literature = literature;
        this.expert = expert;
        this.database = database;
    }

    public ProvenanceLiterature getLiterature() {
        return literature;
    }

    public ProvenanceExpert getExpert() {
        return expert;
    }

    public ProvenanceDatabase getDatabase() {
        return database;
    }
}
