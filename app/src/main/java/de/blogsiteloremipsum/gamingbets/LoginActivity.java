package de.blogsiteloremipsum.gamingbets;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

import de.blogsiteloremipsum.gamingbets.classes.Bet;
import de.blogsiteloremipsum.gamingbets.classes.Ticket;
import de.blogsiteloremipsum.gamingbets.classes.User;
import de.blogsiteloremipsum.gamingbets.classes.Globals_bla;
import de.blogsiteloremipsum.gamingbets.communication.client.ClientMethods;
import de.blogsiteloremipsum.gamingbets.communication.client.LocalClientSocket;

public class LoginActivity extends AppCompatActivity implements ClientMethods {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public boolean LoginAttempt(View v) {
        //Button b = (Button) findViewById(R.id.LoginButton);
        EditText MailEdit = (EditText) findViewById(R.id.mail);
        EditText PwEdit = (EditText) findViewById(R.id.password);
        Globals_bla g = (Globals_bla) getApplication();
        User u = g.getUser();
        u.setEmail(MailEdit.getText().toString());
        u.setPassword(PwEdit.getText().toString());
        new LoginTask().execute(u);
        return true;
    }

    private class LoginTask extends AsyncTask<User, Void, Boolean>{

        @Override
        protected void onPreExecute(){

        }

        @Override
        protected Boolean doInBackground(User... params) {
            if (login(params[0])){
                return true;
            }
            else{
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean b){
            TextView Status = (TextView) findViewById(R.id.Status);
            if(b) {
                Globals_bla g = (Globals_bla) getApplication();
                User u = g.getUser();
                u.setLoggedin(true);
                Status.setText("Login successful");
                Status.setVisibility(View.VISIBLE);
            }
            else{
                Status.setText("Login unsuccessful");
                Status.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public boolean login(User user) {
        ClientMethods client = new LocalClientSocket();
        return client.login(user);
    }

    @Override
    public boolean logout(User user) {
        return false;
    }

    @Override
    public boolean register(String username, String email, String pw, Date dob) {
        return false;
    }

    @Override
    public boolean edit(User user) {
        return false;
    }

    @Override
    public boolean editAdmin(User user) {
        return false;
    }

    @Override
    public boolean placeBet(Bet bet) {
        return false;
    }

    @Override
    public boolean sendTicket(Ticket ticket) {
        return false;
    }
}