package de.blogsiteloremipsum.gamingbets.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import de.blogsiteloremipsum.gamingbets.R;
import de.blogsiteloremipsum.gamingbets.classes.Globals;
import de.blogsiteloremipsum.gamingbets.classes.UnregisteredUser;
import de.blogsiteloremipsum.gamingbets.classes.User;
import de.blogsiteloremipsum.gamingbets.communication.clientREST.LocalClient;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Globals g = (Globals)getApplication();
        User u = new User();
        UnregisteredUser uu = new UnregisteredUser();
        LocalClient client = new LocalClient();
        g.setClient(client);
        g.setUser(u);
        g.setUnregisteredUser(uu);
    }

    public void LoginButtonOnClick(View v){
        Intent intentLogin = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intentLogin);
    }

    public void RegisterButtonOnClick(View v){
        Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intentRegister);
    }

    public void GuestButtonOnClick(View v){
        Intent intentGuest = new Intent(getApplicationContext(), GuestLandingActivity.class);
        startActivity(intentGuest);
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_guest, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_SignIn) {
            Intent intentLogin = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intentLogin);
            return true;
        }
        if (id == R.id.action_Ticket){
            Intent intentTicket = new Intent(getApplicationContext(), TicketGuestActivity.class);
            startActivity(intentTicket);
            return true;
        }
        if (id == R.id.action_Register){
            Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intentRegister);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */
}
