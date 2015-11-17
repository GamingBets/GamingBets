package de.blogsiteloremipsum.gamingbets;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

import de.blogsiteloremipsum.gamingbets.classes.Bet;
import de.blogsiteloremipsum.gamingbets.classes.User;
import de.blogsiteloremipsum.gamingbets.server.ServerMethods;

public class LoginActivity extends AppCompatActivity implements ServerMethods {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public boolean LoginAttempt(View v){
        Button b=(Button) findViewById(R.id.LoginButton);
        EditText MailEdit = (EditText) findViewById(R.id.mail);
        EditText PwEdit = (EditText) findViewById(R.id.password);
        TextView Status = (TextView) findViewById(R.id.Status);
        User u = (User)getApplication();
        u.setEmail(MailEdit.getText().toString());
        u.setPassword(PwEdit.getText().toString());
        if(login(u)){
            Status.setText("Login successful");
            Status.setVisibility(View.VISIBLE);
            return true;
        }
        else{
            Status.setText("Login unsuccessful");
            Status.setVisibility(View.VISIBLE);
            return false;
        }
    }

    @Override
    public boolean login(User user) {
        if((user.getEmail().toString().equals("admin"))&&(user.getPassword().toString().equals("pw"))){
            return true;
        }
        else{
            return false;
        }
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
}
