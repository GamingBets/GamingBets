package de.blogsiteloremipsum.gamingbets.parser;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.blogsiteloremipsum.gamingbets.model.UserModel;

/**
 * Created by Andre on 21.04.2016.
 */
public class UserJSONParser {

    public static ArrayList<UserModel> parseFeed(String content){

        try{
            JSONArray ar = new JSONArray(content);
            ArrayList<UserModel> userModelList = new ArrayList<>();

            for(int i = 0; i<ar.length(); i++){

                JSONObject obj = ar.getJSONObject(i);
                UserModel userModel = new UserModel();

                userModel.setId(obj.getInt("iD"));
                userModel.setUserName(obj.getString("userName"));
                userModel.setPassword(obj.getString("password"));
                userModel.setBets(obj.getString("bets"));
                userModel.setLoggedIn(obj.getBoolean("loggedIn"));
                userModel.setAdmin(obj.getBoolean("admin"));
                userModel.setActive(obj.getBoolean("active"));

                SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
                Date parsed = format.parse(obj.getString("dob"));
                java.sql.Date sql = new java.sql.Date(parsed.getTime());
                userModel.setDob(sql);

                userModel.setEmail(obj.getString("email"));
                userModel.setScore(obj.getInt("score"));

                userModelList.add(userModel);
            }

            return userModelList;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }
}
