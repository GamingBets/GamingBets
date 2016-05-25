package de.blogsiteloremipsum.gamingbets.parser;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.blogsiteloremipsum.gamingbets.classes.User;


/**
 * Created by Andre on 21.04.2016.
 */
public class UserFromJSONParser {

    public static ArrayList<User> parseFeed(String content){

        try {

            ArrayList<User> userList = new ArrayList<>();
            JSONArray ar;
                JSONObject parentobj = new JSONObject(content);
                ar = parentobj.getJSONArray("user");

            for (int i = 0; i < ar.length(); i++) {

                JSONObject obj = ar.getJSONObject(i);
                User user = new User();
                user.setId(obj.getInt("id"));
                user.setUserName(obj.getString("userName"));
                user.setPassword(obj.getString("password"));
                user.setLoggedIn(obj.getBoolean("loggedIn"));
                user.setAdmin(obj.getBoolean("admin"));
                user.setActive(obj.getBoolean("active"));
                user.setEmail(obj.getString("email"));
                user.setScore(obj.getInt("score"));

                userList.add(user);
            }

            return userList;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
