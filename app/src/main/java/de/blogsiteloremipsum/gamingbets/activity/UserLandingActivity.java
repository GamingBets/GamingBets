package de.blogsiteloremipsum.gamingbets.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import de.blogsiteloremipsum.gamingbets.R;
import de.blogsiteloremipsum.gamingbets.classes.Globals;

public class UserLandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_landing);
        Globals g = (Globals) getApplication();
        g.getUser().setUserName("Bob");
        String username = g.getUser().getUserName();
        TextView WelcomeMessage = (TextView) findViewById(R.id.WelcomeMessage);
        WelcomeMessage.setText("Hello "+username+"! What would you like to do?");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        return true;
    }
}
