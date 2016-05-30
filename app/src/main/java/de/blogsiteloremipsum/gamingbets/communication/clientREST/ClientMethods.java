package de.blogsiteloremipsum.gamingbets.communication.clientREST;

import java.util.ArrayList;

import de.blogsiteloremipsum.gamingbets.classes.Bet;
import de.blogsiteloremipsum.gamingbets.classes.Sc2AvailableBets;
import de.blogsiteloremipsum.gamingbets.classes.Sc2Bet;
import de.blogsiteloremipsum.gamingbets.classes.Sc2Matches;
import de.blogsiteloremipsum.gamingbets.classes.Sc2Tournament;
import de.blogsiteloremipsum.gamingbets.classes.Ticket;
import de.blogsiteloremipsum.gamingbets.classes.TicketMessages;
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

    ArrayList<Ticket> sendTicket(Ticket ticket);

    boolean setStatus(Ticket ticket);

    ArrayList<User> getUsers();

    User getUser(String userName);

    ArrayList<Ticket> getTickets();

    ArrayList<User> getLeaderboard();

    ArrayList<Sc2AvailableBets> getAvailableBets(int idtournament);

    ArrayList<Sc2Bet> getUserBets(int userId);

    Sc2Tournament getTournament(int id);

    boolean createBet(Sc2Bet bet);

    ArrayList<Sc2Tournament> getAllTournaments();

    ArrayList<Sc2Matches> getNewsFeed();

    ArrayList<TicketMessages> getTicketMessages(int id);

    boolean sendTicketMessage(TicketMessages message);


    ArrayList<Ticket> getTicketsByUserId(int id);

    }
