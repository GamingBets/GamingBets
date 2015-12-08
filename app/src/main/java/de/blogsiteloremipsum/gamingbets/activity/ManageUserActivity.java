package de.blogsiteloremipsum.gamingbets.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import de.blogsiteloremipsum.gamingbets.R;
import de.blogsiteloremipsum.gamingbets.classes.Globals;
import de.blogsiteloremipsum.gamingbets.classes.User;
import de.blogsiteloremipsum.gamingbets.communication.client.LocalClientSocket;

public class ManageUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_user);

        Globals g = (Globals) getApplication();
        User u = g.getUser();
        ArrayList<User> scores = null;
        try {
            scores = new ManageUserTask().execute(u).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        String[] leaderBoardArray = new String [scores.size()];

        for (int i=0;i< scores.size();i++){
            leaderBoardArray[i] = scores.get(i).getUserName();
        }

        ListAdapter leaderboardAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                leaderBoardArray);

        ListView leaderboardView = (ListView) findViewById(R.id.listView);
        leaderboardView.setAdapter(leaderboardAdapter);

        leaderboardView.setOnItemClickListener(new
            AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                     String tvShowPicked = "You selected" + String.valueOf(adapterView.getItemAtPosition(position));

                     Toast.makeText(ManageUserActivity.this, tvShowPicked, Toast.LENGTH_SHORT).show();
                }
            });

         }


    private class ManageUserTask extends AsyncTask<User ,Void, ArrayList<User>> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected ArrayList<User> doInBackground(User... params) {

            //For debugging
            return new LocalClientSocket().getUsers();



        }

    }
}

