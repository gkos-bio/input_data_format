package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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

    private ProvenanceExpert(Builder builder) {
        status = builder.status;
        setStatement(builder.statement);
        setAuthor(builder.author);
    }

    public static Builder newBuilder() {
        return new Builder();
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

    public static final class Builder {
        private boolean status;
        private String statement;
        private ProvenanceAuthor author;

        private Builder() {
        }

        public Builder withStatus(boolean status) {
            this.status = status;
            return this;
        }

        public Builder withStatement(String statement) {
            this.statement = statement;
            return this;
        }

        public Builder withAuthor(ProvenanceAuthor author) {
            this.author = author;
            return this;
        }

        public ProvenanceExpert build() {
            return new ProvenanceExpert(this);
        }
    }
}
