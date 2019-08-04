package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartGame extends AppCompatActivity {

    private Button btn03;
    private TextView tvWordsCount, tvTeam1_Name, tvTeam2_Name,tvCountTeam1,tvCountTeam2,tvGameRoundCount, TeamPlay;
    private String sTeam1_Name, sTeam2_Name;
    private String sCountWords, sTime;
    private Integer CountTeam1 = 0,CountTeam2 = 0,GameCount = 0,RaundCount = 0;
    private Boolean boo = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
        addListenerOnButton();

        tvCountTeam1 = (TextView)findViewById(R.id.textView11);
        tvCountTeam1.setText(String.valueOf(CountTeam1));
        tvCountTeam2 = (TextView)findViewById(R.id.textView12);
        tvCountTeam2.setText(String.valueOf(CountTeam2));
        tvGameRoundCount = (TextView)findViewById(R.id.textView13);
        tvGameRoundCount.setText("Раунд "+String.valueOf(RaundCount)+" \\ Гра "+String.valueOf(GameCount));

        sTeam1_Name = getIntent().getStringExtra("TEAM_1");
        tvTeam1_Name = (TextView)findViewById(R.id.textView9);
        tvTeam1_Name.setText(sTeam1_Name);

        sTeam2_Name = getIntent().getStringExtra("TEAM_2");
        tvTeam2_Name  = (TextView)findViewById(R.id.textView10);
        tvTeam2_Name.setText(sTeam2_Name);

        sCountWords = getIntent().getStringExtra("WORDS_COUNT");
        tvWordsCount = (TextView)findViewById(R.id.textView5);
        tvWordsCount.setText(sCountWords);

        sTime = getIntent().getStringExtra("TIME");

        TeamPlay = (TextView)findViewById(R.id.textView16);
        if (boo){
            TeamPlay.setText(sTeam1_Name);
        }
        else {
            TeamPlay.setText(sTeam2_Name);
        }
    }

    public void addListenerOnButton() {
        btn03 = (Button) findViewById(R.id.button8);
        btn03.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent int_btn1 = new Intent(StartGame.this, GamePlay.class);
                        sendTime(int_btn1);
                        startActivity(int_btn1);
                    }
                }
        );
    }

    public void sendTime(Intent _intent) {
        _intent.putExtra("TIME", sTime);
    }
}
