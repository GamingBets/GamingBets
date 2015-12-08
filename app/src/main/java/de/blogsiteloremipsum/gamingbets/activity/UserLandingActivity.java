package de.blogsiteloremipsum.gamingbets.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import de.blogsiteloremipsum.gamingbets.R;
import de.blogsiteloremipsum.gamingbets.classes.Globals;

public class UserLandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_landing);
        Globals g = (Globals) getApplication();
        if (g.getUser().getUserName()==null || g.getUser().getUserName().equalsIgnoreCase("")) {
            g.getUser().setUserName("User");
        }
        String username = g.getUser().getUserName();
        TextView WelcomeMessage = (TextView) findViewById(R.id.WelcomeMessage);
        WelcomeMessage.setText("Hello "+username+"! What would you like to do?");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Globals g = (Globals) getApplication();
        if (g.getUser().isAdmin()) {
            getMenuInflater().inflate(R.menu.menu_admin, menu);
            return true;
        }
        else{
            getMenuInflater().inflate(R.menu.menu_user, menu);
            return true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_Ticket){
            Intent intentTicket = new Intent(getApplicationContext(), TicketUserActivity.class);
            startActivity(intentTicket);
            return true;
        }
        if (id == R.id.action_Leaderboard) {
            Intent intentLeaderboard = new Intent(getApplicationContext(), LeaderboardActivity.class);
            startActivity(intentLeaderboard);
            return true;
        }
        if (id == R.id.action_UserEdit){
            Intent intentEditUser = new Intent(getApplicationContext(), EditUserActivity.class);
            startActivity(intentEditUser);
            return true;
        }
        if (id == R.id.action_SignIn){
            Globals g = (Globals) getApplication();
            g.setUser(null);
            Intent intentLogin = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intentLogin);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
