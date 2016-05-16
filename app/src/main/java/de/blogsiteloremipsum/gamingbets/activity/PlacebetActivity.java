package de.blogsiteloremipsum.gamingbets.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import de.blogsiteloremipsum.gamingbets.R;
import de.blogsiteloremipsum.gamingbets.classes.Globals;
import de.blogsiteloremipsum.gamingbets.classes.Sc2AvailableBets;
import de.blogsiteloremipsum.gamingbets.classes.Sc2Bet;
import de.blogsiteloremipsum.gamingbets.classes.User;
import de.blogsiteloremipsum.gamingbets.communication.clientREST.LocalClient;

public class PlacebetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet_on_win);

        Globals g = (Globals) getApplication();
        User u = g.getUser();

        Sc2AvailableBets bet = g.getAvailableBet();
        TextView tournament = (TextView) findViewById(R.id.tournament_2);
        TextView desc = (TextView) findViewById(R.id.description);
        RadioButton player1 = (RadioButton) findViewById(R.id.Team1RadioButton);
        RadioButton player2 = (RadioButton) findViewById(R.id.Team2RadioButton);

        player1.setText(bet.getMatchId().getPlayer1().getIngameName());
        player2.setText(bet.getMatchId().getPlayer2().getIngameName());
        tournament.setText(bet.getMatchId().getTournamentId().getName() + " in " + bet.getMatchId().getTournamentId().getLocation());
        desc.setText(bet.getMatchId().getPlayer1().getIngameName() + " VS " + bet.getMatchId().getPlayer2().getIngameName());
    }

    public void placeBetOnClick(View v){

        RadioButton player1 = (RadioButton) findViewById(R.id.Team1RadioButton);
        RadioButton player2 = (RadioButton) findViewById(R.id.Team2RadioButton);
        Globals g = (Globals) getApplication();

        Sc2Bet bet = new Sc2Bet();
        bet.setBetId(g.getAvailableBet());
        bet.setUserId(g.getUser().getID());
        if(player1.isChecked()){
            bet.setBettedResult(1);
        }else {
            bet.setBettedResult(2);
        }
        bet.setStatus(0);
        new PlaceBetTask().execute(bet);
        Toast.makeText(PlacebetActivity.this, "Bet placed", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getApplicationContext(), AvailableSc2Bets.class);
        startActivity(i);
    }

    private class PlaceBetTask extends AsyncTask<Sc2Bet, Void, Boolean>{

        @Override
        protected Boolean doInBackground(Sc2Bet... params) {
            if(new LocalClient().createBet(params[0])){
                return true;
            }
            return false;

        }
    }


}
