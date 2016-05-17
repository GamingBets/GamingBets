package de.blogsiteloremipsum.gamingbets.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import de.blogsiteloremipsum.gamingbets.R;
import de.blogsiteloremipsum.gamingbets.classes.Globals;

import de.blogsiteloremipsum.gamingbets.classes.User;
import de.blogsiteloremipsum.gamingbets.communication.clientREST.LocalClient;

public class LeaderboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        Globals g = (Globals) getApplication();
        User u = g.getUser();
        ArrayList<User> score = new ArrayList<>();
        score = null;

        try {
            score = new LeaderboardTask().execute(u).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if(score!=null){
            String[] leaderBoardArray = new String [score.size()];

            for (int i=0;i< score.size();i++){
                leaderBoardArray[i] = score.get(i).getUserName() + "                            " + score.get(i).getScore();
            }

            ListAdapter leaderboardAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                    leaderBoardArray);

            ListView leaderboardView = (ListView) findViewById(R.id.listView);
            leaderboardView.setAdapter(leaderboardAdapter);

            leaderboardView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    String tvShowPicked = "You selected" + String.valueOf(adapterView.getItemAtPosition(position));

                    Toast.makeText(LeaderboardActivity.this, tvShowPicked, Toast.LENGTH_SHORT).show();
                    Globals g = (Globals) getApplication();
                }
            });
        }else{
            Toast.makeText(LeaderboardActivity.this, "Kein Leaderboard gefunden", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Globals g = (Globals) getApplication();
        User u = g.getUser();
        if(u.getId()==0){
            getMenuInflater().inflate(R.menu.menu_guest, menu);
        }else if(u.getAdmin()){
            getMenuInflater().inflate(R.menu.menu_admin, menu);
        }else{
            getMenuInflater().inflate(R.menu.menu_user, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_PlaceBet) {
            Intent intentPlaceBet = new Intent(getApplicationContext(), AvailableSc2Bets.class);
            startActivity(intentPlaceBet);
            return true;
        }
        if (id == R.id.action_Ticket) {
            Intent intentTicket = new Intent(getApplicationContext(), TicketUserActivity.class);
            startActivity(intentTicket);
            return true;
        }
        if (id == R.id.action_Leaderboard) {
            Intent intentLeaderboard = new Intent(getApplicationContext(), LeaderboardActivity.class);
            startActivity(intentLeaderboard);
            return true;
        }
        if (id == R.id.action_UserEdit) {
            Globals g = (Globals) getApplication();
            g.setUsereditName(g.getUser().getUserName());
            Intent intentEditUser = new Intent(getApplicationContext(), EditUserActivity.class);
            startActivity(intentEditUser);
            return true;
        }
        if (id == R.id.action_SignIn) {
            Globals g = (Globals) getApplication();
            g.setUser(null);
            Intent intentLogin = new Intent(getApplicationContext(), Welcome.class);
            startActivity(intentLogin);
            return true;
        }
        if (id == R.id.action_ManageUser) {
            Globals g = (Globals) getApplication();
            Intent intentLogin = new Intent(getApplicationContext(), ManageUserActivity.class);
            startActivity(intentLogin);
            return true;
        }

        if (id == R.id.action_TicketAnswer) {
            Intent intentTicketAnswer = new Intent(getApplicationContext(), TicketAnswerActivity.class);
            startActivity(intentTicketAnswer);
            return true;
        }
        if (id == R.id.action_Logout) {
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

    private class LeaderboardTask extends AsyncTask<User,Void, ArrayList<User>> {
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected ArrayList<User> doInBackground(User... params) {

            //For debugging
            return new LocalClient().getLeaderboard();

        }

    }
}
