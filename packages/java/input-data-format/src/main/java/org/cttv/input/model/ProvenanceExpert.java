package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@JsonInclude(Include.NON_NULL)
public class ProvenanceExpert {
    private boolean status;
    private String statement;
    private ProvenanceAuthor author;

    public ProvenanceExpert(boolean status){
        this.status = status;
    }

    public void setStatement(String statement){
        this.statement = statement;
    }

    public void setAuthor(ProvenanceAuthor author) {
        this.author = author;
    }

    public boolean getStatus() {
        return status;
    }

    public String getStatement() {
        return statement;
    }

    public ProvenanceAuthor getAuthor() {
        return author;
    }
}
