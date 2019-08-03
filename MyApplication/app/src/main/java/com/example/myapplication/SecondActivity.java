package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    private Button btn2;
    private EditText team_1, team_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        addListenerOnButton1();
    }


    public void addListenerOnButton1() {
        btn2 = (Button) findViewById(R.id.button3);
        btn2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent int_btn2 = new Intent(SecondActivity.this, ThirdActivity.class);
                        startActivity(int_btn2);
                        sendName();
                    }
                }
        );
    }


    public void sendName(){

        team_1 = (EditText)findViewById(R.id.editText3);
        team_2 = (EditText)findViewById(R.id.editText4);

        Intent intent = new Intent(SecondActivity.this, StartGame.class);
        intent.putExtra("TEAM_1",team_1.getText().toString());
        intent.putExtra("TEAM_2",team_2.getText().toString());
        startActivity(intent);
    }
}
