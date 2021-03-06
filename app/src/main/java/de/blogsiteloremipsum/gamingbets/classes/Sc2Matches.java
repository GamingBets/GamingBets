package de.blogsiteloremipsum.gamingbets.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Date;

/**
 * Created by Andre on 16.05.2016.
 */
public class Sc2Matches {

    private int id;
    private int result;
    private Sc2Player player1;
    private Sc2Player player2;
    private Date date;
    private String comment;
    private boolean betCreated;
    private Sc2Tournament tournamentId;
    private int type;
    private boolean finished;
    private long lastUpdated;

    public boolean isBetCreated() {
        return betCreated;
    }

/*
    public void setBetCreated(int value){
        if(value == 1) {
            this.betCreated = true;
        }else{
            this.betCreated = false;
        }
    }

 */
    public void setBetCreated(String string){
        if(string.contains("1")) {
            this.betCreated = true;
        }else{
            this.betCreated = false;
        }
    }


    @JsonIgnore
    public void setBetCreated(boolean betCreated) {
        this.betCreated = betCreated;
    }


    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Sc2Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Sc2Player player1) {
        this.player1 = player1;
    }

    public Sc2Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Sc2Player player2) {
        this.player2 = player2;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public Sc2Tournament getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Sc2Tournament tournamentId) {
        this.tournamentId = tournamentId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isFinished() {
        return finished;
    }

    @JsonIgnore
    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public void setFinished(String string){
        if(string.contains("1")) {
            this.betCreated = true;
        }else{
            this.betCreated = false;
        }
    }
}
