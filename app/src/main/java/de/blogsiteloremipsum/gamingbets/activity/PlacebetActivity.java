package de.blogsiteloremipsum.gamingbets.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

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
        bet.setUserId(g.getUser().getId());
        if(player1.isChecked()){
            bet.setBettedResult(1);
        }else {
            bet.setBettedResult(2);
        }
        bet.setProcessed(false);
        bet.setStatus(0);
        new PlaceBetTask().execute(bet);
        Toast.makeText(PlacebetActivity.this, "Bet placed", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getApplicationContext(), AvailableSc2Bets.class);
        startActivity(i);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Globals g = (Globals) getApplication();
        if (g.getUser().getAdmin()) {
            getMenuInflater().inflate(R.menu.menu_admin, menu);
            return true;
        }
        else{
            getMenuInflater().inflate(R.menu.menu_user, menu);
            return true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_PlaceBet){
            Intent intentPlaceBet = new Intent(getApplicationContext(), AvailableSc2Bets.class);
            startActivity(intentPlaceBet);
            return true;
        }
        if (id == R.id.action_Ticket){
            Intent intentTicket = new Intent(getApplicationContext(), TicketUserActivity.class);
            startActivity(intentTicket);
            return true;
        }
        if (id == R.id.action_Leaderboard) {
            Intent intentLeaderboard = new Intent(getApplicationContext(), LeaderboardActivity.class);
            startActivity(intentLeaderboard);
            return true;
        }
        if (id == R.id.action_UserEdit){
            Globals g = (Globals) getApplication();
            g.setUsereditName(g.getUser().getUserName());
            Intent intentEditUser = new Intent(getApplicationContext(), EditUserActivity.class);
            startActivity(intentEditUser);
            return true;
        }
        if (id == R.id.action_SignIn){
            Globals g = (Globals) getApplication();
            g.setUser(null);
            Intent intentLogin = new Intent(getApplicationContext(), Welcome.class);
            startActivity(intentLogin);
            return true;
        }
        if (id == R.id.action_ManageUser){
            Globals g = (Globals) getApplication();
            Intent intentLogin = new Intent(getApplicationContext(), ManageUserActivity.class);
            startActivity(intentLogin);
            return true;
        }

        if(id==R.id.action_TicketAnswer){
            Intent intentTicketAnswer = new Intent(getApplicationContext(), TicketAnswerActivity.class);
            startActivity(intentTicketAnswer);
            return true;
        }
        if(id==R.id.action_Logout){
            Globals g = (Globals) getApplication();
            User user = new User();
            user.setId(0);
            user.setAdmin(false);
            user.setLoggedIn(false);
            user.setUserName(null);
            g.setUser(user);

            Intent intentWelcome = new Intent(getApplicationContext(), Welcome.class);
            startActivity(intentWelcome);
            return true;
        }


        return super.onOptionsItemSelected(item);
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
