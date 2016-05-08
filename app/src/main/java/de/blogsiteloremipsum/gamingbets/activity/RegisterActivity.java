package de.blogsiteloremipsum.gamingbets.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.TextView;



import android.util.Log;
import android.widget.Toast;

import de.blogsiteloremipsum.gamingbets.R;
import de.blogsiteloremipsum.gamingbets.classes.Globals;
import de.blogsiteloremipsum.gamingbets.classes.UnregisteredUser;
import de.blogsiteloremipsum.gamingbets.communication.clientREST.LocalClient;
import de.blogsiteloremipsum.gamingbets.communication.old.client.LocalClientSocket;

public class RegisterActivity extends AppCompatActivity {

    static Button b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


    }

    public boolean RegisterAttempt(View v){
        EditText UserEdit = (EditText) findViewById(R.id.username);
        EditText MailEdit = (EditText) findViewById(R.id.mail);
        EditText PwEdit = (EditText) findViewById(R.id.password);
        EditText PwEdit2 = (EditText) findViewById(R.id.password2);
        TextView Status = (TextView) findViewById(R.id.Status);

        if(UserEdit.getText().toString().equals("")){
            Toast.makeText(RegisterActivity.this, "Enter a Username!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(MailEdit.getText().toString().equals("")){
            Toast.makeText(RegisterActivity.this, "Enter an E-Mail address!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (PwEdit.getText().toString().compareTo(PwEdit2.getText().toString())!=0) {
            Toast.makeText(RegisterActivity.this, "Passwords don´t match!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(PwEdit.getText().toString().equals("") || PwEdit2.toString().equals("")){
            Toast.makeText(RegisterActivity.this, "Enter a Password!", Toast.LENGTH_SHORT).show();
            return false;
        }

        UnregisteredUser ur = new UnregisteredUser();

        ur.setUserName(UserEdit.getText().toString());
        ur.setEmail(MailEdit.getText().toString());
        ur.setPassword(PwEdit.getText().toString());

        new RegisterTask().execute(ur);

        return true;
    }

    public boolean register(UnregisteredUser ur) {
        Globals g = (Globals) getApplication();
        LocalClient client = new LocalClient();
        return client.register(ur.getUserName(),ur.getEmail(),ur.getPassword());
    }

    private class RegisterTask extends AsyncTask<UnregisteredUser, Void, Boolean>{

        @Override
        protected void onPreExecute(){

        }

        @Override
        protected Boolean doInBackground(UnregisteredUser... params){
            Log.d("Registration Attempt","Attempt started");
            if (register(params[0])){
                return true;
            }
            else{
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean b){
            TextView Status = (TextView) findViewById(R.id.Status);
            if (b){
                Toast.makeText(RegisterActivity.this, "Registered", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
            else{
                Toast.makeText(RegisterActivity.this, "Registered", Toast.LENGTH_SHORT).show();

            }
        }
    }

}
