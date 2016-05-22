package de.blogsiteloremipsum.gamingbets.parser;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import de.blogsiteloremipsum.gamingbets.classes.Sc2Matches;
import de.blogsiteloremipsum.gamingbets.classes.Sc2MatchesList;
import de.blogsiteloremipsum.gamingbets.classes.Sc2Player;
import de.blogsiteloremipsum.gamingbets.classes.Sc2TournamentList;

/**
 * Created by Felix on 22.05.2016.
 */
public class Sc2MatchesFromJSONParser {

    public static Sc2MatchesList parseFeed(String content){
        ObjectMapper mapper = new ObjectMapper();
        try {
            Sc2MatchesList matchesList = mapper.readValue(content, Sc2MatchesList.class);
            return matchesList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static Sc2MatchesList parseFeed_manually(String content){

        ArrayList<Sc2Matches> sc2Matches = new ArrayList<>();
        Sc2MatchesList list = new Sc2MatchesList();


        try {
            JSONObject obj = new JSONObject(content);
            JSONArray ar = obj.getJSONArray("sc2Matches");

            for (int i = 0; i < ar.length(); i++){
                obj = ar.getJSONObject(i);

                String player1_name, player2_name;

                player1_name = obj.getJSONObject("player1").getString("ingameName");
                player2_name = obj.getJSONObject("player2").getString("ingameName");


                Sc2Player player1 = new Sc2Player();
                player1.setIngameName(player1_name);

                Sc2Player player2 = new Sc2Player();
                player2.setIngameName(player2_name);

                int result = obj.getInt("result");

                Sc2Matches sc2match = new Sc2Matches();
                sc2match.setPlayer1(player1);
                sc2match.setPlayer2(player2);
                sc2match.setResult(result);
                sc2Matches.add(sc2match);
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

        list.setSc2Matches(sc2Matches);

        return list;
    }
}
