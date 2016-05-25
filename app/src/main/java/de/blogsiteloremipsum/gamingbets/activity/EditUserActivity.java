package de.blogsiteloremipsum.gamingbets.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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
    Spinner spinner_profile_pic;
    ArrayList<String> pics;

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
        spinner_profile_pic = (Spinner) findViewById(R.id.spinner_profile_pic);


        Globals g = (Globals) getApplication();
        User u = g.getUser();


        pics = new ArrayList<>();
        pics.add("Standard");

        String user_locks = u.getUnlocks();
        int i = 1;
        for (char each : user_locks.toCharArray()){
            if(each=='1'){
                pics.add("Profile Image: "+ i);
            }
            i++;
        }

        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, pics);

        spinner_profile_pic.setAdapter(listAdapter);



        //Get UserModel
        u = null;

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
            spinner_profile_pic.setSelection(u.getProfilePic()-1);

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
        if(id==R.id.action_MyBets){
            Intent intentMyBets = new Intent(getApplicationContext(), MyBetsActivity.class);
            startActivity(intentMyBets);
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
        Globals g = (Globals) getApplication();
        User u = g.getUser();
        Globals.hideSoftKeyboard(this);

        op = "Personal Data Change";

        String username = userEdit.getText().toString();
        String email = mailEdit.getText().toString();
        int position = spinner_profile_pic.getSelectedItemPosition();


        int new_profile_img_id;
        if (position == 0){
            new_profile_img_id = -1;
        }else {
            new_profile_img_id = -1;
            String unlocks = u.getUnlocks();

            int i = 0;
            int position_counter = 0;

            for (char each : unlocks.toCharArray()) {
                if (each == '1') {
                    if (position_counter == position-1) {
                        new_profile_img_id = i;
                        break;
                    } else {
                        position_counter++;
                    }

                }
                i++;
            }


        }

        u= null;
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
            u.setProfilePic(new_profile_img_id);

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
                new RefreshUserTask().execute();
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

    private class RefreshUserTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected void onPreExecute() {

            //TODO Set Flag: isRefreshing

        }

        @Override
        protected Boolean doInBackground(Void... params) {

            Globals g = (Globals) getApplication();
            String username = g.getUser().getUserName();
            User u = new LocalClient().getUser(username);

            if (u.getScore()!=null){
                Log.d(u.getUserName() + " hat profil bild:  ", "" + u.getProfilePic());
            }else{
                Log.d(u.getUserName(), " Update failed!" );
            }

            g.setUser(u);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean b) {

        }
    }


}
