package de.blogsiteloremipsum.gamingbets.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import de.blogsiteloremipsum.gamingbets.R;
import de.blogsiteloremipsum.gamingbets.classes.Globals;
import de.blogsiteloremipsum.gamingbets.classes.User;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Globals g = (Globals) getApplication();
        User u = g.getUser();


        TextView username = (TextView) findViewById(R.id.txt_userName);
        username.setText(u.getUserName());

        TextView score = (TextView) findViewById(R.id.txt_score_value);
        //score.setText("60");
        int value = u.getScore();
        score.setText(""+value);


    }
}
