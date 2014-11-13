package org.cttv.input.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * Created by eddturner on 13/11/14.
 */
public class UniProtESSanityTest {
    // logger
    private static final Logger logger = LoggerFactory.getLogger(UniProtESSanityTest.class);

    /**
     * test whether some valid json can be produced
     */
    @Test
    public void simpleJsonGenerationTest() {
        ObjectMapper mapper = new ObjectMapper();

        EvidenceString simpleUniProtEString = new EvidenceString(null, null, null);

        try {
            String jsonStr = mapper.writeValueAsString(simpleUniProtEString);
            logger.info(jsonStr);
            assertNotNull(jsonStr);
            assertTrue(jsonStr.startsWith("{"));
            assertTrue(jsonStr.endsWith("}"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * sanity test. checking whether some "about" attribute in a BiologicalSubject
     * instance exists
     */
    @Test
    public void containsBioSubjTest() {
        ObjectMapper mapper = new ObjectMapper();

        EvidenceString simpleUniProtEString = EvidenceString.newBuilder().withBiologicalSubject(
                BiologicalSubject
                        .newBuilder()
                        .withAbout(
                                new ArrayList<String>() {
                                    {
                                        add("about1");
                                    }
                                }).build())
                .build();

        try {
            String jsonStr = mapper.writeValueAsString(simpleUniProtEString);
            logger.info(jsonStr);
            assertNotNull(jsonStr);
            assertTrue(jsonStr.startsWith("{"));
            assertTrue(jsonStr.endsWith("}"));
            assertTrue(jsonStr.contains("about"));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
