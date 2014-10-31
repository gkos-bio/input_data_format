package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@JsonInclude(Include.NON_NULL)
public class ProvenanceLiterature {
    private int isTrue;
    private List<Long> pubmedRefs;

    public ProvenanceLiterature(int isTrue, List<Long> pubmedRefs) {
        this.isTrue = isTrue;
        this.pubmedRefs = pubmedRefs;
    }

    @JsonProperty("true")
    public int getIsTrue() {
        return isTrue;
    }

    @JsonProperty("pubmed_refs")
    public List<Long> getPubmedRefs() {
        return pubmedRefs;
    }
}
