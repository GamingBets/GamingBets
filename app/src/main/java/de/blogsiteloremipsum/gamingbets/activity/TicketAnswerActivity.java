package de.blogsiteloremipsum.gamingbets.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import de.blogsiteloremipsum.gamingbets.R;
import de.blogsiteloremipsum.gamingbets.classes.Globals;
import de.blogsiteloremipsum.gamingbets.classes.Ticket;
import de.blogsiteloremipsum.gamingbets.communication.clientREST.LocalClient;

public class TicketAnswerActivity extends AppCompatActivity {

    Ticket t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_answer);

        TextView messageContent = (TextView) findViewById(R.id.textViewContent);
        TextView head = (TextView) findViewById(R.id.textViewDetails);
        ArrayList<Ticket> allTickets = null;
        try {
            allTickets = new getTicketTask().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if(allTickets.size()!=0){
            t = allTickets.get(0);
            String text = "Ticket ID: " + t.getID() + " UserModel ID: " + t.getUserID() + " Date: " +t.getDate();
            head.setText(text);
            messageContent.setText(t.getContent());
        }else{
            messageContent.setText("No Tickets need to be answered");
            EditText ticketAnswer = (EditText) findViewById(R.id.editTextAnswer);
            ticketAnswer.setVisibility(View.INVISIBLE);
            Button sendAnswer = (Button) findViewById(R.id.sendAnswer);
            sendAnswer.setVisibility(View.INVISIBLE);
        }


    }

    public void sendAnswer(View view) {

        EditText ticketAnswer = (EditText) findViewById(R.id.editTextAnswer);
        String answer = ticketAnswer.getText().toString();

        t.setStatus(1);
        new sendTicketAnswerTask().execute(t);
        Intent i = new Intent(getApplicationContext(), TicketAnswerActivity.class);
        startActivity(i);
    }


    private class getTicketTask extends AsyncTask<Void, Void, ArrayList<Ticket>> {

        @Override
        protected ArrayList<Ticket> doInBackground(Void... params) {
            Globals g = (Globals) getApplication();
            LocalClient client = g.getClient();
            return client.getTickets();
        }
    }

    private class sendTicketAnswerTask extends AsyncTask<Ticket, Void, Boolean>{

        @Override
        protected Boolean doInBackground(Ticket... params) {
            Globals g = (Globals) getApplication();
            return g.getClient().setStatus(params[0]);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            Toast.makeText(TicketAnswerActivity.this, "Ticket answered", Toast.LENGTH_SHORT).show();
        }
    }
}
