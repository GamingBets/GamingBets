package de.blogsiteloremipsum.gamingbets.parser;

import org.json.JSONException;
import org.json.JSONObject;

import de.blogsiteloremipsum.gamingbets.classes.Sc2Bet;

/**
 * Created by Andre on 16.05.2016.
 */
public class Sc2BetToJSONParser {
    public static String parseFeed(Sc2Bet bet) {


            try {
                JSONObject obj = new JSONObject();
                obj.put("user_id", bet.getUser_id());
                obj.put("bet_id", bet.getBet_id());
                obj.put("betted_result", bet.getBetted_result());
                obj.put("status", 0);
                obj.put("processed", 0);

                return obj.toString();

            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }

    }
}
