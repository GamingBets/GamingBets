package de.blogsiteloremipsum.gamingbets.classes;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by quint_000 on 12.11.2015.
 */
public class User implements Serializable {

    private static final long serialVersionUID = 6529685098267757690L;

    private int iD;
    private String userName;
    private String email;
    private String password;
    private String bets;
    private boolean admin;
    private boolean active;
    private int score;

    public User(int iD, String userName, String email, String password, String bets, boolean admin, boolean active) {
        this.iD = iD;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.bets = bets;
        this.admin = admin;
        this.active = active;
    }
    public User(String userName, String password){
        this.iD = -1;
        this.userName = userName;
        this.email = "";
        this.password = password;
        this.bets = "";
        this.admin = false;
        this.active = false;
    }

    public User(){
        iD = -1;
        userName = "";
        email = "";
        password = "";
        bets = "";
        admin = false;
        active = false;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getID() {
        return iD;
    }

    public void setID(int iD) {
        this.iD = iD;
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

    public String getBets() {
        return bets;
    }

    public boolean isAdmin() {
        return admin;
    }

    public boolean isLoggedin() {
        return active;
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

    public void setBets(String bets) {
        this.bets = bets;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void setLoggedin(boolean loggedin) {
        this.active = loggedin;
    }
}
