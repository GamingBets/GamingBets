package de.blogsiteloremipsum.gamingbets.classes;

import android.app.Activity;
import android.app.Application;
import android.view.inputmethod.InputMethodManager;

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

    private String usereditName = "";

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
    }
}
