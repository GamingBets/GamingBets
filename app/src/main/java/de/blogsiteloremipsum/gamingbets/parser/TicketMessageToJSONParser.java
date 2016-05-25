package de.blogsiteloremipsum.gamingbets.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.blogsiteloremipsum.gamingbets.classes.TicketMessages;

/**
 * Created by Andre on 24.05.2016.
 */
public class TicketMessageToJSONParser {

    public static String parseFeed(TicketMessages message) {

        try{
            ObjectMapper mapper = new ObjectMapper();
            String jsonmessage = mapper.writeValueAsString(message);
            System.out.println(jsonmessage);
            return jsonmessage;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
