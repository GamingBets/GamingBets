package de.blogsiteloremipsum.gamingbets.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import de.blogsiteloremipsum.gamingbets.classes.Sc2Bet;

/**
 * Created by Andre on 16.05.2016.
 */
public class Sc2BetToJSONParser {
    public static String parseFeed(Sc2Bet bet) {

        ObjectMapper mapper = new ObjectMapper();
            try {
                String jsonbet = mapper.writeValueAsString(bet);
                System.out.println(jsonbet);
                return jsonbet;

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        return null;
    }
}
