package de.blogsiteloremipsum.gamingbets.parser;

import org.json.JSONException;
import org.json.JSONObject;

import de.blogsiteloremipsum.gamingbets.classes.Sc2Matches;
import de.blogsiteloremipsum.gamingbets.classes.Sc2Tournament;

/**
 * Created by Andre on 16.05.2016.
 */
public class Sc2TournamentSpecJSONParser {

    public static Sc2Tournament parseFeed(String content) {

        try {
            JSONObject obj = new JSONObject(content);

            Sc2Tournament tournament = new Sc2Tournament();
            tournament.setIdtournament(obj.getInt("idtournament"));
            tournament.setName(obj.getString("name"));
            tournament.setLocation(obj.getString("location"));
            tournament.setLink(obj.getString("link"));
            tournament.setStatus(obj.getInt("status"));

            return tournament;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
