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
    private ExperimentSpecific experimentSpecific;

    public Evidence(Date dateAsserted,
                    boolean isAssociated,
                    ProvenanceType provenanceType,
                    List<String> evidenceCodes,
                    ProvenanceUrls urls){
        this(dateAsserted, isAssociated, provenanceType, evidenceCodes, urls, new AssociationScore());
    }

    public Evidence(Date dateAsserted,
                    boolean isAssociated,
                    ProvenanceType provenanceType,
                    List<String> evidenceCodes,
                    ProvenanceUrls urls,
                    AssociationScore associationScore) {
        this.dateAsserted = dateAsserted;
        this.isAssociated = isAssociated;
        this.provenanceType = provenanceType;
        this.evidenceCodes = evidenceCodes;
        this.urls = urls;
        this.associationScore = associationScore;
    }

    @JsonProperty(value = "date_asserted", required = true)
    public String getDateAsserted() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        return formatter.format(dateAsserted);
    }

    @JsonProperty(value = "is_associated", required = true)
    public boolean isAssociated() {
        return isAssociated;
    }

    @JsonProperty(value = "provenance_type", required = true)
    public ProvenanceType getProvenanceType() {
        return provenanceType;
    }

    @JsonProperty(value = "evidence_codes", required = true)
    public List<String> getEvidenceCodes() {
        return evidenceCodes;
    }

    @JsonProperty(required = true)
    public ProvenanceUrls getUrls() {
        return urls;
    }

    @JsonProperty(value = "association_score", required = true)
    public AssociationScore getAssociationScore() {
        return associationScore;
    }

    @JsonProperty(required = true)
    public List<EvidenceString> getChain() {
        return chain;
    }

    @JsonProperty(value = "experiment_specific", required = true)
    public ExperimentSpecific getExperimentSpecific() {
        return experimentSpecific;
    }

    public void setExperimentSpecific(ExperimentSpecific experimentSpecific) {
        this.experimentSpecific = experimentSpecific;
    }

    public void setAssociationScore(AssociationScore associationScore) {
        this.associationScore = associationScore;
    }

    public void setChain(List<EvidenceString> chain) {
        this.chain = chain;
    }
}
