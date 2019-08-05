package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    private Button btn3;
    private SeekBar words_count, time;
    private TextView textView1, textView2;
    private String sTeam1_Name, sTeam2_Name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        addListenerOnButton2();

        textView1 = (TextView) findViewById(R.id.textView7);
        textView2 = (TextView) findViewById(R.id.textView8);

        words_count = (SeekBar) findViewById(R.id.seekBar1);
        time = (SeekBar) findViewById(R.id.seekBar2);

        words_count.setMin(10);
        words_count.setMax(200);

        time.setMin(10);
        time.setMax(120);
        sTeam1_Name = getIntent().getStringExtra("TEAM_1");
        sTeam2_Name = getIntent().getStringExtra("TEAM_2");

        words_count.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textView1.setText("" + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        time.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textView2.setText("" + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void addListenerOnButton2() {
        btn3 = (Button) findViewById(R.id.button4);
        btn3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent int_btn2 = new Intent(ThirdActivity.this, Levels.class);
                        sendTeamsNames(int_btn2);
                        sendWordsCount(int_btn2);
                        sendTime(int_btn2);
                        startActivity(int_btn2);
                    }
                }
        );
    }
    public void sendTeamsNames(Intent _intent) {
        _intent.putExtra("TEAM_1", sTeam1_Name);
        _intent.putExtra("TEAM_2", sTeam2_Name);
    }
    public void sendWordsCount(Intent _intent) {
        _intent.putExtra("WORDS_COUNT", textView1.getText().toString());
    }
    public void sendTime(Intent _intent) {
        if (textView2.getText().equals("")) {
            _intent.putExtra("TIME", "10");
        } else {
            _intent.putExtra("TIME", textView2.getText().toString());
        }
    }
}
