package de.blogsiteloremipsum.gamingbets.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

import de.blogsiteloremipsum.gamingbets.R;
import de.blogsiteloremipsum.gamingbets.classes.Bet;
import de.blogsiteloremipsum.gamingbets.classes.Ticket;
import de.blogsiteloremipsum.gamingbets.classes.User;
import de.blogsiteloremipsum.gamingbets.classes.Globals;
import de.blogsiteloremipsum.gamingbets.communication.client.ClientMethods;
import de.blogsiteloremipsum.gamingbets.communication.client.LocalClientSocket;

public class LoginActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public boolean LoginAttempt(View v) {
        EditText MailEdit = (EditText) findViewById(R.id.mail);
        EditText PwEdit = (EditText) findViewById(R.id.password);
        Globals g = (Globals) getApplication();
        User u = g.getUser();
        u.setUserName(MailEdit.getText().toString());
        u.setPassword(PwEdit.getText().toString());
        new LoginTask().execute(u);
        return true;
    }

    public boolean login(User user) {
        Globals g = (Globals) getApplication();
        LocalClientSocket client = g.getClient();
        return client.login(user);
    }

    private class LoginTask extends AsyncTask<User, Void, Boolean>{

        @Override
        protected void onPreExecute(){

        }

        @Override
        protected Boolean doInBackground(User... params) {
            Log.d("Login Atempt","Attempt started");
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
                Globals g = (Globals) getApplication();
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

}