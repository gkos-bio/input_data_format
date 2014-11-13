package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@JsonInclude(Include.NON_NULL)
public class ProvenanceLiterature {
//    private boolean isStatus;
    private List<String> pubmedRefs;

    public ProvenanceLiterature() {
//        this.isStatus = false;
    }

    private ProvenanceLiterature(Builder builder) {
        pubmedRefs = builder.pubmedRefs;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public boolean addPubmdRef(String pubmedRef){
        if(pubmedRef==null) return false;
        if(pubmedRefs==null){
            pubmedRefs = new LinkedList<String>();
        }
//        isStatus = true;
        return pubmedRefs.add(pubmedRef);
    }

//    @JsonProperty("true")
//    public boolean getIsTrue() {
//        return isStatus;
//    }

    @JsonProperty("pubmed_refs")
    public List<String> getPubmedRefs() {
        return pubmedRefs;
    }

    public static final class Builder {
        private List<String> pubmedRefs;

        private Builder() {
        }

        public Builder withPubmedRefs(List<String> pubmedRefs) {
            this.pubmedRefs = pubmedRefs;
            return this;
        }

        public ProvenanceLiterature build() {
            return new ProvenanceLiterature(this);
        }
    }
}
