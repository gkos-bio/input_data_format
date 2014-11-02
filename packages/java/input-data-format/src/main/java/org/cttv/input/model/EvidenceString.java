package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@JsonInclude(Include.NON_NULL)
public class EvidenceString {

    private BiologicalSubject biologicalSubject;
    private Provenance provenance;
    private BiologicalObject biologicalObject;

    public EvidenceString(BiologicalSubject biologicalSubject, Provenance provenance, BiologicalObject biologicalObject) {
        this.biologicalSubject = biologicalSubject;
        this.provenance = provenance;
        this.biologicalObject = biologicalObject;
    }

    @JsonProperty("biological_subject")
    public BiologicalSubject getBiologicalSubject() {
        return biologicalSubject;
    }

    public Provenance getProvenance() {
        return provenance;
    }

    @JsonProperty("biological_object")
    public BiologicalObject getBiologicalObject() {
        return biologicalObject;
    }
}
