package de.blogsiteloremipsum.gamingbets.activity;

import android.content.Intent;
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
import de.blogsiteloremipsum.gamingbets.classes.Sc2AvailableBets;
import de.blogsiteloremipsum.gamingbets.classes.User;
import de.blogsiteloremipsum.gamingbets.communication.clientREST.LocalClient;

public class AvailableSc2Bets extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_sc2_bets);

        Globals g = (Globals) getApplication();
        User u = g.getUser();
        ArrayList<Sc2AvailableBets> bets = new ArrayList<>();
        bets = null;

        try{
            bets = new AvailableSc2BetsTask().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if(bets!=null){
            g.setAvailableBets(bets);
            String[] betsArray = new String [bets.size()];

            for(int i = 0; i<bets.size();i++){
                betsArray[i] = bets.get(i).getMatchId().getTournamentId().getName() + "\t"  + "\t" + bets.get(i).getMatchId().getPlayer1().getIngameName() + " vs. " +bets.get(i).getMatchId().getPlayer2().getIngameName();
            }

            ListAdapter availableBetsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, betsArray);

            ListView betsView = (ListView) findViewById(R.id.listViewBets);
            betsView.setAdapter(availableBetsAdapter);

            betsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                    Globals g = (Globals) getApplication();
                    g.setAvailableBet(g.getAvailableBets().get(position));
                    Intent i = new Intent(getApplicationContext(), PlacebetActivity.class);
                    startActivity(i);
                }
            });
        }else{
            Toast.makeText(AvailableSc2Bets.this, "No available bets found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_user, menu);
        return true;
    }

    private class AvailableSc2BetsTask extends AsyncTask<Void , Void, ArrayList<Sc2AvailableBets>>
    {
        @Override
        protected ArrayList<Sc2AvailableBets> doInBackground(Void... params) {
            return new LocalClient().getAvailableBets();
        }
    }
}
