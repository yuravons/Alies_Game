package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Levels extends AppCompatActivity {

    private Button btn0,btn01,btn02;
    private String sTeam1_Name, sTeam2_Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        addListenerOnButton();

        sTeam1_Name = getIntent().getStringExtra("TEAM_1");
        sTeam2_Name = getIntent().getStringExtra("TEAM_2");
    }
    
    public void addListenerOnButton() {
        btn0 = (Button) findViewById(R.id.button5);
        btn01 = (Button) findViewById(R.id.button6);
        btn02 = (Button) findViewById(R.id.button7);

        btn0.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent int_btn1 = new Intent(Levels.this, StartGame.class);
                        sendTeamsNames(int_btn1);
                        startActivity(int_btn1);
                    }
                }

        );

        btn01.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent int_btn1 = new Intent(Levels.this, StartGame.class);
                        sendTeamsNames(int_btn1);
                        startActivity(int_btn1);
                    }
                }

        );

        btn02.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent int_btn1 = new Intent(Levels.this, StartGame.class);
                        sendTeamsNames(int_btn1);
                        startActivity(int_btn1);
                    }
                }
        );
    }
    public void sendTeamsNames(Intent _intent) {
        //Intent intent = new Intent(this, StartGame.class);
        _intent.putExtra("TEAM_1", sTeam1_Name);
        _intent.putExtra("TEAM_2", sTeam2_Name);
        //startActivity(intent);
    }
}
