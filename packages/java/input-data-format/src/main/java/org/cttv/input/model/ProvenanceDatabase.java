package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@JsonInclude(Include.NON_NULL)
public class ProvenanceDatabase {
    private String id;
    private String version;
    private ProvenanceDatabaseXRef dbXref;

    public ProvenanceDatabase(String id, String version) {
        this.id = id;
        this.version = version;
    }

    public void setDbXref(ProvenanceDatabaseXRef dbXref) {
        this.dbXref = dbXref;
    }

    @JsonProperty(value = "id", required = false)
    public String getId() {
        return id;
    }

    @JsonProperty(value = "version", required = false)
    public String getVersion() {
        return version;
    }

    @JsonProperty(value = "dbxref", required = false)
    public ProvenanceDatabaseXRef getDbXref() {
        return dbXref;
    }
}
