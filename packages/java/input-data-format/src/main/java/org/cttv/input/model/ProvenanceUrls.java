package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@JsonInclude(Include.NON_NULL)
public class ProvenanceUrls {
    private String biojs;
    private List<LinkOut> linkouts;

    public ProvenanceUrls(String biojs, List<LinkOut> linkouts) {
        this.biojs = biojs;
        this.linkouts = linkouts;
    }

    public String getBiojs() {
        return biojs;
    }

    public List<LinkOut> getLinkouts() {
        return linkouts;
    }
}
