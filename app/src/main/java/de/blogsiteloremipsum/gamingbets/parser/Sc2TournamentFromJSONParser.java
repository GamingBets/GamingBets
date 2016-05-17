package de.blogsiteloremipsum.gamingbets.parser;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

import de.blogsiteloremipsum.gamingbets.classes.Sc2AvailableBets;
import de.blogsiteloremipsum.gamingbets.classes.Sc2Tournament;
import de.blogsiteloremipsum.gamingbets.classes.Sc2TournamentList;

/**
 * Created by Andre on 17.05.2016.
 */
public class Sc2TournamentFromJSONParser {

    public static Sc2TournamentList parseFeed(String content){
        ObjectMapper mapper = new ObjectMapper();
        try {
            Sc2TournamentList tournaments = mapper.readValue(content, Sc2TournamentList.class);
            return tournaments;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}
