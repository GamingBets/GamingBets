package de.blogsiteloremipsum.gamingbets.parser;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

import de.blogsiteloremipsum.gamingbets.classes.Sc2AvailableBets;
import de.blogsiteloremipsum.gamingbets.classes.Sc2Tournament;

/**
 * Created by Andre on 17.05.2016.
 */
public class Sc2TournamentFromJSONParser {

    public static ArrayList<Sc2Tournament> parseFeed(String content){
        ObjectMapper mapper = new ObjectMapper();
        try {
            ArrayList<Sc2Tournament> tournaments = mapper.readValue(content, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Sc2Tournament.class));
            return tournaments;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}
