package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @JsonProperty(value = "id", required = false)
    @JsonFormat(pattern = "http://identifiers.org/.+$")
    public String getId() {
        return id;
    }

    @JsonProperty(value = "url", required = false)
    @JsonFormat(pattern = "http://identifiers.org/.+/.+$")
    public String getUrl() {
        return url;
    }

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }
}
