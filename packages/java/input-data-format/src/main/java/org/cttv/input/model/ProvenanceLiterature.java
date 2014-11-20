package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class ProvenanceLiterature {
    private List<String> pubmedRefs;

    public ProvenanceLiterature() {
        //Nothing here
    }

    public boolean addPubmdRef(String pubmedRef){
        if(pubmedRef==null) return false;
        if(pubmedRefs==null){
            pubmedRefs = new LinkedList<String>();
        }
        return pubmedRefs.add(pubmedRef);
    }

    @JsonProperty("pubmed_refs")
    @JsonFormat(pattern = "http://identifiers.org/pubmed/[0-9]+$")
    //TODO: Add minItems and uniqueItems properties
    public List<String> getPubmedRefs() {
        return pubmedRefs;
    }
}
