package de.blogsiteloremipsum.gamingbets.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.blogsiteloremipsum.gamingbets.classes.Ticket;
import de.blogsiteloremipsum.gamingbets.classes.TicketMessages;

/**
 * Created by Andre on 30.05.2016.
 */
public class TicketMessagesFromJSONParser {

    public static ArrayList<TicketMessages> parseFeed(String content) {

        JSONArray ar;
        JSONObject parentobj;
        ArrayList<TicketMessages> ticketList = new ArrayList<>();

        if (content.startsWith("{\"ticketMessages\":[")) {
            try {
                parentobj = new JSONObject(content);
                ar = parentobj.getJSONArray("ticketMessages");

                for (int i = 0; i < ar.length(); i++) {

                    JSONObject obj = ar.getJSONObject(i);
                    TicketMessages ticket = new TicketMessages();
                    ticket.setContent(obj.getString("content"));
                    ticket.setUserId(obj.getInt("userId"));
                    ticket.setDatetime(obj.getString("datetime"));

                    ticketList.add(ticket);
                }

                return ticketList;

            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            try {
                    JSONObject parent = new JSONObject(content);
                    JSONObject obj = parent.getJSONObject("ticketMessages");
                    TicketMessages ticket = new TicketMessages();
                    ticket.setContent(obj.getString("content"));
                    ticket.setUserId(obj.getInt("userId"));
                    ticket.setDatetime(obj.getString("datetime"));

                    ticketList.add(ticket);


                return ticketList;

            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }


        }
    }
}
