package de.blogsiteloremipsum.gamingbets.classes;

import java.util.Date;

/**
 * Created by Andre on 16.05.2016.
 */
public class Sc2Player {

    private int id;
    private int race;
    private int team;
    private Date dob;
    private int nationality;
    private String ingame_name;
    private String real_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRace() {
        return race;
    }

    public void setRace(int race) {
        this.race = race;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int getNationality() {
        return nationality;
    }

    public void setNationality(int nationality) {
        this.nationality = nationality;
    }

    public String getIngame_name() {
        return ingame_name;
    }

    public void setIngame_name(String ingame_name) {
        this.ingame_name = ingame_name;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }
}
