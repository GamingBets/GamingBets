package de.blogsiteloremipsum.gamingbets.server;

import java.util.Date;

import de.blogsiteloremipsum.gamingbets.classes.Bet;
import de.blogsiteloremipsum.gamingbets.classes.User;

/**
 * Created by Felix Morsbach on 13.11.2015.
 */
public class Logic implements ServerMethods {

    public Logic() {
    }

    @Override
    public boolean login(User user) {
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
}
