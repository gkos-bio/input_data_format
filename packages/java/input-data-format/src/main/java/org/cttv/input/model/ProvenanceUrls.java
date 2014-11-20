package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@JsonInclude(Include.NON_NULL)
public class ProvenanceUrls {

    private List<LinkOut> linkouts;

    public ProvenanceUrls() {
        //Nothing here
    }

    public boolean addLinkOut(LinkOut linkOut){
        if(linkOut==null) return false;
        if(linkouts==null){
            linkouts = new LinkedList<LinkOut>();
        }
        return linkouts.add(linkOut);
    }

    @JsonProperty(value = "linkouts", required = false)
    public List<LinkOut> getLinkouts() {
        return linkouts;
    }
}
