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
//    private String biojs;
    private List<LinkOut> linkouts;

    public ProvenanceUrls() {
        //Nothing here
    }

    private ProvenanceUrls(Builder builder) {
        linkouts = builder.linkouts;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

//    public void setBiojs(String biojs) {
//        this.biojs = biojs;
//    }

    public boolean addLinkOut(LinkOut linkOut){
        if(linkOut==null) return false;
        if(linkouts==null){
            linkouts = new LinkedList<LinkOut>();
        }
        return linkouts.add(linkOut);
    }

//    public String getBiojs() {
//        return biojs;
//    }

    public List<LinkOut> getLinkouts() {
        return linkouts;
    }

    public static final class Builder {
        private List<LinkOut> linkouts;

        private Builder() {
        }

        public Builder withLinkouts(List<LinkOut> linkouts) {
            this.linkouts = linkouts;
            return this;
        }

        public ProvenanceUrls build() {
            return new ProvenanceUrls(this);
        }
    }
}
