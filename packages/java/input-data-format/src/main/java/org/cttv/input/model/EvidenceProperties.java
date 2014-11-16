package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EvidenceProperties {
    private List<EvidenceString> chain;
    private ExperimentSpecific experimentSpecific;

    public EvidenceProperties() {
        //Nothing here
    }

    @JsonProperty(value = "evidence_chain", required = false)
    public List<EvidenceString> getChain() {
        return chain;
    }

    @JsonProperty(value = "experiment_specific", required = false)
    @JsonPropertyDescription("Use this to add any string-based key:value pairs that you want to use to describe your biological object.")
    public ExperimentSpecific getExperimentSpecific() {
        return experimentSpecific;
    }

    public void setChain(List<EvidenceString> chain) {
        this.chain = chain;
    }

    public void setExperimentSpecific(ExperimentSpecific experimentSpecific) {
        this.experimentSpecific = experimentSpecific;
    }
}
