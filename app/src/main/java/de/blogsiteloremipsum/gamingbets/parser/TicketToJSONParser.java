package de.blogsiteloremipsum.gamingbets.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import de.blogsiteloremipsum.gamingbets.classes.Ticket;

/**
 * Created by Andre on 09.05.2016.
 */
public class TicketToJSONParser {

    public static String parseFeed(Ticket ticket) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonTicket = mapper.writeValueAsString(ticket);
            System.out.println(jsonTicket);
            return jsonTicket;

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
