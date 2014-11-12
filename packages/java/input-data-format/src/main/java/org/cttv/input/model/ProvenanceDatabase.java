package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@JsonInclude(Include.NON_NULL)
public class ProvenanceDatabase {
//    private boolean isStatus;
    private String id;
    private String version;
    private ProvenanceDatabaseXRef dbXref;

    public ProvenanceDatabase(String id, String version) {
//        this.isStatus = true;
        this.id = id;
        this.version = version;
    }

    public void setDbXref(ProvenanceDatabaseXRef dbXref) {
        this.dbXref = dbXref;
    }

//    @JsonProperty("true")
//    public boolean getIsTrue() {
//        return isStatus;
//    }

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
