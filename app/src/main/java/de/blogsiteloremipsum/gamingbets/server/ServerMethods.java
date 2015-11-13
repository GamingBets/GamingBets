package de.blogsiteloremipsum.gamingbets.server;

import java.util.Date;

import de.blogsiteloremipsum.gamingbets.classes.Bet;
import de.blogsiteloremipsum.gamingbets.classes.User;

/**
 * Created by Felix Morsbach on 13.11.2015.
 */
public interface ServerMethods {

    public boolean login (User user);

    public boolean logout(User user);

    public boolean register (String username, String email, String pw, Date dob);

    public boolean edit(User user);

    public boolean editAdmin(User user);

    public boolean placeBet(Bet bet);
}
