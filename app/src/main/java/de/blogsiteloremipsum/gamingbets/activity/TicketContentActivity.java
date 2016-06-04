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
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import de.blogsiteloremipsum.gamingbets.R;
import de.blogsiteloremipsum.gamingbets.classes.Globals;
import de.blogsiteloremipsum.gamingbets.classes.Ticket;
import de.blogsiteloremipsum.gamingbets.classes.TicketMessages;
import de.blogsiteloremipsum.gamingbets.classes.User;
import de.blogsiteloremipsum.gamingbets.communication.clientREST.LocalClient;

public class TicketContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_content);

        Globals g = (Globals) getApplication();
        User u = g.getUser();

        ArrayList<TicketMessages> tickets = new ArrayList<>();
        tickets = null;

        try{
            tickets = new TicketContentActivityTask().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if(tickets!=null){

            String[] ticketsArray = new String [tickets.size()];

            for(int i = 0; i<tickets.size();i++){
                try {
                    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-mm-dd H:mm:ss");
                    Date date = format1.parse(tickets.get(i).getDatetime());
                    format1 = new SimpleDateFormat("H:mm:ss");
                    String time = format1.format(date.getTime());
                    ticketsArray[i] = time +" " +new getUserTask().execute(tickets.get(i).getUserId()).get().getUserName() + ": ";
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                ticketsArray[i] += tickets.get(i).getContent();
            }

            ListAdapter availableBetsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ticketsArray);

            ListView ticketView = (ListView) findViewById(R.id.listViewBets);
            ticketView.setAdapter(availableBetsAdapter);


        }else{
            Toast.makeText(TicketContentActivity.this, "No available tickets found", Toast.LENGTH_SHORT).show();
        }
    }

    public void sendTicketMessage(View view) {

        Globals g = (Globals) getApplication();
        EditText message = (EditText) findViewById(R.id.editText_ticketMessage);
        TicketMessages newMessage = new TicketMessages();
        newMessage.setContent(message.getText().toString());
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-mm-dd H:mm:ss");
        String date = format1.format(Calendar.getInstance().getTime());
        newMessage.setDatetime(date);

        newMessage.setUserId(g.getUser().getId());
        newMessage.setTicketId(g.getTicket());

        new sendTicketTask().execute(newMessage);
        Toast.makeText(TicketContentActivity.this, "Answer sent", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), TicketContentActivity.class);
        startActivity(intent);
    }

    public void closeTicket(View view) {

        Globals g = (Globals) getApplication();
        Ticket ticket = g.getTicket();
        ticket.setStatus(0);

        new closeTicketTask().execute(ticket);

        Intent intent = new Intent(getApplicationContext(), UserLandingActivity.class);
        startActivity(intent);
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

    private class TicketContentActivityTask extends AsyncTask<Void, Void, ArrayList<TicketMessages>> {

        @Override
        protected ArrayList<TicketMessages> doInBackground(Void... params) {
            Globals g = (Globals) getApplication();

            return new LocalClient().getTicketMessages(g.getTicket().getId());
        }
    }

    private class sendTicketTask extends AsyncTask<TicketMessages, Void, Void> {

        @Override
        protected Void doInBackground(TicketMessages... params) {
            new LocalClient().sendTicketMessage(params[0]);
            return null;
        }
    }

    private class getUserTask extends AsyncTask<Integer, Void, User> {

        @Override
        protected User doInBackground(Integer... params) {
           return new LocalClient().getUser(params[0]);

        }
    }

    private class closeTicketTask extends AsyncTask<Ticket, Void, Void> {

        @Override
        protected Void doInBackground(Ticket... params) {
            new LocalClient().closeTicket(params[0]);
            return null;
        }
    }
}
