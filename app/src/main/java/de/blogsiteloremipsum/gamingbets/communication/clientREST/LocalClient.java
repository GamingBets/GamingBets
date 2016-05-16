package de.blogsiteloremipsum.gamingbets.communication.clientREST;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.blogsiteloremipsum.gamingbets.classes.Bet;
import de.blogsiteloremipsum.gamingbets.classes.Sc2AvailableBets;
import de.blogsiteloremipsum.gamingbets.classes.Sc2Bet;
import de.blogsiteloremipsum.gamingbets.classes.Sc2Tournament;
import de.blogsiteloremipsum.gamingbets.classes.Ticket;
import de.blogsiteloremipsum.gamingbets.classes.UnregisteredUser;
import de.blogsiteloremipsum.gamingbets.classes.User;
import de.blogsiteloremipsum.gamingbets.communication.HttpManager;
import de.blogsiteloremipsum.gamingbets.communication.RequestPackage;
import de.blogsiteloremipsum.gamingbets.parser.AvailableBetsFromJSONParser;
import de.blogsiteloremipsum.gamingbets.parser.AvailableBetsJSONParser;
import de.blogsiteloremipsum.gamingbets.parser.BetJSONParser;
import de.blogsiteloremipsum.gamingbets.parser.Sc2BetToJSONParser;
import de.blogsiteloremipsum.gamingbets.parser.Sc2TournamentSpecJSONParser;
import de.blogsiteloremipsum.gamingbets.parser.TicketJSONParser;
import de.blogsiteloremipsum.gamingbets.parser.TicketToJSONParser;
import de.blogsiteloremipsum.gamingbets.parser.UserFromJSONParser;
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
        System.out.println(p.getUser()+"iiiiiiiiiiiiiiiiiiiiiii");
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
        return edit(user);
    }


    @Override
    public boolean placeBet(Bet bet) {
        return false;
    }

    @Override
    public boolean sendTicket(Ticket ticket) {
        RequestPackage p = new RequestPackage();
        p.setMethod("POST");
        p.setUri("/tickets");
        p.setTicket(TicketToJSONParser.parseFeed(ticket));
        HttpManager.getData(p);
        return true;
    }

    @Override
    public boolean setStatus(Ticket ticket) {
        return false;
    }

    @Override
    public ArrayList<User> getUsers() {
        RequestPackage p = new RequestPackage();
        p.setUri("/users");
        p.setMethod("GET");

        return UserJSONParser.parseFeed(HttpManager.getData(p));
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

            return u;
        }
        return null;
    }


    @Override
    public ArrayList<Ticket> getTickets() {
        RequestPackage p = new RequestPackage();
        p.setMethod("GET");
        p.setUri("/tickets");
        return TicketJSONParser.parseFeed(HttpManager.getData(p));
    }

    @Override
    public ArrayList<User> getLeaderboard() {
        RequestPackage p = new RequestPackage();
        p.setUri("/users/getLeaderboard");
        p.setMethod("GET");
        String content = HttpManager.getData(p);
        return UserFromJSONParser.parseFeed(content);
    }

    @Override
    public ArrayList<Sc2AvailableBets> getAvailableBets() {
        RequestPackage p = new RequestPackage();
        p.setUri("/sc2availablebets/notFinished");
        p.setMethod("GET");
        String content = HttpManager.getData(p);
        ArrayList<Sc2AvailableBets> bets = AvailableBetsFromJSONParser.parseFeed(content);
        if(bets!=null){
            return bets;
        }
        return null;
    }

    @Override
    public ArrayList<Sc2Bet> getUserBets(int userId) {
        RequestPackage p = new RequestPackage();
        p.setUri("/sc2Bet/userId/"+userId);
        p.setMethod("GET");
        String content = HttpManager.getData(p);
        ArrayList<Sc2Bet> bets = BetJSONParser.parseFeed(content);
        if(bets!=null){
            return bets;
        }
        return null;
    }

    @Override
    public Sc2Tournament getTournament(int id) {
        RequestPackage p = new RequestPackage();
        p.setUri("/sc2Tournament/"+id);
        p.setMethod("GET");
        String content = HttpManager.getData(p);
        Sc2Tournament tournament = Sc2TournamentSpecJSONParser.parseFeed(content);
        if(tournament!=null){
            return tournament;
        }
        return null;
    }

    @Override
    public boolean createBet(Sc2Bet bet) {
        RequestPackage p = new RequestPackage();
        p.setUri("/sc2bet");
        p.setMethod("POST");
        p.setBet(Sc2BetToJSONParser.parseFeed(bet));
        System.out.println(p.getBet());
        HttpManager.getData(p);

        return true;
    }
}
