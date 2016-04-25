package de.blogsiteloremipsum.gamingbets.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import de.blogsiteloremipsum.gamingbets.communication.old.client.LocalClientSocket;

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

        Date date = new Date(Calendar.getInstance().getTimeInMillis());
        Ticket ticket = new Ticket(-1, u.getID(), 2, date, null, message, u.getEmail());

        new SendTicket().execute(ticket);

    }

    private class SendTicket extends AsyncTask<Ticket, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Ticket... params) {
            Globals g = (Globals) getApplication();
            LocalClientSocket client = g.getClient();
            return client.sendTicket(params[0]);
        }

        @Override
        protected void onPostExecute(Boolean b) {
            TextView Status = (TextView) findViewById(R.id.Status);
            if (b) {
                Toast.makeText(TicketUserActivity.this, "Ticket sent", Toast.LENGTH_SHORT).show();
                Intent intentUser = new Intent(getApplicationContext(), UserLandingActivity.class);
                startActivity(intentUser);
            } else {
                Status.setText("Internal Error");
                Status.setVisibility(View.VISIBLE);
            }
        }
    }
}
