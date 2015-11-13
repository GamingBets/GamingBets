package de.blogsiteloremipsum.gamingbets.server;

import java.util.ArrayList;
import java.util.Date;

import de.blogsiteloremipsum.gamingbets.classes.Bet;
import de.blogsiteloremipsum.gamingbets.classes.User;

/**
 * Created by Felix Morsbach on 13.11.2015.
 */
public class Logic implements ServerMethods {

    private ArrayList<User> users;

    public Logic() {
        users = new ArrayList<>();
    }

    @Override
    public boolean login(User user) {
        //TODO overright isequal method in User!
        if (!users.contains(user)) {
            users.add(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean logout(User user) {
        if (users.contains(user)) {
            users.remove(user);
            return true;
        } else {
            return false;
        }
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
}
