package de.blogsiteloremipsum.gamingbets.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.blogsiteloremipsum.gamingbets.classes.Sc2AvailableBets;
import de.blogsiteloremipsum.gamingbets.classes.Sc2Bet;

/**
 * Created by Andre on 16.05.2016.
 */
public class AvailableBetsJSONParser {

    public static ArrayList<Sc2AvailableBets> parseFeed(String content) {

        try {

            ArrayList<Sc2AvailableBets> betList = new ArrayList<>();
            JSONArray ar;
            JSONObject parentobj = new JSONObject(content);
            ar = parentobj.getJSONArray("ticket");

            for (int i = 0; i < ar.length(); i++) {

                JSONObject obj = ar.getJSONObject(i);
                Sc2AvailableBets bet = new Sc2AvailableBets();
                bet.setIdsc2_available_bets(obj.getInt("idsc2_available_bets"));
                bet.setMatch_id(obj.getInt("match_id"));

                betList.add(bet);
            }

            return betList;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
