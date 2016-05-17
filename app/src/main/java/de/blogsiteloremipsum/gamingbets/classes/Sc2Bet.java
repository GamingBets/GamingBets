package de.blogsiteloremipsum.gamingbets.classes;

/**
 * Created by Andre on 16.05.2016.
 */
public class Sc2Bet {

    private int idsc2Bet;
    private Sc2AvailableBets betId;
    private int userId;
    private int bettedResult;
    private int status;
    private boolean processed;
    private int input;

    public int getIdsc2Bet() {
        return idsc2Bet;
    }

    public void setIdsc2Bet(int idsc2_bet) {
        this.idsc2Bet = idsc2_bet;
    }

    public Sc2AvailableBets getBetId() {
        return betId;
    }

    public void setBetId(Sc2AvailableBets betId) {
        this.betId = betId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBettedResult() {
        return bettedResult;
    }

    public void setBettedResult(int bettedResult) {
        this.bettedResult = bettedResult;
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

    public void setInput(int input){
        this.input = input;
    }

    public int getInput(){
        return this.input;
    }

}
