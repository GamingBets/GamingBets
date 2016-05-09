package de.blogsiteloremipsum.gamingbets.parser;

import org.json.JSONException;
import org.json.JSONObject;

import de.blogsiteloremipsum.gamingbets.classes.Ticket;
import de.blogsiteloremipsum.gamingbets.classes.User;

/**
 * Created by Andre on 09.05.2016.
 */
public class TicketToJSONParser {

    public static String parseFeed(Ticket ticket) {

        try {
            JSONObject obj = new JSONObject();
            obj.put("content", ticket.getContent());
            obj.put("email", ticket.getEmail());
            obj.put("status", ticket.getStatus());
            obj.put("userID", ticket.getUserID());

            return obj.toString();

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
