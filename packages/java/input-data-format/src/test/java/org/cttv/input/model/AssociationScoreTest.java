package org.cttv.input.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;
import org.junit.*;

import java.io.IOException;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public class AssociationScoreTest {
    private AssociationScore score;

    @Before
    public void before(){
        this.score = new AssociationScore(0.5, 0.5);
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonSchemaGenerator generator = new JsonSchemaGenerator(mapper);
            JsonSchema jsonSchema = generator.generateSchema(EvidenceString.class);
            System.out.println(jsonSchema.get$schema());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


    }

    @After
    public void after(){
        this.score = null;
    }

    @Test
    public void testAssociationScore() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            String expected = "{\"probability\":0.5,\"p-value\":0.5}";
//            String expected = "{\"probability\":0.5,\"p-Value\":0.5}";
            String result = mapper.writeValueAsString(this.score);
            Assert.assertEquals("JSON should be equal", expected, result);
        } catch (IOException e) {
//            Assert.ass
            e.printStackTrace();
        }


    }

}
