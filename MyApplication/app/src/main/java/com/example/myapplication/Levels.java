package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Levels extends AppCompatActivity {

    private Button btn0,btn01,btn02;
    private String sTeam1_Name, sTeam2_Name;
    private String sCountWords, sTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        addListenerOnButton();

        sTeam1_Name = getIntent().getStringExtra("TEAM_1");
        sTeam2_Name = getIntent().getStringExtra("TEAM_2");

        sCountWords = getIntent().getStringExtra("WORDS_COUNT");
        sTime = getIntent().getStringExtra("TIME");
    }
    
    public void addListenerOnButton() {
        btn0 = (Button) findViewById(R.id.button5); //Hard level
        btn01 = (Button) findViewById(R.id.button6); //Easy level
        btn02 = (Button) findViewById(R.id.button7); //Medium level

        //Hard level
        btn0.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent int_btn1 = new Intent(Levels.this, StartGame.class);
                        sendTeamsNames(int_btn1);
                        sendWordsCount(int_btn1);
                        sendTime(int_btn1);
                        sendIsPenalty(int_btn1);
                        sendIsCommonLastWord(int_btn1);
                        sendTypeLevel(int_btn1, "Hard");
                        startActivity(int_btn1);
                    }
                }

        );

        //Easy level
        btn01.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent int_btn1 = new Intent(Levels.this, StartGame.class);
                        sendTeamsNames(int_btn1);
                        sendWordsCount(int_btn1);
                        sendTime(int_btn1);
                        sendIsPenalty(int_btn1);
                        sendIsCommonLastWord(int_btn1);
                        sendTypeLevel(int_btn1, "Easy");
                        startActivity(int_btn1);
                    }
                }

        );

        //Medium level
        btn02.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent int_btn1 = new Intent(Levels.this, StartGame.class);
                        sendTeamsNames(int_btn1);
                        sendWordsCount(int_btn1);
                        sendTime(int_btn1);
                        sendIsPenalty(int_btn1);
                        sendIsCommonLastWord(int_btn1);
                        sendTypeLevel(int_btn1, "Medium");
                        startActivity(int_btn1);
                    }
                }
        );
    }

    public void sendTeamsNames(Intent _intent) {
        _intent.putExtra("TEAM_1", sTeam1_Name);
        _intent.putExtra("TEAM_2", sTeam2_Name);
    }

    public void sendWordsCount(Intent _intent) {
        _intent.putExtra("WORDS_COUNT", sCountWords);
    }

    public void sendTime(Intent _intent) {
        _intent.putExtra("TIME", sTime);
    }

    public void sendTypeLevel(Intent _intent, String _type) {
        _intent.putExtra("LEVEL", _type);
    }

    public void sendIsPenalty(Intent _intent) {
        Boolean temp = getIntent().getBooleanExtra("PENALTY", false);
        _intent.putExtra("PENALTY", getIntent().getBooleanExtra("PENALTY", false));
    }

    public void sendIsCommonLastWord(Intent _intent) {
        _intent.putExtra("COMMON_LAST_WORD", getIntent().getBooleanExtra("COMMON_LAST_WORD", false));
    }
}
