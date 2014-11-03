package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@JsonInclude(Include.NON_NULL)
public class ProvenanceUrls {
    private String biojs;
    private List<LinkOut> linkouts;

    public ProvenanceUrls() {
        //Nothing here
    }

    public void setBiojs(String biojs) {
        this.biojs = biojs;
    }

    public boolean addLinkOout(LinkOut linkOut){
        if(linkOut==null) return false;
        if(linkouts==null){
            linkouts = new LinkedList<LinkOut>();
        }
        return linkouts.add(linkOut);
    }

    public String getBiojs() {
        return biojs;
    }

    public List<LinkOut> getLinkouts() {
        return linkouts;
    }
}
