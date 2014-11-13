package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@JsonInclude(Include.NON_NULL)
public class ProvenanceDatabaseXRef {
    private String id;
    private String url;
    private String version;

    public ProvenanceDatabaseXRef(String id, String url, String version) {
        this.id = id;
        this.url = url;
        this.version = version;
    }

    private ProvenanceDatabaseXRef(Builder builder) {
        id = builder.id;
        url = builder.url;
        version = builder.version;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getVersion() {
        return version;
    }

    public static final class Builder {
        private String id;
        private String url;
        private String version;

        private Builder() {
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder withVersion(String version) {
            this.version = version;
            return this;
        }

        public ProvenanceDatabaseXRef build() {
            return new ProvenanceDatabaseXRef(this);
        }
    }
}
