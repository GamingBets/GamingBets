package de.blogsiteloremipsum.gamingbets.communication.clientREST;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.blogsiteloremipsum.gamingbets.classes.Bet;
import de.blogsiteloremipsum.gamingbets.classes.Ticket;
import de.blogsiteloremipsum.gamingbets.classes.User;


/**
 * Created by Felix Morsbach on 13.11.2015.
 */
public interface ClientMethods {

    boolean  login(User user);

    boolean logout(User user);

    boolean register(String username, String email, String pw);

    boolean edit(User user);

    boolean editAdmin(User user);

    boolean placeBet(Bet bet);

    boolean sendTicket(Ticket ticket);

    boolean setStatus(Ticket ticket);

    ArrayList<User> getUsers();

    User getUser(String userName);

    ArrayList<Ticket> getTickets();

    ArrayList<User> getLeaderboard();

}
