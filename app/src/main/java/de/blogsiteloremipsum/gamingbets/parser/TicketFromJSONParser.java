package de.blogsiteloremipsum.gamingbets.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.util.ArrayList;

import de.blogsiteloremipsum.gamingbets.classes.Ticket;

/**
 * Created by Andre on 09.05.2016.
 */
public class TicketFromJSONParser {

    public static ArrayList<Ticket> parseFeed(String content){

        if(content.startsWith("{\"ticket\":[")) {
            try {

                ArrayList<Ticket> ticketList = new ArrayList<>();
                JSONArray ar;
                JSONObject parentobj = new JSONObject(content);
                ar = parentobj.getJSONArray("ticket");

                for (int i = 0; i < ar.length(); i++) {

                    JSONObject obj = ar.getJSONObject(i);
                    Ticket ticket = new Ticket();
                    ticket.setId(obj.getInt("id"));
                    ticket.setStatus(obj.getInt("status"));
                    ticket.setUserId(obj.getInt("userId"));
                    ticket.setDate(obj.getString("date"));

                    ticketList.add(ticket);
                }

                return ticketList;

            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }else {
            try {

                ArrayList<Ticket> ticketList = new ArrayList<>();
                JSONObject parentobj = new JSONObject(content);

                    JSONObject obj = parentobj.getJSONObject("ticket");
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
}
