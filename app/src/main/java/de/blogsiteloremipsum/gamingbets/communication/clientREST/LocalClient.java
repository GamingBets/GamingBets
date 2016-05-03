package de.blogsiteloremipsum.gamingbets.communication.clientREST;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.blogsiteloremipsum.gamingbets.classes.Bet;
import de.blogsiteloremipsum.gamingbets.classes.Ticket;
import de.blogsiteloremipsum.gamingbets.classes.User;
import de.blogsiteloremipsum.gamingbets.communication.HttpManager;
import de.blogsiteloremipsum.gamingbets.communication.RequestPackage;
import de.blogsiteloremipsum.gamingbets.parser.UserJSONParser;
import de.blogsiteloremipsum.gamingbets.parser.UserSpecJSONParser;

/**
 * Created by Andre on 21.04.2016.
 */
public class LocalClient implements ClientMethods {


    @Override
    public boolean login(User user) {
        RequestPackage p = new RequestPackage();
        p.setUri("/users/name");
        p.setParam("id", ""+user.getUserName());
        p.setMethod("GET");
        String content = HttpManager.getData(p);
        User testee = UserSpecJSONParser.parseFeed(content);
        if (user.getPassword().equals(testee.getPassword())){
            return true;
        }
        return false;

    }

    @Override
    public boolean logout(User user) {
        return false;
    }

    @Override
    public boolean register(String username, String email, String pw, Date dob) {
        return false;
    }

    @Override
    public boolean edit(User user) {
        return false;
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
        return null;
    }


    @Override
    public ArrayList<Ticket> getTickets() {
        return null;
    }

    @Override
    public ArrayList<User> getLeaderboard() {
        RequestPackage p = new RequestPackage();
        p.setUri("/users/1");
        p.setMethod("GET");
        String content = HttpManager.getData(p);
        return UserJSONParser.parseFeed(content);
    }


}
