package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Time;
import java.util.Locale;
import java.util.Timer;

public class GamePlay extends AppCompatActivity {

    private String sTeam1_Name, sTeam2_Name;
    private Integer nGuessedWords, nSkippedWords;

    private String sTime;
    private TextView tvTime, tvSkippedWord, tvGuessedWord;
    private Button btnStart, btnYes, btnNo;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;

    private long mTimeLeftMilliSeconds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        sTime = getIntent().getStringExtra("TIME");
        mTimeLeftMilliSeconds = Integer.parseInt(sTime) * 1000;
        tvTime  = (TextView)findViewById(R.id.textView21);

        tvGuessedWord = (TextView)findViewById(R.id.textView17);
        tvSkippedWord = (TextView)findViewById(R.id.textView20);

        btnStart = (Button) findViewById(R.id.button11);

        addListenerOnButton();

        addListenerOnButtonYes();
        addListenerOnButtonNo();

        updateCountDownText();
    }

    public void addListenerOnButton() {
        btnStart.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mTimerRunning) {
                            pauseTimer();
                        } else {
                            startTimer();
                        }
                    }
                }
        );
    }

    public void addListenerOnButtonYes() {
        btnYes.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        nGuessedWords += 1;
                        tvGuessedWord.setText(String.valueOf(nGuessedWords));
                    }
                }
        );
    }

    public void addListenerOnButtonNo() {
        btnNo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        nSkippedWords += 1;
                        tvSkippedWord.setText(String.valueOf(nSkippedWords));
                    }
                }
        );
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftMilliSeconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftMilliSeconds = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                btnStart.setText("Start");
                btnStart.setVisibility(View.INVISIBLE);
            }
        }.start();

        mTimerRunning = true;
        btnStart.setText("pause");
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        btnStart.setText("Start");
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftMilliSeconds / 1000) / 60;
        int seconds = (int) (mTimeLeftMilliSeconds / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        tvTime.setText(timeLeftFormatted);
    }
}
