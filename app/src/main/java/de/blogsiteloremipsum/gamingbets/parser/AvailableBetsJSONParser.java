package de.blogsiteloremipsum.gamingbets.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.blogsiteloremipsum.gamingbets.classes.Sc2AvailableBets;
import de.blogsiteloremipsum.gamingbets.classes.Sc2Bet;
import de.blogsiteloremipsum.gamingbets.classes.Sc2Matches;
import de.blogsiteloremipsum.gamingbets.classes.Sc2Player;
import de.blogsiteloremipsum.gamingbets.classes.Sc2Races;
import de.blogsiteloremipsum.gamingbets.classes.Sc2Tournament;

/**
 * Created by Andre on 16.05.2016.
 */
public class AvailableBetsJSONParser {

    public static ArrayList<Sc2AvailableBets> parseFeed(String content) {

        try {
            ArrayList<Sc2AvailableBets> betList = new ArrayList<>();
            JSONArray ar = new JSONArray(content);
            //JSONObject parentobj = new JSONObject(content);
            //ar = parentobj.getJSONArray("sc2AvailableBets");

            for (int i = 0; i < ar.length(); i++) {

                JSONObject obj = ar.getJSONObject(i);
                Sc2AvailableBets bet = new Sc2AvailableBets();

                Sc2Matches match = new Sc2Matches();
                Sc2Player player1 = new Sc2Player();
                Sc2Player player2 = new Sc2Player();
                Sc2Tournament tournament = new Sc2Tournament();

                JSONObject matchobj = obj.getJSONObject("matchId");
                JSONObject player1obj = matchobj.getJSONObject("player1");
                JSONObject player2obj = matchobj.getJSONObject("player2");
                JSONObject tournamentobj = matchobj.getJSONObject("tournamentId");
                if(tournamentobj!=null){
                    tournament.setName(tournamentobj.getString("name"));
                    tournament.setLocation(tournamentobj.getString("location"));
                    tournament.setStatus(tournamentobj.getInt("status"));
                    tournament.setLink(tournamentobj.getString("link"));
                    tournament.setIdtournament(tournamentobj.getInt("idtournament"));
                    match.setTournamentId(tournament);
                }else{
                    tournament.setName("dumm");
                    tournament.setLocation("dümmer");
                    match.setTournamentId(tournament);
                }

                player1.setIngameName(player1obj.getString("ingameName"));
                player1.setId(player1obj.getInt("id"));
                JSONObject raceobj = player1obj.getJSONObject("race");
                Sc2Races race = new Sc2Races();
                race.setIdraces(raceobj.getInt("idraces"));
                race.setShortcut(raceobj.getString("shortcut"));
                race.setName(raceobj.getString("name"));
                player1.setRace(race);
                player1.setRealName(player1obj.getString("realName"));


                player2.setId(player2obj.getInt("id"));
                player2.setIngameName(player2obj.getString("ingameName"));
                raceobj = player2obj.getJSONObject("race");
                race.setIdraces(raceobj.getInt("idraces"));
                race.setShortcut(raceobj.getString("shortcut"));
                race.setName(raceobj.getString("name"));
                player2.setRace(race);
                player2.setRealName(player2obj.getString("realName"));

                match.setId(matchobj.getInt("id"));
                match.setPlayer1(player1);
                match.setPlayer2(player2);


                bet.setIdsc2AvailableBets(obj.getInt("idsc2AvailableBets"));
                bet.setMatchId(match);
                betList.add(bet);
            }

            return betList;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
