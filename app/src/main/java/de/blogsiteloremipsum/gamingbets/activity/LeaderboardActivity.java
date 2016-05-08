package de.blogsiteloremipsum.gamingbets.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
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

import de.blogsiteloremipsum.gamingbets.communication.clientREST.LocalClient;
import de.blogsiteloremipsum.gamingbets.communication.old.client.LocalClientSocket;

public class LeaderboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        Globals g = (Globals) getApplication();
        User u = g.getUser();
        ArrayList<User> score = new ArrayList<>();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Globals g = (Globals) getApplication();
        User u = g.getUser();
        if(u.getID()==0){
            getMenuInflater().inflate(R.menu.menu_guest, menu);
        }else if(u.isAdmin()){
            getMenuInflater().inflate(R.menu.menu_admin, menu);
        }else{
            getMenuInflater().inflate(R.menu.menu_user, menu);
        }
        return true;
    }

    private class LeaderboardTask extends AsyncTask<User ,Void, ArrayList<User>> {
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected ArrayList<User> doInBackground(User... params) {

            //For debugging
            return new LocalClient().getLeaderboard();

        }

    }
}
