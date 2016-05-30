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
import de.blogsiteloremipsum.gamingbets.classes.Sc2Tournament;
import de.blogsiteloremipsum.gamingbets.classes.Ticket;
import de.blogsiteloremipsum.gamingbets.classes.User;
import de.blogsiteloremipsum.gamingbets.communication.clientREST.LocalClient;

public class MyTicketsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tickets);

        Globals g = (Globals) getApplication();
        User u = g.getUser();

        ArrayList<Ticket> tickets = new ArrayList<>();
        tickets = null;

        try{
            tickets = new MyTicketsActivityTask().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if(tickets!=null){
            g.setTickets(tickets);

            String[] betsArray = new String [tickets.size()];

            for(int i = 0; i<tickets.size();i++){
                betsArray[i] = tickets.get(i).getDate();
            }

            ListAdapter availableBetsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, betsArray);

            ListView ticketView = (ListView) findViewById(R.id.listViewBets);
            ticketView.setAdapter(availableBetsAdapter);

            ticketView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Globals g = (Globals) getApplication();
                    g.setTicket(g.getTickets().get(position));
                    Intent i = new Intent(getApplicationContext(), TicketContentActivity.class);
                    startActivity(i);
                }
            });
        }else{
            Toast.makeText(MyTicketsActivity.this, "No available tickets found", Toast.LENGTH_SHORT).show();
        }
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
            Intent intentPlaceBet = new Intent(getApplicationContext(), ChooseSc2TournamentActivity.class);
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
        if(id==R.id.action_MyBets){
            Intent intentMyBets = new Intent(getApplicationContext(), MyBetsActivity.class);
            startActivity(intentMyBets);
            return true;
        }
        if(id==R.id.action_Profile){
            Intent intentProfile = new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(intentProfile);
            return true;
        }
        if(id==R.id.action_Unlock){
            Intent intentUnlock = new Intent(getApplicationContext(), UnlocksActivity.class);
            startActivity(intentUnlock);
            return true;
        }
        if(id==R.id.action_MyTickets) {
            Intent intentMyTickets = new Intent(getApplicationContext(), MyTicketsActivity.class);
            startActivity(intentMyTickets);
            return true;
        }




        return super.onOptionsItemSelected(item);
    }

    private class MyTicketsActivityTask extends AsyncTask<Void, Void, ArrayList<Ticket>>{

        @Override
        protected ArrayList<Ticket> doInBackground(Void... params) {
            Globals g = (Globals) getApplication();

            return new LocalClient().getTicketsByUserId(g.getUser().getId());
        }
    }
}
