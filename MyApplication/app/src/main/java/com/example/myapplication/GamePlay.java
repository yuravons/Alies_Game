package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class GamePlay extends AppCompatActivity {

    private String sTeam1_Name, sTeam2_Name;
    private Integer nGuessedWords = 0, nSkippedWords = 0;

    private String sTime;
    private TextView tvTime, tvSkippedWord, tvGuessedWord;
    private Button btnStart, btnYes, btnNo;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;

    private ArrayList <Integer> arr = new ArrayList<Integer>();
    private ArrayList <String> arrWords = new ArrayList<String>();
    private ArrayList <Boolean> wordResult = new ArrayList<>();

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
        btnYes = (Button) findViewById(R.id.button9);
        btnNo = (Button) findViewById(R.id.button10);




        addListenerOnButton();

        updateCountDownText();

        setWord();

        startTimer();
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
        btnYes.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        nGuessedWords += 1;
                        tvGuessedWord.setText(String.valueOf(nGuessedWords));
                        wordResult.add(true);
                        if(mTimerRunning) {
                            setWord();
                        } else {
                            endGamePlay();
                        }
                    }
                }
        );
        btnNo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        nSkippedWords += 1;
                        tvSkippedWord.setText(String.valueOf(nSkippedWords));
                        wordResult.add(false);
                        if(mTimerRunning) {
                            setWord();
                        } else {
                            endGamePlay();
                        }
                    }
                }
        );
    }

    private void setWord() {
        TextView tx = (TextView) findViewById(R.id.textView14);
        tx.setText(RandomWord());
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
                btnStart.setVisibility(View.INVISIBLE);
            }
        }.start();

        mTimerRunning = true;
        btnStart.setText("Пауза");
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        btnStart.setText("Продовжити");
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftMilliSeconds / 1000) / 60;
        int seconds = (int) (mTimeLeftMilliSeconds / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        tvTime.setText(timeLeftFormatted);
    }
    private String RandomWord() {
        Integer randNumber = 0;

        arr.add(1);
        Boolean isFlag = false;
        EasyWord easy_word = new EasyWord();

        int i = 0;
        Boolean isGenerate = true;
        do {
            if (isGenerate)
                randNumber = new Random().nextInt(50);
            if (randNumber != arr.get(i)) {
                isFlag = true;
                isGenerate = false;
            } else {
                isFlag = false;
                i = 0;
                isGenerate = true;
            }
        } while (++i < arr.size());
        arr.add(randNumber);
        String sWord = easy_word.getWord(randNumber);
        arrWords.add(sWord);
        return sWord;
    }

    public void sendArrayWords(Intent _intent) {
        _intent.putExtra("ARRAY_WORDS", arrWords);

        boolean arr[] = new boolean[wordResult.size()];
        for (int i=0;i<wordResult.size();++i) {
            arr[i] = wordResult.get(i);
        }
        _intent.putExtra("WORDS_RESULTS", arr);
    }

    public void endGamePlay() {
        Intent intent = new Intent(GamePlay.this,RoundResult.class);
        sendArrayWords(intent);
        startActivity(intent);
    }
}
