package de.blogsiteloremipsum.gamingbets.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import de.blogsiteloremipsum.gamingbets.R;
import de.blogsiteloremipsum.gamingbets.classes.Globals;
import de.blogsiteloremipsum.gamingbets.classes.Ticket;
import de.blogsiteloremipsum.gamingbets.classes.TicketMessages;
import de.blogsiteloremipsum.gamingbets.classes.User;
import de.blogsiteloremipsum.gamingbets.communication.clientREST.LocalClient;

public class TicketUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_user);
    }

    public void sendTicket(View view) {

        Log.d("Step", "Send Ticket started!");

        EditText edittextMessage = (EditText) findViewById(R.id.message);
        String message = edittextMessage.getText().toString();

        Globals g = (Globals) getApplication();
        User u = g.getUser();

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-mm-dd H:mm:ss");
        String date = format1.format(Calendar.getInstance().getTime());
        Ticket ticket = new Ticket();
        ticket.setUserId(u.getId());
        ticket.setDate(date);
        ticket.setStatus(2);

        try {
            ticket =  new SendTicket().execute(ticket).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        TicketMessages messages = new TicketMessages();
        messages.setContent(message);
        messages.setDatetime(ticket.getDate());
        messages.setTicketId(ticket);
        messages.setUserId(ticket.getUserId());
        new SendTicketMessage().execute(messages);

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

    private class SendTicketMessage extends AsyncTask<TicketMessages, Void, Boolean> {
        @Override
        protected Boolean doInBackground(TicketMessages... params) {
            new LocalClient().sendTicketMessage(params[0]);
            return true;

        }

        @Override
        protected void onPostExecute(Boolean b){
            TextView Status = (TextView) findViewById(R.id.Status);
            if(b) {
                Toast.makeText(TicketUserActivity.this, "Ticket sent", Toast.LENGTH_LONG).show();
                Intent intentUser = new Intent(getApplicationContext(), UserLandingActivity.class);
                startActivity(intentUser);
            }
            else{
                Status.setText("Internal Error");
                Status.setVisibility(View.VISIBLE);
            }
        }
    }

    private class SendTicket extends AsyncTask<Ticket, Void, Ticket>{

        @Override
        protected Ticket doInBackground(Ticket... params) {
            Globals g = (Globals) getApplication();
            LocalClient client = g.getClient();
            return client.sendTicket(params[0]).get(0);
        }


    }
}
