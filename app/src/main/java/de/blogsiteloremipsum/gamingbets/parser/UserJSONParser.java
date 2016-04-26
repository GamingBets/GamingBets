package de.blogsiteloremipsum.gamingbets.parser;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.blogsiteloremipsum.gamingbets.classes.User;


/**
 * Created by Andre on 21.04.2016.
 */
public class UserJSONParser {

    public static ArrayList<User> parseFeed(String content){

        try{
            JSONArray ar = new JSONArray(content);
            ArrayList<User> userList = new ArrayList<>();

            for(int i = 0; i<ar.length(); i++){

                JSONObject obj = ar.getJSONObject(i);
                User user = new User();

                user.setID(obj.getInt("iD"));
                user.setUserName(obj.getString("userName"));
                user.setPassword(obj.getString("password"));
                user.setBets(obj.getString("bets"));
                user.setLoggedin(obj.getBoolean("loggedIn"));
                user.setAdmin(obj.getBoolean("admin"));
                user.setActive(obj.getBoolean("active"));

                SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
                Date parsed = format.parse(obj.getString("dob"));
                java.sql.Date sql = new java.sql.Date(parsed.getTime());
                user.setDob(sql);

                user.setEmail(obj.getString("email"));
                user.setScore(obj.getInt("score"));

                userList.add(user);
            }

            return userList;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }
}
