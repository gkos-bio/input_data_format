package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@JsonInclude(Include.NON_NULL)
public class ChainBiologicalSubject {

    private String about;

    public ChainBiologicalSubject(String about) {
        this.about = about;
    }

    private ChainBiologicalSubject(Builder builder) {
        about = builder.about;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getAbout() {
        return about;
    }

    public static final class Builder {
        private String about;

        private Builder() {
        }

        public Builder withAbout(String about) {
            this.about = about;
            return this;
        }

        public ChainBiologicalSubject build() {
            return new ChainBiologicalSubject(this);
        }
    }
}
