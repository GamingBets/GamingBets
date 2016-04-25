package de.blogsiteloremipsum.gamingbets.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import de.blogsiteloremipsum.gamingbets.R;

public class PlacebetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_placebet);
    }

    public void placeBet(){
        Toast.makeText(PlacebetActivity.this, "Bet placed", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getApplicationContext(), UserLandingActivity.class);
        startActivity(i);
    }


}
