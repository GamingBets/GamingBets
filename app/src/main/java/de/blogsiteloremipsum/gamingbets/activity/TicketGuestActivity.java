package de.blogsiteloremipsum.gamingbets.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import de.blogsiteloremipsum.gamingbets.R;
import de.blogsiteloremipsum.gamingbets.classes.Globals;
import de.blogsiteloremipsum.gamingbets.classes.Ticket;
import de.blogsiteloremipsum.gamingbets.classes.TicketMessages;
import de.blogsiteloremipsum.gamingbets.communication.clientREST.LocalClient;

public class TicketGuestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_guest);
    }

    public void sendTicket(View view){

        EditText edittextMessage = (EditText) findViewById(R.id.message);
        String message = edittextMessage.getText().toString();

        EditText edittextMail = (EditText) findViewById(R.id.mail);
        String mail = edittextMail.getText().toString();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-mm-dd H:m:s");
        String date = format1.format(Calendar.getInstance().getTime());
        Ticket ticket = new Ticket();
        ticket.setUserId(0);
        ticket.setDate(date);
        ticket.setStatus(2);

        try {
            ticket =  new SendTicket().execute(ticket).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("TEEEEEEEEESSSSSSSST");
        TicketMessages messages = new TicketMessages();
        messages.setContent(message);
        messages.setDatetime(ticket.getDate());
        messages.setTicketId(ticket);
        messages.setUserId(ticket.getUserId());
        new SendTicketMessage().execute(messages);
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
                Toast.makeText(TicketGuestActivity.this, "Ticket sent", Toast.LENGTH_SHORT).show();
                Intent intentUser = new Intent(getApplicationContext(), GuestLandingActivity.class);
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
