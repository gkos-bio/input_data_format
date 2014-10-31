package org.cttv.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;
import org.cttv.input.model.AssociationScore;
import org.cttv.input.model.ChainEvidenceProvenance;
import org.cttv.input.model.EvidenceString;
import org.junit.*;

import java.io.IOException;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public class AssociationScoreTest {
    private ChainEvidenceProvenance provenance;
    private AssociationScore score;

    @Before
    public void before(){
        this.score = new AssociationScore(0.5, 0.5);
        this.provenance = new ChainEvidenceProvenance(null, this.score);

        try {
            ObjectMapper mapper = new ObjectMapper();
            System.out.println(mapper.writeValueAsString(this.provenance));

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
