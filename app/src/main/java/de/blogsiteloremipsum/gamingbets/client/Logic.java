package de.blogsiteloremipsum.gamingbets.client;

import java.util.Date;

import de.blogsiteloremipsum.gamingbets.classes.Bet;
import de.blogsiteloremipsum.gamingbets.classes.User;

/**
 * Created by Felix Morsbach on 13.11.2015.
 */
public class Logic implements ClientMethods {

    ClientMethods server = new LocalClientSocket();

    public Logic() {
    }

    @Override
    public boolean login(User user) {
        return server.login(user);
    }

    @Override
    public boolean logout(User user) {
       return server.logout(user);
    }

    @Override
    public boolean register(String username, String email, String pw, Date dob) {
        return server.register(username, email, pw, dob);
    }

        // And so on....
        // @nick, dein kram

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
