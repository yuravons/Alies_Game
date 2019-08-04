package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class GamePlay extends AppCompatActivity {

    private String sTeam1_Name, sTeam2_Name;
    private String sTime;
    private TextView tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        sTime = getIntent().getStringExtra("TIME");
        tvTime  = (TextView)findViewById(R.id.textView21);
        tvTime.setText(sTime);

    }
}
