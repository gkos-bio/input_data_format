package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@JsonInclude(Include.NON_NULL)
public class ProvenanceDatabase {
    private int isTrue;
    private String id;
    private String version;
    private ProvenanceDatabaseXRef dbXref;

    public ProvenanceDatabase(int isTrue, String id, String version, ProvenanceDatabaseXRef dbXref) {
        this.isTrue = isTrue;
        this.id = id;
        this.version = version;
        this.dbXref = dbXref;
    }

    @JsonProperty("true")
    public int getIsTrue() {
        return isTrue;
    }

    public String getId() {
        return id;
    }

    public String getVersion() {
        return version;
    }

    @JsonProperty("dbxref")
    public ProvenanceDatabaseXRef getDbXref() {
        return dbXref;
    }
}
