package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @JsonProperty(value = "name", required = false)
    public String getName() {
        return name;
    }

    @JsonProperty(value = "organization", required = false)
    public String getOrganization() {
        return organization;
    }

    @JsonProperty(value = "email", required = false)
    @JsonFormat(pattern = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
    public String getEmail() {
        return email;
    }
}
