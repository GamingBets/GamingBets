package de.blogsiteloremipsum.gamingbets.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.blogsiteloremipsum.gamingbets.classes.Sc2AvailableBets;
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
            ar = parentobj.getJSONArray("sc2Bet");

            for (int i = 0; i < ar.length(); i++) {

                JSONObject obj = ar.getJSONObject(i);
                Sc2Bet bet = new Sc2Bet();
                bet.setIdsc2Bet(obj.getInt("idsc2Bet"));

                ArrayList<Sc2AvailableBets> bets = AvailableBetsSpecJSONParser.parseFeed(obj.getJSONObject("betId").toString());
                bet.setBetId(bets.get(0));
                bet.setUserId(obj.getInt("userId"));
                bet.setBettedResult(obj.getInt("bettedResult"));
                bet.setStatus(obj.getInt("status"));
                if(obj.getInt("processed")==0){
                    bet.setProcessed(false);
                }else{
                    bet.setProcessed(true);
                }


                betList.add(bet);
            }

            return betList;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
