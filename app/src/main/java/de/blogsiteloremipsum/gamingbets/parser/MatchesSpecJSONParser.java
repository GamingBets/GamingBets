package de.blogsiteloremipsum.gamingbets.parser;

import org.json.JSONException;
import org.json.JSONObject;


import de.blogsiteloremipsum.gamingbets.classes.Sc2Matches;
import de.blogsiteloremipsum.gamingbets.classes.User;

/**
 * Created by Andre on 16.05.2016.
 */
public class MatchesSpecJSONParser {
    public static Sc2Matches parseFeed(String content) {

        try {
            JSONObject obj = new JSONObject(content);

            Sc2Matches match = new Sc2Matches();
            match.setId(obj.getInt("id"));
            match.setResult(obj.getInt("result"));
            match.setPlayer1(obj.getInt("player1"));
            match.setPlayer2(obj.getInt("player2"));
            match.setTournament_id(obj.getInt("tournament_id"));
            match.setFinished(obj.getBoolean("finished"));

            return match;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
