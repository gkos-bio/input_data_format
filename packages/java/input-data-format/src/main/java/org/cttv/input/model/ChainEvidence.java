package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@JsonInclude(Include.NON_NULL)
public class ChainEvidence {
    private ChainBiologicalSubject biologicalSubject;
    private ChainEvidenceProvenance provenance;
    private ChainBiologicalObject biologicalObject;

    public ChainEvidence(ChainBiologicalSubject biologicalSubject, ChainEvidenceProvenance provenance, ChainBiologicalObject biologicalObject) {
        this.biologicalSubject = biologicalSubject;
        this.provenance = provenance;
        this.biologicalObject = biologicalObject;
    }

    @JsonProperty("biological_subject")
    public ChainBiologicalSubject getBiologicalSubject() {
        return biologicalSubject;
    }

    public ChainEvidenceProvenance  getProvenance() {
        return provenance;
    }

    @JsonProperty("biological_object")
    public ChainBiologicalObject getBiologicalObject() {
        return biologicalObject;
    }
}
