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
    private boolean isTrue;
    private List<Long> pubmedRefs;

    public ProvenanceLiterature() {
        this.isTrue = false;
    }

    public boolean addPubmdRef(Long pubmedRef){
        if(pubmedRef==null) return false;
        if(pubmedRefs==null){
            pubmedRefs = new LinkedList<Long>();
        }
        isTrue = true;
        return pubmedRefs.add(pubmedRef);
    }

    @JsonProperty("true")
    public boolean getIsTrue() {
        return isTrue;
    }

    @JsonProperty("pubmed_refs")
    public List<Long> getPubmedRefs() {
        return pubmedRefs;
    }
}
