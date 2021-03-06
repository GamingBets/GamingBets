package de.blogsiteloremipsum.gamingbets.parser;

import org.json.JSONException;
import org.json.JSONObject;

import de.blogsiteloremipsum.gamingbets.classes.UnregisteredUser;
import de.blogsiteloremipsum.gamingbets.classes.User;

/**
 * Created by Andre on 03.05.2016.
 */
public class UserToJSONParser {
    public static String parseFeed(UnregisteredUser user) {

        try {
            JSONObject obj = new JSONObject();
            obj.put("userName", user.getUserName());
            obj.put("password", user.getPassword());
            obj.put("email", user.getEmail());
            obj.put("score", 0);
            obj.put("loggedIn", false);
            obj.put("admin", false);
            obj.put("active", true);
            obj.put("bets", "");
            obj.put("profile_pic", "-1");
            obj.put("unlocks", "0000000000");

            return obj.toString();

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        } /*catch (ParseException e) {
            e.printStackTrace();
            return null;
        }*/
    }

    public static String parseFeed(User user) {

        try {
            JSONObject obj = new JSONObject();
            obj.put("userName", user.getUserName());
            obj.put("password", user.getPassword());
            obj.put("email", user.getEmail());
            obj.put("score", user.getScore());
            obj.put("loggedIn", false);
            obj.put("admin", false);
            obj.put("active", true);
            obj.put("profilePic", user.getProfilePic());
            obj.put("unlocks", user.getUnlocks());

            return obj.toString();

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
