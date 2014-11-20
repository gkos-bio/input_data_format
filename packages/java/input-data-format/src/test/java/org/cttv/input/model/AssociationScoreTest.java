package org.cttv.input.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public class AssociationScoreTest {
    private AssociationScore score;

    @Before
    public void before(){
        this.score = new AssociationScore(0.5, 0.5);
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
