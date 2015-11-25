package de.blogsiteloremipsum.gamingbets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import de.blogsiteloremipsum.gamingbets.classes.globals;
import de.blogsiteloremipsum.gamingbets.classes.User;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        globals g = (globals)getApplication();
        User u = new User();
        g.setUser(u);
    }

    public void buttonOnClick(View v){
        Button b=(Button) v;
        b.setText("Boom!");
        TextView myTextView=(TextView) findViewById(R.id.textView);
        globals g  = (globals)getApplication();
        User u = g.getUser();
        u.setUserName("Bob");
        myTextView.setText(g.getUser().getUserName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
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
            Intent intentTicket = new Intent(getApplicationContext(), TicketActivity.class);
            startActivity(intentTicket);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
