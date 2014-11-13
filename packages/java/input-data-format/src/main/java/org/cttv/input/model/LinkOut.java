package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@JsonInclude(Include.NON_NULL)
public class LinkOut {
    private String niceName;
    private String url;

    public LinkOut(String niceName, String url) {
        this.niceName = niceName;
        this.url = url;
    }

    private LinkOut(Builder builder) {
        niceName = builder.niceName;
        url = builder.url;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @JsonProperty("nice_name")
    public String getNiceName() {
        return niceName;
    }

    public String getUrl() {
        return url;
    }

    public static final class Builder {
        private String niceName;
        private String url;

        private Builder() {
        }

        public Builder withNiceName(String niceName) {
            this.niceName = niceName;
            return this;
        }

        public Builder withUrl(String url) {
            this.url = url;
            return this;
        }

        public LinkOut build() {
            return new LinkOut(this);
        }
    }
}
