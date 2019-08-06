package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class congratulations_winner extends AppCompatActivity {

    private String winnerTeam, scoreWinnerTeam;
    private TextView tvWinnerTeamName, tvscoreWinnerTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulations_winner);

        winnerTeam = getIntent().getStringExtra("WINNER_TEAM");
        scoreWinnerTeam = getIntent().getStringExtra("WINNER_RESULT");

        tvscoreWinnerTeam = (TextView)findViewById(R.id.textView27);
        tvscoreWinnerTeam.setText(scoreWinnerTeam);
        tvWinnerTeamName = (TextView)findViewById(R.id.textView26);
        tvWinnerTeamName.setText(winnerTeam);

    }
}
