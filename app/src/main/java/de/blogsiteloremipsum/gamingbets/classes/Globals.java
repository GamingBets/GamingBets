package de.blogsiteloremipsum.gamingbets.classes;

import android.app.Activity;
import android.app.Application;
import android.view.inputmethod.InputMethodManager;

import java.lang.reflect.Array;
import java.util.ArrayList;

import de.blogsiteloremipsum.gamingbets.communication.clientREST.LocalClient;


/**
 * Created by Niclas on 24.11.2015.
 */
public class Globals extends Application{

    private User user;
    private UnregisteredUser unregisteredUser;
    private LocalClient client;
    private Sc2Matches match;
    private Sc2AvailableBets availableBet;
    private ArrayList<Sc2AvailableBets> availableBets;
    private ArrayList<Sc2Tournament> tournaments;
    private Sc2Tournament tournament;
    private String usereditName = "";
    private ArrayList<String> newsfeed;
    private Ticket ticket;
    private ArrayList<Ticket> tickets;

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public ArrayList<String> getNewsfeed(){
        return this.newsfeed;
    }

    public void setNewsfeed(ArrayList<String> newsfeed){
        this.newsfeed = newsfeed;
    }

    public Sc2Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Sc2Tournament tournament) {
        this.tournament = tournament;
    }

    public ArrayList<Sc2AvailableBets> getAvailableBets() {
        return availableBets;
    }

    public void setAvailableBets(ArrayList<Sc2AvailableBets> availableBets) {
        this.availableBets = availableBets;
    }

    public ArrayList<Sc2Tournament> getTournaments() {
        return tournaments;
    }

    public Sc2Matches getMatch() {
        return match;
    }

    public void setMatch(Sc2Matches match) {
        this.match = match;
    }

    public Sc2AvailableBets getAvailableBet() {
        return availableBet;
    }

    public void setAvailableBet(Sc2AvailableBets availableBet) {
        this.availableBet = availableBet;
    }

    public String getUsereditName() {
        return usereditName;
    }

    public void setUsereditName(String usereditName) {
        this.usereditName = usereditName;
    }

    public void setUser (User u){
        user=u;
    }

    public User getUser(){
        return user;
    }

    public void setUnregisteredUser(UnregisteredUser u){
        unregisteredUser=u;
    }

    public UnregisteredUser getUnregisteredUser(){return unregisteredUser;}

    public void setClient(LocalClient c){
        client=c;
    }

    public LocalClient getClient(){
        return client;
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    public void setTournaments(ArrayList<Sc2Tournament> tournaments) {
        this.tournaments = tournaments;
    }
}
