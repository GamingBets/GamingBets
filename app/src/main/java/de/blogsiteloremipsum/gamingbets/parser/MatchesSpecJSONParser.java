package de.blogsiteloremipsum.gamingbets.parser;

import org.json.JSONException;
import org.json.JSONObject;


import de.blogsiteloremipsum.gamingbets.classes.Sc2Matches;
import de.blogsiteloremipsum.gamingbets.classes.Sc2Player;
import de.blogsiteloremipsum.gamingbets.classes.Sc2Tournament;

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

            Sc2Player player1 = new Sc2Player();
            Sc2Player player2 = new Sc2Player();
            Sc2Tournament tournament = new Sc2Tournament();

            JSONObject matchobj = obj.getJSONObject("matchId");
            JSONObject player1obj = matchobj.getJSONObject("player1");
            JSONObject player2obj = matchobj.getJSONObject("player2");
            JSONObject tournamentobj = matchobj.getJSONObject("tournamentId");

            player1.setIngameName(player1obj.getString("ingameName"));
            player2.setIngameName(player1obj.getString("ingameName"));
            match.setId(matchobj.getInt("id"));
            match.setPlayer1(player1);
            match.setPlayer2(player2);
            tournament.setName(tournamentobj.getString("name"));
            tournament.setLocation(tournamentobj.getString("location"));
            match.setTournamentId(tournament);
            match.setFinished(obj.getBoolean("finished"));

            return match;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
