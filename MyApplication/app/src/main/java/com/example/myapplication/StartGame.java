package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartGame extends AppCompatActivity {

    private Button btn03;
    private TextView tvWordsCount, tvTeam1_Name, tvTeam2_Name, tvCountTeam1, tvCountTeam2, tvGameRoundCount, TeamPlay;
    private String sTeam1_Name, sTeam2_Name;
    private String sCountWords, sTime;
    private Integer CountTeam1 = 0, CountTeam2 = 0, GameCount = 1, RaundCount = 1;
    private Boolean boo = true;
    private String str;
    private String playingTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
        addListenerOnButton();

        tvCountTeam1 = (TextView) findViewById(R.id.textView11);
        tvCountTeam1.setText(String.valueOf(CountTeam1));
        tvCountTeam2 = (TextView) findViewById(R.id.textView12);
        tvCountTeam2.setText(String.valueOf(CountTeam2));
        tvGameRoundCount = (TextView) findViewById(R.id.textView13);
        tvGameRoundCount.setText("Раунд " + String.valueOf(RaundCount) + " \\ Гра " + String.valueOf(GameCount));


        sTeam1_Name = getIntent().getStringExtra("TEAM_1");
        tvTeam1_Name = (TextView) findViewById(R.id.textView9);
        tvTeam1_Name.setText(sTeam1_Name);

        sTeam2_Name = getIntent().getStringExtra("TEAM_2");
        tvTeam2_Name = (TextView) findViewById(R.id.textView10);
        tvTeam2_Name.setText(sTeam2_Name);

        sCountWords = getIntent().getStringExtra("WORDS_COUNT");
        tvWordsCount = (TextView) findViewById(R.id.textView5);
        tvWordsCount.setText(sCountWords);

        sTime = getIntent().getStringExtra("TIME");

        TeamPlay = (TextView) findViewById(R.id.textView16);

        playingTeam = sTeam1_Name;

        TeamPlay.setText(playingTeam);

    }

    public void addListenerOnButton() {
        btn03 = (Button) findViewById(R.id.button8);
        btn03.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent int_btn1 = new Intent(StartGame.this, GamePlay.class);
                        sendTime(int_btn1);
                        sendplayingTeam(int_btn1);
                        startActivityForResult(int_btn1, 2);

                    }
                }
        );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 2) {
            if(playingTeam.equals(sTeam1_Name)) {
                CountTeam1 += Integer.valueOf(data.getStringExtra("RESULT_POINTS"));
                tvCountTeam1.setText(CountTeam1.toString());
                playingTeam = sTeam2_Name;
                RaundCount += 1;
            } else if (playingTeam.equals(sTeam2_Name)) {
                CountTeam2 += Integer.valueOf(data.getStringExtra("RESULT_POINTS"));
                tvCountTeam2.setText(CountTeam2.toString());
                playingTeam = sTeam1_Name;
                GameCount += 1;
                RaundCount -= 1;
            }

            TeamPlay.setText(playingTeam);
            tvGameRoundCount.setText("Раунд " + String.valueOf(RaundCount) + " \\ Гра " + String.valueOf(GameCount));
            WhoWiner();
        }
    }

    public void sendTime(Intent _intent) {
        _intent.putExtra("TIME", sTime);
    }
    public void sendplayingTeam(Intent _intent) {
        _intent.putExtra("PLAYING_TEAM", playingTeam);
    }

    public void WhoWiner (){
        if(RaundCount == 1){
            if (CountTeam1 >= Integer.parseInt(sCountWords) || CountTeam2 >= Integer.parseInt(sCountWords)){
                if (CountTeam1 > CountTeam2){
                    Intent int_btn2 = new Intent(StartGame.this, congratulations_winner.class);
                    int_btn2.putExtra("WINNER_TEAM", sTeam1_Name);
                    int_btn2.putExtra("WINNER_RESULT", CountTeam1.toString());
                    startActivity(int_btn2);
                }
                else {
                    Intent int_btn2 = new Intent(StartGame.this, congratulations_winner.class);
                    int_btn2.putExtra("WINNER_TEAM", sTeam2_Name);
                    int_btn2.putExtra("WINNER_RESULT", CountTeam2.toString());
                    startActivity(int_btn2);
                }
            }
        }
    }
}
