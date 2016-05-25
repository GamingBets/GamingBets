package de.blogsiteloremipsum.gamingbets.parser;

import org.json.JSONException;
import org.json.JSONObject;

import de.blogsiteloremipsum.gamingbets.classes.User;

/**
 * Created by Andre on 03.05.2016.
 */
public class UserSpecificFromJSONParser {
    public static User parseFeed(String content) {

        try {
            JSONObject obj = new JSONObject(content);

            User user = new User();
            user.setId(obj.getInt("id"));
            user.setUserName(obj.getString("userName"));
            user.setPassword(obj.getString("password"));
            user.setLoggedIn(obj.getBoolean("loggedIn"));
            user.setAdmin(obj.getBoolean("admin"));
            user.setActive(obj.getBoolean("active"));
            user.setProfilePic(obj.getInt("profilePic"));
            user.setUnlocks(obj.getString("unlocks"));
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
