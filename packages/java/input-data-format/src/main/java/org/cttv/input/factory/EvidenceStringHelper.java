package org.cttv.input.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cttv.input.model.EvidenceString;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public abstract class EvidenceStringHelper {

    public static String getJson(EvidenceString evidenceString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(evidenceString);
    }

    public static void writeToFile(/*file and object*/){

    }
}
