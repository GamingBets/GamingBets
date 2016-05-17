package de.blogsiteloremipsum.gamingbets.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;

import de.blogsiteloremipsum.gamingbets.R;
import de.blogsiteloremipsum.gamingbets.classes.Globals;
import de.blogsiteloremipsum.gamingbets.classes.User;
import de.blogsiteloremipsum.gamingbets.communication.clientREST.LocalClient;

public class EditUserActivity extends AppCompatActivity {

    EditText newPWEdit;
    EditText newPWConfirmEdit;
    EditText userEdit;
    EditText mailEdit;

    String op = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);


        //Get Views Components
        userEdit = (EditText) findViewById(R.id.UserEdit);
        mailEdit = (EditText) findViewById(R.id.MailEdit);
        newPWEdit = (EditText) findViewById(R.id.NewPWEdit);
        newPWConfirmEdit = (EditText) findViewById(R.id.NewPWEditConfirm);

        //Get UserModel
        Globals g = (Globals) getApplication();

        User u;

        try {

            if (g.getUsereditName().equalsIgnoreCase(g.getUser().getUserName())) {
                u = new GetUser().execute(g.getUser().getUserName()).get();

            } else {
                GetUser gu = new GetUser();
                u = gu.execute(g.getUsereditName()).get();


            }


            //Set email and Name
            userEdit.setText(u.getUserName());
            mailEdit.setText(u.getEmail());

            //TODO Handle Exception properly
        } catch (InterruptedException e) {
            e.printStackTrace();

        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Globals g = (Globals) getApplication();
        if (g.getUser().getAdmin()) {
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
        if (id == R.id.action_PlaceBet){
            Intent intentPlaceBet = new Intent(getApplicationContext(), AvailableSc2Bets.class);
            startActivity(intentPlaceBet);
            return true;
        }
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
            Globals g = (Globals) getApplication();
            g.setUsereditName(g.getUser().getUserName());
            Intent intentEditUser = new Intent(getApplicationContext(), EditUserActivity.class);
            startActivity(intentEditUser);
            return true;
        }
        if (id == R.id.action_SignIn){
            Globals g = (Globals) getApplication();
            g.setUser(null);
            Intent intentLogin = new Intent(getApplicationContext(), Welcome.class);
            startActivity(intentLogin);
            return true;
        }
        if (id == R.id.action_ManageUser){
            Globals g = (Globals) getApplication();
            Intent intentLogin = new Intent(getApplicationContext(), ManageUserActivity.class);
            startActivity(intentLogin);
            return true;
        }

        if(id==R.id.action_TicketAnswer){
            Intent intentTicketAnswer = new Intent(getApplicationContext(), TicketAnswerActivity.class);
            startActivity(intentTicketAnswer);
            return true;
        }
        if(id==R.id.action_Logout){
            Globals g = (Globals) getApplication();
            User user = new User();
            user.setId(0);
            user.setAdmin(false);
            user.setLoggedIn(false);
            user.setUserName(null);
            g.setUser(user);

            Intent intentWelcome = new Intent(getApplicationContext(), Welcome.class);
            startActivity(intentWelcome);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    public void submitPW(View view) throws NoSuchAlgorithmException {

        Globals.hideSoftKeyboard(this);

        op = "Password Change";

        String pw1 = newPWEdit.getText().toString();
        String pw2 = newPWConfirmEdit.getText().toString();


        if (pw1.equalsIgnoreCase(pw2) && !pw1.equalsIgnoreCase("")) {
            String generatedPassword = "";

            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(pw1.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();

            Globals g = (Globals) getApplication();
            User u= null;
            try {
                u = new GetUser().execute(g.getUsereditName()).get();
            }catch(Exception e){
                e.printStackTrace();
            }
            u.setPassword(generatedPassword);
            new SubmitChanges().execute(u);

        }else{
            Toast.makeText(EditUserActivity.this, "Passwords don´t match!", Toast.LENGTH_SHORT).show();
        }


    }

    public void submitChange(View view) {

        Globals.hideSoftKeyboard(this);

        op = "Personal Data Change";

        String username = userEdit.getText().toString();
        String email = mailEdit.getText().toString();

        Globals g = (Globals) getApplication();
        User u= null;
        try {
            u = new GetUser().execute(g.getUsereditName()).get();
        }catch(Exception e){
            e.printStackTrace();
        }
        if(u==null){
            System.out.println("FEEEEEEEEEEEHHHHHHHLLLLLERRRR");
        }else{
            u.setEmail(email);
            u.setUserName(username);

            new SubmitChanges().execute(u);
        }



    }

    private class SubmitChanges extends AsyncTask<User, Void, Boolean> {


        @Override
        protected Boolean doInBackground(User... params) {


            Globals g = (Globals) getApplication();
            LocalClient client = new LocalClient();
            if (client.edit(params[0])) {

                return true;
            } else {
                return false;
            }


        }

        @Override
        protected void onPostExecute(Boolean b) {
            TextView Status = (TextView) findViewById(R.id.Status);


            if (b) {

                Toast.makeText(EditUserActivity.this, "UserModel edited", Toast.LENGTH_SHORT).show();
                Intent intentUser = new Intent(getApplicationContext(), UserLandingActivity.class);
                startActivity(intentUser);
            } else {
                Status.setText(op + " unsuccessful");
                Status.setVisibility(View.VISIBLE);
            }
        }


    }


    private class GetUser extends AsyncTask<String, Void, User>{

        @Override
        protected User doInBackground(String... params) {
            Globals g = (Globals) getApplication();
            LocalClient client = new LocalClient();
            return client.getUser(params[0]);
        }
    }


}
