package de.blogsiteloremipsum.gamingbets.classes;

import java.util.Date;

/**
 * Created by Andre on 16.05.2016.
 */
public class Sc2Player {

    private int id;
    private Sc2Races race;
    private Sc2Team team;
    private Date dob;
    private Sc2Nationality nationality;
    private String ingameName;
    private String realName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sc2Races getRace() {
        return race;
    }

    public void setRace(Sc2Races race) {
        this.race = race;
    }

    public Sc2Team getTeam() {
        return team;
    }

    public void setTeam(Sc2Team team) {
        this.team = team;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Sc2Nationality getNationality() {
        return nationality;
    }

    public void setNationality(Sc2Nationality nationality) {
        this.nationality = nationality;
    }

    public String getIngameName() {
        return ingameName;
    }

    public void setIngameName(String ingameName) {
        this.ingameName = ingameName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
