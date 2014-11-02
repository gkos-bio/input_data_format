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

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getVersion() {
        return version;
    }
}
