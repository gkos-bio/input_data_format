package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@JsonInclude(Include.NON_NULL)
public class BiologicalSubject {

    private List<String> about;

    private BiologicalSubjectProperties properties;

    public BiologicalSubject(List<String> about, BiologicalSubjectProperties properties) {
        this.about = about;
        this.properties = properties;
    }

    @JsonProperty(value = "about", required = true)
    @JsonPropertyDescription("An array of identifier.org URIs. For example, if depicting a CTTV target, which is a heteropolymeric protein complex, use: [http://identifiers.org/uniprot/P251200, http://identifiers.org/uniprot/P35348, http://identifiers.org/uniprot/P35368]")
    public List<String> getAbout() {
        return about;
    }

    @JsonProperty(value = "properties", required = false)
    public BiologicalSubjectProperties getProperties() {
        return properties;
    }
}
