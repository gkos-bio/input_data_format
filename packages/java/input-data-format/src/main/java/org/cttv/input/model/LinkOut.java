package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

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

    @JsonProperty(value = "nice_name", required = false)
    @JsonPropertyDescription("A nice name for the URL e.g. 'ArrayExpress Experiment overview'")
    public String getNiceName() {
        return niceName;
    }

    @JsonProperty(value = "url", required = false)
    @JsonFormat(pattern = "^http.+")
    @JsonPropertyDescription("A foreign URL, which the user can dig into for provenance about the association.")
    public String getUrl() {
        return url;
    }
}
