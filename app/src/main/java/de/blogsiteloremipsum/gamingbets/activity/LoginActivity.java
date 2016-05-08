package de.blogsiteloremipsum.gamingbets.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import de.blogsiteloremipsum.gamingbets.R;
import de.blogsiteloremipsum.gamingbets.classes.User;
import de.blogsiteloremipsum.gamingbets.classes.Globals;
import de.blogsiteloremipsum.gamingbets.communication.clientREST.LocalClient;
import de.blogsiteloremipsum.gamingbets.communication.old.client.LocalClientSocket;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public boolean LoginAttemptOnClick(View v) {
        EditText UsernameEdit = (EditText) findViewById(R.id.mail);
        EditText PwEdit = (EditText) findViewById(R.id.password);
        if(UsernameEdit.getText().toString().equals("")){
            Toast.makeText(LoginActivity.this, "Enter a UserName!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(PwEdit.getText().toString().equals("")){
            Toast.makeText(LoginActivity.this, "Enter a Password!", Toast.LENGTH_SHORT).show();
            return false;
        }
        Globals g = (Globals) getApplication();
        User u = g.getUser();
        u.setUserName(UsernameEdit.getText().toString());
        u.setPassword(PwEdit.getText().toString());
        new LoginTask().execute(u);
        return true;
    }

    public boolean login(User user) {
        //Globals g = (Globals) getApplication();
        //LocalClientSocket client = g.getClient();
        LocalClient client = new LocalClient();
        return client.login(user);
    }

    //Eingabe, Abfrage while, Abfrage After
    private class LoginTask extends AsyncTask<User, Void, Boolean> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Boolean doInBackground(User... params) {

            //For debugging
            Log.d("Login Atempt", "Attempt started");
            if (login(params[0])) {
                Globals g = (Globals) getApplication();
                User u = g.getUser();
                g.setUser(g.getClient().getUser(u.getUserName()));
                u.setLoggedin(true);
                return true;
            } else {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean b) {
            TextView Status = (TextView) findViewById(R.id.Status);
            if (b) {

                Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                Intent intentUser = new Intent(getApplicationContext(), UserLandingActivity.class);
                startActivity(intentUser);
            } else {
                Status.setText("Login unsuccessful");
                Status.setVisibility(View.VISIBLE);
            }
        }
    }

}