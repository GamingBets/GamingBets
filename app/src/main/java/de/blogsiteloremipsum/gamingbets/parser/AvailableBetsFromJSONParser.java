package de.blogsiteloremipsum.gamingbets.parser;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

import de.blogsiteloremipsum.gamingbets.classes.Sc2AvailableBets;

/**
 * Created by Andre on 17.05.2016.
 */
public class AvailableBetsFromJSONParser {

    public static ArrayList<Sc2AvailableBets> parseFeed(String content){
        ObjectMapper mapper = new ObjectMapper();

        try{
            ArrayList<Sc2AvailableBets> bets = mapper.readValue(content, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Sc2AvailableBets.class));
            return bets;
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
