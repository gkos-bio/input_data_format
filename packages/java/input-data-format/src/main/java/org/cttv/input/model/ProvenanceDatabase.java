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

    private ProvenanceDatabase(Builder builder) {
        id = builder.id;
        version = builder.version;
        setDbXref(builder.dbXref);
    }

    public static Builder newBuilder() {
        return new Builder();
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

    public static final class Builder {
        private String id;
        private String version;
        private ProvenanceDatabaseXRef dbXref;

        private Builder() {
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withVersion(String version) {
            this.version = version;
            return this;
        }

        public Builder withDbXref(ProvenanceDatabaseXRef dbXref) {
            this.dbXref = dbXref;
            return this;
        }

        public ProvenanceDatabase build() {
            return new ProvenanceDatabase(this);
        }
    }
}
