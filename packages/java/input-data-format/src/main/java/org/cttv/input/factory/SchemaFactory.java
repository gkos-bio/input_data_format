package org.cttv.input.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import org.cttv.input.model.EvidenceString;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public class SchemaFactory {

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper wrapper = new SchemaFactoryWrapper();
        mapper.acceptJsonFormatVisitor(EvidenceString.class, wrapper);
        JsonSchema schema = wrapper.finalSchema();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(schema));
    }

}
