package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@JsonInclude(Include.NON_NULL)
public class ProvenanceType {
    private ProvenanceLiterature literature;
    private ProvenanceExpert expert;
    private ProvenanceDatabase database;

    public ProvenanceType(ProvenanceLiterature literature, ProvenanceExpert expert, ProvenanceDatabase database) {
        this.literature = literature;
        this.expert = expert;
        this.database = database;
    }

    private ProvenanceType(Builder builder) {
        literature = builder.literature;
        expert = builder.expert;
        database = builder.database;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public ProvenanceLiterature getLiterature() {
        return literature;
    }

    public ProvenanceExpert getExpert() {
        return expert;
    }

    public ProvenanceDatabase getDatabase() {
        return database;
    }

    public static final class Builder {
        private ProvenanceLiterature literature;
        private ProvenanceExpert expert;
        private ProvenanceDatabase database;

        private Builder() {
        }

        public Builder withLiterature(ProvenanceLiterature literature) {
            this.literature = literature;
            return this;
        }

        public Builder withExpert(ProvenanceExpert expert) {
            this.expert = expert;
            return this;
        }

        public Builder withDatabase(ProvenanceDatabase database) {
            this.database = database;
            return this;
        }

        public ProvenanceType build() {
            return new ProvenanceType(this);
        }
    }
}
