package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@JsonInclude(Include.NON_NULL)
public class Evidence {

    private Date dateAsserted;
    private boolean isAssociated;
    private ProvenanceType provenanceType;
    private List<String> evidenceCodes;
    private ProvenanceUrls urls;
    private AssociationScore associationScore;
    private List<EvidenceString> chain;
    private ExperimentalEvidenceSpecific experimentalEvidenceSpecific;

    public Evidence(Date dateAsserted,
                    boolean isAssociated,
                    ProvenanceType provenanceType,
                    List<String> evidenceCodes,
                    ProvenanceUrls urls) {
//                    ExperimentalEvidenceSpecific experimentalEvidenceSpecific
        this.dateAsserted = dateAsserted;
        this.isAssociated = isAssociated;
        this.provenanceType = provenanceType;
        this.evidenceCodes = evidenceCodes;
        this.urls = urls;
//        this.experimentalEvidenceSpecific = experimentalEvidenceSpecific;
    }

    @JsonProperty("date_asserted")
    public String getDateAsserted() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        return formatter.format(dateAsserted);
    }

    @JsonProperty("is_associated")
    public boolean isAssociated() {
        return isAssociated;
    }

    @JsonProperty("provenance_type")
    public ProvenanceType getProvenanceType() {
        return provenanceType;
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

//    @JsonProperty("experimental_evidence_specific")
//    public ExperimentalEvidenceSpecific getExperimentalEvidenceSpecific() {
//        return experimentalEvidenceSpecific;
//    }

    public void setAssociationScore(AssociationScore associationScore) {
        this.associationScore = associationScore;
    }

    public List<EvidenceString> getChain() {
        return chain;
    }

    public void setChain(List<EvidenceString> chain) {
        this.chain = chain;
    }
}
