package de.blogsiteloremipsum.gamingbets.communication.clientREST;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.blogsiteloremipsum.gamingbets.classes.Bet;
import de.blogsiteloremipsum.gamingbets.classes.Ticket;
import de.blogsiteloremipsum.gamingbets.classes.UnregisteredUser;
import de.blogsiteloremipsum.gamingbets.classes.User;
import de.blogsiteloremipsum.gamingbets.communication.HttpManager;
import de.blogsiteloremipsum.gamingbets.communication.RequestPackage;
import de.blogsiteloremipsum.gamingbets.parser.UserJSONParser;
import de.blogsiteloremipsum.gamingbets.parser.UserSpecJSONParser;
import de.blogsiteloremipsum.gamingbets.parser.UserToJSONParser;

/**
 * Created by Andre on 21.04.2016.
 */
public class LocalClient implements ClientMethods {


    @Override
    public boolean login(User user) {

        User testee = getUser(user.getUserName());
        if(testee==null){
            return false;
        }
        String generatedPassword = "";
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(user.getPassword().getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        if (generatedPassword.equals(testee.getPassword())){
            return true;
        }
        return false;

    }

    @Override
    public boolean logout(User user) {
        return false;
    }

    @Override
    public boolean register(String username, String email, String pw) {
        String generatedPassword = "";
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(pw.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        UnregisteredUser u = new UnregisteredUser(username, email, generatedPassword);
        RequestPackage p = new RequestPackage();
        p.setUri("/users");
        p.setMethod("POST");
        p.setUser(UserToJSONParser.parseFeed(u));

        HttpManager.getData(p);

        return true;
    }

    @Override
    public boolean edit(User user) {
        RequestPackage p = new RequestPackage();
        p.setUri("/users");
        p.setMethod("PUT");
        p.setUser(UserToJSONParser.parseFeed(user));
        p.setParam("id", ""+user.getID());

        HttpManager.getData(p);

        return true;
    }

    @Override
    public boolean editAdmin(User user) {
        return false;
    }


    @Override
    public boolean placeBet(Bet bet) {
        return false;
    }

    @Override
    public boolean sendTicket(Ticket ticket) {
        return false;
    }

    @Override
    public boolean setStatus(Ticket ticket) {
        return false;
    }

    @Override
    public ArrayList<User> getUsers() {
        return null;
    }

    @Override
    public User getUser(String userName) {
        RequestPackage p = new RequestPackage();
        p.setUri("/users/name");
        p.setParam("id", ""+userName);
        p.setMethod("GET");
        String content = HttpManager.getData(p);
        if(content!=null){
            User u = UserSpecJSONParser.parseFeed(content);
            System.out.println(content+"?????????????????");
            System.out.println(u.isAdmin()+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            return u;
        }
        return null;
    }


    @Override
    public ArrayList<Ticket> getTickets() {
        return null;
    }

    @Override
    public ArrayList<User> getLeaderboard() {
        RequestPackage p = new RequestPackage();
        p.setUri("/users");
        p.setMethod("GET");
        String content = HttpManager.getData(p);
        return UserJSONParser.parseFeed(content);
    }


}
