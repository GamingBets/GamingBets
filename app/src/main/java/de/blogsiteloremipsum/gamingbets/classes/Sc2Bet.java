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

    public int getIdsc2Bet() {
        return idsc2Bet;
    }

    public void setIdsc2Bet(int idsc2_bet) {
        this.idsc2Bet = idsc2_bet;
    }

    public Sc2AvailableBets getBet_id() {
        return betId;
    }

    public void setBetId(Sc2AvailableBets betId) {
        this.betId = betId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int user_id) {
        this.userId = user_id;
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
}
