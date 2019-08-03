package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ThirdActivity extends AppCompatActivity {

    private Button btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        addListenerOnButton2();
    }

    public void addListenerOnButton2() {
        btn3 = (Button) findViewById(R.id.button4);
        btn3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent int_btn2 = new Intent(ThirdActivity.this, FourthActivity.class);
                        startActivity(int_btn2);
                    }
                }
        );
    }
}
