package de.blogsiteloremipsum.gamingbets.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.util.Calendar;

import de.blogsiteloremipsum.gamingbets.R;
import de.blogsiteloremipsum.gamingbets.classes.Globals;
import de.blogsiteloremipsum.gamingbets.classes.Ticket;
import de.blogsiteloremipsum.gamingbets.classes.User;
import de.blogsiteloremipsum.gamingbets.communication.client.LocalClientSocket;

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

        Date date = new Date(Calendar.getInstance().getTimeInMillis());
        Ticket ticket = new Ticket(message, mail, date);

        new SendTicket().execute(ticket);

    }

    private class SendTicket extends AsyncTask<Ticket, Void, Boolean>{

        @Override
        protected Boolean doInBackground(Ticket... params) {
            Globals g = (Globals) getApplication();
            LocalClientSocket client = g.getClient();
            return client.sendTicket(params[0]);
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
}
