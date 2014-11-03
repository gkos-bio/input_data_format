package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@JsonInclude(Include.NON_NULL)
public class ProvenanceExpert {
    private boolean isTrue;
    private String statement;
    private ProvenanceAuthor author;

    public ProvenanceExpert(boolean isTrue){
        this.isTrue = isTrue;
    }

    public void setStatement(String statement){
        this.statement = statement;
    }

    public void setAuthor(ProvenanceAuthor author) {
        this.author = author;
    }

    @JsonProperty("true")
    public boolean isTrue() {
        return isTrue;
    }

    public String getStatement() {
        return statement;
    }

    public ProvenanceAuthor getAuthor() {
        return author;
    }
}
