package org.cttv.input.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@JsonInclude(Include.NON_NULL)
public class BiologicalSubject {

    private List<String> about;

    private BiologicalSubjectProperties properties;

    public BiologicalSubject(List<String> about, BiologicalSubjectProperties properties) {
        this.about = about;
        this.properties = properties;
    }

    private BiologicalSubject(Builder builder) {
        about = builder.about;
        properties = builder.properties;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public List<String> getAbout() {
        return about;
    }

    public BiologicalSubjectProperties getProperties() {
        return properties;
    }

    public static final class Builder {
        private List<String> about;
        private BiologicalSubjectProperties properties;

        private Builder() {
        }

        public Builder withAbout(List<String> about) {
            this.about = about;
            return this;
        }

        public Builder withProperties(BiologicalSubjectProperties properties) {
            this.properties = properties;
            return this;
        }

        public BiologicalSubject build() {
            return new BiologicalSubject(this);
        }
    }
}
