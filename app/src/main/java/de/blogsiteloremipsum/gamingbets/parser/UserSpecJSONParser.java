package de.blogsiteloremipsum.gamingbets.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.blogsiteloremipsum.gamingbets.classes.User;

/**
 * Created by Andre on 03.05.2016.
 */
public class UserSpecJSONParser {
    public static User parseFeed(String content) {

        try {
            JSONObject obj = new JSONObject(content);

            User user = new User();
            user.setID(obj.getInt("ID"));
            user.setUserName(obj.getString("userName"));
            user.setPassword(obj.getString("password"));
            user.setBets(obj.getString("bets"));
            user.setLoggedin(obj.getBoolean("loggedIn"));
            user.setAdmin(obj.getBoolean("admin"));
            user.setActive(obj.getBoolean("active"));

//                SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//                Date parsed = format.parse(obj.getString("dob"));
//                java.sql.Date sql = new java.sql.Date(parsed.getTime());
//                user.setDob(sql);

            user.setEmail(obj.getString("email"));
            user.setScore(obj.getInt("score"));

            return user;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        } /*catch (ParseException e) {
            e.printStackTrace();
            return null;
        }*/
    }
}
