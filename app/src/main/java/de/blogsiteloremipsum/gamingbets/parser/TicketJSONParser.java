package de.blogsiteloremipsum.gamingbets.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.blogsiteloremipsum.gamingbets.classes.Ticket;

/**
 * Created by Andre on 09.05.2016.
 */
public class TicketJSONParser {

    public static ArrayList<Ticket> parseFeed(String content){

        try {

            ArrayList<Ticket> ticketList = new ArrayList<>();
            JSONArray ar;
            JSONObject parentobj = new JSONObject(content);
            ar = parentobj.getJSONArray("ticket");

            for (int i = 0; i < ar.length(); i++) {

                JSONObject obj = ar.getJSONObject(i);
                Ticket ticket = new Ticket();
                ticket.setID(obj.getInt("ID"));
                ticket.setContent(obj.getString("content"));
                ticket.setEmail(obj.getString("email"));
                ticket.setStatus(obj.getInt("status"));
                ticket.setUserID(obj.getInt("userID"));


                ticketList.add(ticket);
            }

            return ticketList;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
