package de.blogsiteloremipsum.gamingbets.classes;

import java.util.List;

/**
 * Created by quint_000 on 12.11.2015.
 */
public class User {

    private int ID;
    private String userName;
    private String email;
    private String password;
    private List<Bet> bets;
    private boolean admin;
    private boolean loggedin;

    public User(int ID, String userName, String email, String password, List<Bet> bets, boolean admin, boolean loggedin) {
        this.ID = ID;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.bets = bets;
        this.admin = admin;
        this.loggedin = loggedin;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public boolean isAdmin() {
        return admin;
    }

    public boolean isLoggedin() {
        return loggedin;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void setLoggedin(boolean loggedin) {
        this.loggedin = loggedin;
    }
}
