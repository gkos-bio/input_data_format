package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@JsonInclude(Include.NON_NULL)
public class Provenance {

    private Date dateAsserted;
    private boolean isAssociated;
    private ProvenanceType type;
    private List<String> evidenceCodes;
    private ProvenanceUrls urls;
    private AssociationScore associationScore;
    private ExperimentalEvidenceSpecific experimentalEvidenceSpecific;

    @JsonProperty("date_asserted")
    public Date getDateAsserted() {
        return dateAsserted;
    }

    @JsonProperty("is_associated")
    public boolean isAssociated() {
        return isAssociated;
    }

    public ProvenanceType getType() {
        return type;
    }

    @JsonProperty("evidence_codes")
    public List<String> getEvidenceCodes() {
        return evidenceCodes;
    }

    public ProvenanceUrls getUrls() {
        return urls;
    }

    @JsonProperty("associated_score")
    public AssociationScore getAssociationScore() {
        return associationScore;
    }

    @JsonProperty("experimental_evidence_specific")
    public ExperimentalEvidenceSpecific getExperimentalEvidenceSpecific() {
        return experimentalEvidenceSpecific;
    }
}
