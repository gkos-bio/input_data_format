package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @JsonProperty(value = "literature", required = false)
    public ProvenanceLiterature getLiterature() {
        return literature;
    }

    @JsonProperty(value = "expert", required = false)
    public ProvenanceExpert getExpert() {
        return expert;
    }

    @JsonProperty(value = "database", required = false)
    public ProvenanceDatabase getDatabase() {
        return database;
    }
}
