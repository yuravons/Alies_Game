package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    private Button btn3;
    private SeekBar words_count, time;
    private TextView textView1, textView2;
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
                        startActivity(int_btn2);
                        sendWordsCount();
                        sendTime();
                    }
                }
        );
    }
    public void sendWordsCount() {
        Intent intent = new Intent(this, StartGame.class);
        intent.putExtra("WORDS_COUNT", textView1.getText().toString());
        startActivity(intent);
    }
    public void sendTime() {
        Intent intent = new Intent(this, StartGame.class);
        intent.putExtra("TIME", textView2.getText().toString());
        startActivity(intent);
    }
}
