package de.blogsiteloremipsum.gamingbets.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import de.blogsiteloremipsum.gamingbets.R;
import de.blogsiteloremipsum.gamingbets.classes.Globals;
import de.blogsiteloremipsum.gamingbets.classes.User;
import de.blogsiteloremipsum.gamingbets.communication.clientREST.LocalClient;

public class UnlocksActivity extends AppCompatActivity implements PurchaseDialogFragment.NoticeDialogListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlocks);

        Globals g = (Globals) getApplication();
        User u = g.getUser();

        ArrayList<String> locks = new ArrayList<>();
        String user_locks = u.getUnlocks();
        int i = 1;
        for (char each : user_locks.toCharArray()){
            if(each=='0'){
                locks.add("Profile Image: "+ i);
            }
            i++;
        }

        ListAdapter availableBetsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, locks);

        ListView unlocks_list = (ListView) findViewById(R.id.lst_unlocks);
        unlocks_list.setAdapter(availableBetsAdapter);

        unlocks_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Globals g = (Globals) getApplication();

                if (g.getUser().getScore()-50<0){
                    Toast.makeText(UnlocksActivity.this, "You don´t have enough points to Purchase this Item, you need 50 points to unlock this one.", Toast.LENGTH_LONG).show();
                }else {
                    PurchaseDialogFragment dialog = PurchaseDialogFragment.newInstance(g.getUser().getScore() - 50, position, g.getUser().getUnlocks());
                    dialog.show(getSupportFragmentManager(), "purchase_dialog");
                    UnlocksActivity.this.position = position;
                }
            }
        });


    }

    private int position;

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        System.out.println("PositiveClick in UnlocksActivity!");


        System.out.println("Begin of Purchasing Profile Image!");
        System.out.println("Wanted position: "+ position);

        Globals g = (Globals) getApplication();
        User u = g.getUser();

        int profile_to_unlock = -1;
        String unlocks = u.getUnlocks();

        int i = 0;
        int position_counter = 0;

        for (char each : unlocks.toCharArray()) {
            if (each == '0') {
                if (position_counter == position) {
                    profile_to_unlock = i;
                    break;
                } else {
                    position_counter++;
                }

            }
            i++;
        }

        System.out.println("profile_to_unlock: " + profile_to_unlock);

        char[] before = u.getUnlocks().toCharArray();
        before[profile_to_unlock] = '1';
        u.setUnlocks(String.copyValueOf(before));
        u.setScore(u.getScore()-50);
        new SubmitChanges().execute(u);

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

        System.out.println("NegativeClick in UnlocksActivity!");

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
                Toast.makeText(UnlocksActivity.this, "UserModel edited", Toast.LENGTH_SHORT).show();
                Intent intentUser = new Intent(getApplicationContext(), UserLandingActivity.class);
                startActivity(intentUser);

            } else {
                Status.setText("unsuccessful");
                Status.setVisibility(View.VISIBLE);
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

                if (u.getScore() != null) {
                    Log.d(u.getUserName() + " hat profil bild:  ", "" + u.getProfilePic());
                } else {
                    Log.d(u.getUserName(), " Update failed!");
                }

                g.setUser(u);
                return true;
            }

            @Override
            protected void onPostExecute(Boolean b) {

            }
        }
    }
}
