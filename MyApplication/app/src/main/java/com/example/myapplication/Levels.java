package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Levels extends AppCompatActivity {

    private Button btn0,btn01,btn02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        addListenerOnButton();
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
                        startActivity(int_btn1);
                    }
                }

        );

        btn01.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent int_btn1 = new Intent(Levels.this, StartGame.class);
                        startActivity(int_btn1);
                    }
                }

        );

        btn02.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent int_btn1 = new Intent(Levels.this, StartGame.class);
                        startActivity(int_btn1);
                    }
                }
        );
    }
}
