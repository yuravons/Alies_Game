package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    private Button btn2;

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
                    }
                }
        );
    }
}
