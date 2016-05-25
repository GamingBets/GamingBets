package de.blogsiteloremipsum.gamingbets.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.blogsiteloremipsum.gamingbets.classes.Ticket;

/**
 * Created by Andre on 24.05.2016.
 */
public class TicketSpecificFromJSONParser {
    public static ArrayList<Ticket> parseFeed(String content) {

        try {

            ArrayList<Ticket> ticketList = new ArrayList<>();
            JSONArray ar;
            JSONObject obj = new JSONObject(content);
            Ticket ticket = new Ticket();
            ticket.setId(obj.getInt("id"));
            ticket.setStatus(obj.getInt("status"));
            ticket.setUserId(obj.getInt("userId"));
            ticket.setDate(obj.getString("date"));

            ticketList.add(ticket);


            return ticketList;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
