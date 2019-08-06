package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class GamePlay extends AppCompatActivity {

    private String sTeam1_Name, sTeam2_Name;
    private Integer nGuessedWords = 0, nSkippedWords = 0;
    private Map<String, Boolean> gameLevels = new HashMap<String, Boolean>();
    private String sTime,playingTeam;
    private TextView tvTime, tvSkippedWord, tvGuessedWord, tvPlayingTeam;
    private Button btnStart, btnYes, btnNo;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;

    private ArrayList<Integer> arr = new ArrayList<Integer>();
    private ArrayList<String> arrWords = new ArrayList<String>();
    private ArrayList<Boolean> wordResult = new ArrayList<>();

    private long mTimeLeftMilliSeconds;

    private String count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        playingTeam = getIntent().getStringExtra("PLAYING_TEAM");

        gameLevels.put("Easy", false);
        gameLevels.put("Medium", false);
        gameLevels.put("Hard", false);

        sTime = getIntent().getStringExtra("TIME");
        mTimeLeftMilliSeconds = Integer.parseInt(sTime) * 1000;
        tvTime = (TextView) findViewById(R.id.textView21);

        tvPlayingTeam = (TextView) findViewById(R.id.textView22);
        tvGuessedWord = (TextView) findViewById(R.id.textView17);
        tvSkippedWord = (TextView) findViewById(R.id.textView20);

        tvPlayingTeam.setText(playingTeam);

        btnStart = (Button) findViewById(R.id.button11);
        btnYes = (Button) findViewById(R.id.button9);
        btnNo = (Button) findViewById(R.id.button10);

        addListenerOnButton();

        updateCountDownText();

        setWord();

        startTimer();
    }

    public void sendplayingTeam(Intent _intent) {
        _intent.putExtra("PLAYING_TEAM", playingTeam);
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
                        if (mTimerRunning) {
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
                        if (mTimerRunning) {
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
                tvTime.setText("Останнє слово");
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
        Integer nRangeSize = 0;
        Integer randNumber = 0;

        arr.add(1);
        Boolean isFlag = false;

        EasyWord easy_words = new EasyWord();
        MediumWord medium_words = new MediumWord();
        HardWord hard_words = new HardWord();

        if(gameLevels.get("Easy")) {
            nRangeSize = easy_words.getSize();
        } else if (gameLevels.get("Medium")) {
            nRangeSize = medium_words.getSize();
        } else if (gameLevels.get("Hard")) {
            nRangeSize = hard_words.getSize();
        }

        int i = 0;
        Boolean isGenerate = true;
        do {
            if (isGenerate)
                randNumber = new Random().nextInt(nRangeSize);
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
        String sWord = easy_words.getWord(randNumber);
        arrWords.add(sWord);
        return sWord;
    }

    public void sendArrayWords(Intent _intent) {
        _intent.putExtra("ARRAY_WORDS", arrWords);

        boolean arr[] = new boolean[wordResult.size()];
        for (int i = 0; i < wordResult.size(); ++i) {
            arr[i] = wordResult.get(i);
        }
        _intent.putExtra("WORDS_RESULTS", arr);
    }

    public void endGamePlay() {
//        this.finish();
        Intent intent = new Intent(this, RoundResult.class);
        sendArrayWords(intent);
        sendplayingTeam(intent);
        startActivityForResult(intent , 2);

//        if(getIntent().getStringExtra("RESULT_POINTS") != null) {
//            String count = getIntent().getStringExtra("RESULT_POINTS");
//        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 2) {
            Intent resultIntent = new Intent();
            resultIntent.putExtra( "RESULT_POINTS", data.getStringExtra("RESULT_POINTS"));
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        }
    }
}
