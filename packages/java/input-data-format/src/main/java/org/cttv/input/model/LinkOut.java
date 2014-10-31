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

    @JsonProperty("nice_name")
    public String getNiceName() {
        return niceName;
    }

    public String getUrl() {
        return url;
    }
}
