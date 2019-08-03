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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
        addListenerOnButton();

        Intent intent =getIntent();

        String name1 = intent.getStringExtra("TEAM_1");
        TextView j = (TextView)findViewById(R.id.textView9);
        j.setText(name1);

        String name2= intent.getStringExtra("TEAM_2");
        TextView j1 = (TextView)findViewById(R.id.textView10);
        j1.setText(name2);
    }

    public void addListenerOnButton() {
        btn03 = (Button) findViewById(R.id.button8);
        btn03.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent int_btn1 = new Intent(StartGame.this, GamePlay.class);
                        startActivity(int_btn1);
                    }
                }
        );
    }
}
