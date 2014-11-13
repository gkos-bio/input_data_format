package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@JsonInclude(Include.NON_NULL)
public class ProvenanceAuthor {
    private String name;
    private String organization;
    private String email;

    public ProvenanceAuthor(String name, String organization, String email) {
        this.name = name;
        this.organization = organization;
        this.email = email;
    }

    private ProvenanceAuthor(Builder builder) {
        name = builder.name;
        organization = builder.organization;
        email = builder.email;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public String getOrganization() {
        return organization;
    }

    public String getEmail() {
        return email;
    }

    public static final class Builder {
        private String name;
        private String organization;
        private String email;

        private Builder() {
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withOrganization(String organization) {
            this.organization = organization;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public ProvenanceAuthor build() {
            return new ProvenanceAuthor(this);
        }
    }
}
