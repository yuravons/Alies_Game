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
    private TextView tvWordsCount, tvTeam1_Name, tvTeam2_Name;
    private String sTeam1_Name, sTeam2_Name;
    private String sCountWords, sTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
        addListenerOnButton();
        
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
