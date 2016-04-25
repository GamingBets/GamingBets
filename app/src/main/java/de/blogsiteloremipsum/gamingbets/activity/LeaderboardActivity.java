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
import de.blogsiteloremipsum.gamingbets.model.UserModel;
import de.blogsiteloremipsum.gamingbets.communication.clientREST.LocalClient;
import de.blogsiteloremipsum.gamingbets.communication.old.client.LocalClientSocket;

public class LeaderboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        Globals g = (Globals) getApplication();
        User u = g.getUser();
        ArrayList<UserModel> score = new ArrayList<>();
        score = null;
        try {
            score = new LeaderboardTask().execute(u).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        String[] leaderBoardArray = new String [score.size()];

        for (int i=0;i< score.size();i++){
            leaderBoardArray[i] = score.get(i).getUserName() + "                    " + score.get(i).getScore();
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

                    Toast.makeText(LeaderboardActivity.this, tvShowPicked, Toast.LENGTH_SHORT).show();
                    Globals g = (Globals) getApplication();
                }
            });
    }

    private class LeaderboardTask extends AsyncTask<User ,Void, ArrayList<UserModel>> {
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected ArrayList<UserModel> doInBackground(User... params) {

            //For debugging
            return new LocalClient().getLeaderboards();



        }

    }
}
