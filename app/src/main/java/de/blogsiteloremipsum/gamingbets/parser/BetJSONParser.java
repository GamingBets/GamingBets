package de.blogsiteloremipsum.gamingbets.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.blogsiteloremipsum.gamingbets.classes.Sc2Bet;
import de.blogsiteloremipsum.gamingbets.classes.Ticket;

/**
 * Created by Andre on 16.05.2016.
 */
public class BetJSONParser {
    public static ArrayList<Sc2Bet> parseFeed(String content){

        try {

            ArrayList<Sc2Bet> betList = new ArrayList<>();
            JSONArray ar;
            JSONObject parentobj = new JSONObject(content);
            ar = parentobj.getJSONArray("ticket");

            for (int i = 0; i < ar.length(); i++) {

                JSONObject obj = ar.getJSONObject(i);
                Sc2Bet bet = new Sc2Bet();
                bet.setIdsc2Bet(obj.getInt("idsc2_bet"));

                //bet.setBet_id(obj.getInt("bet_id"));
                bet.setUserId(obj.getInt("user_id"));
                bet.setBettedResult(obj.getInt("betted_result"));
                bet.setStatus(obj.getInt("status"));
                bet.setProcessed(obj.getBoolean("processed"));


                betList.add(bet);
            }

            return betList;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
