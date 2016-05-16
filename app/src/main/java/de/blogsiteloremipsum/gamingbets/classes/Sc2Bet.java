package de.blogsiteloremipsum.gamingbets.classes;

/**
 * Created by Andre on 16.05.2016.
 */
public class Sc2Bet {

    private int idsc2_bet;
    private int bet_id;
    private int user_id;
    private int betted_result;
    private int status;
    private boolean processed;

    public int getIdsc2_bet() {
        return idsc2_bet;
    }

    public void setIdsc2_bet(int idsc2_bet) {
        this.idsc2_bet = idsc2_bet;
    }

    public int getBet_id() {
        return bet_id;
    }

    public void setBet_id(int bet_id) {
        this.bet_id = bet_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBetted_result() {
        return betted_result;
    }

    public void setBetted_result(int betted_result) {
        this.betted_result = betted_result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }
}
