package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.words.EasyWord;
import com.example.myapplication.words.HardWord;
import com.example.myapplication.words.MediumWord;
import com.example.myapplication.words.Word;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class GamePlay extends AppCompatActivity {

    private String sTeam1_Name, sTeam2_Name, sLastWordResult;
    private Integer nGuessedWords = 0, nSkippedWords = 0;
    private String sTime, playingTeam;
    private TextView tvTime, tvSkippedWord, tvGuessedWord, tvPlayingTeam, tvWord;
    private Button btnPause, btnYes, btnNo;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private AlertDialog.Builder adLastWord;

    private static ArrayList<Integer> arr = new ArrayList<>();
    private ArrayList<String> arrWords = new ArrayList<String>();
    private ArrayList<Boolean> wordResult = new ArrayList<>();

    private long mTimeLeftMilliSeconds;

    private String sLevelType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        playingTeam = getIntent().getStringExtra("PLAYING_TEAM");

        sLevelType = getIntent().getStringExtra("LEVEL");

        sTeam1_Name = getIntent().getStringExtra("TEAM_NAME1");
        sTeam2_Name = getIntent().getStringExtra("TEAM_NAME2");

        sTime = getIntent().getStringExtra("TIME");

        mTimeLeftMilliSeconds = Integer.parseInt(sTime) * 1000;
        tvTime = (TextView) findViewById(R.id.textView21);

        tvPlayingTeam = (TextView) findViewById(R.id.textView22);
        tvGuessedWord = (TextView) findViewById(R.id.textView17);
        tvSkippedWord = (TextView) findViewById(R.id.textView20);

        tvPlayingTeam.setText(playingTeam);

        btnPause = (Button) findViewById(R.id.button11);
        btnYes = (Button) findViewById(R.id.button9);
        btnNo = (Button) findViewById(R.id.button10);
        tvWord = (TextView) findViewById(R.id.textView14);

        btnYes.setEnabled(false);
        btnNo.setEnabled(false);
        btnPause.setEnabled(false);

        addListenerOnButton();

        updateCountDownText();

        tvWord.setText("Почати");
    }

    public void sendplayingTeam(Intent _intent) {
        _intent.putExtra("PLAYING_TEAM", playingTeam);
    }

    public void addListenerOnButton() {

        tvWord.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setWord();
                        btnYes.setEnabled(true);
                        btnNo.setEnabled(true);
                        btnPause.setEnabled(true);
                        startTimer();
                    }
                }
        );

        btnPause.setOnClickListener(
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
                            onCreateDialog();
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
                btnPause.setVisibility(View.INVISIBLE);
            }
        }.start();

        mTimerRunning = true;
        btnPause.setText("Пауза");
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        btnPause.setText("Продовжити");
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftMilliSeconds / 1000) / 60;
        int seconds = (int) (mTimeLeftMilliSeconds / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        tvTime.setText(timeLeftFormatted);
    }

    private String RandomWord() {
        int nRangeSize = 0;
        int randNumber = 0;

        arr.add(1);
        boolean isFlag = false;

        Word words;

        if (sLevelType.equals("Easy")) {
            words = new EasyWord();
        } else if (sLevelType.equals("Medium")) {
            words = new MediumWord();
        } else {
            //LEVEL equals Hard
            words = new HardWord();
        }

        nRangeSize = words.getSize();

        int i = 0;
        boolean isGenerate = true;
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

        String sWord;

        sWord = words.getWord(randNumber);

        arrWords.add(sWord);
        return sWord;
    }

    public void sendArrayWords(Intent _intent) {
        _intent.putExtra("ARRAY_WORDS", arrWords);

        boolean[] arr = new boolean[wordResult.size()];
        for (int i = 0; i < wordResult.size(); ++i) {
            arr[i] = wordResult.get(i);
        }
        _intent.putExtra("WORDS_RESULTS", arr);
    }

    public void endGamePlay() {
        Intent intent = new Intent(this, RoundResult.class);
        sendArrayWords(intent);
        sendplayingTeam(intent);
        sendIsPenalty(intent);
        sendIsCommonLastWord(intent);
        startActivityForResult(intent, 2);
    }

    public void sendIsPenalty(Intent _intent) {
        _intent.putExtra("PENALTY", getIntent().getBooleanExtra("PENALTY", false));
    }

    public void sendIsCommonLastWord(Intent _intent) {
        _intent.putExtra("COMMON_LAST_WORD", getIntent().getBooleanExtra("COMMON_LAST_WORD", false));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 2) {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("RESULT_POINTS", data.getStringExtra("RESULT_POINTS"));
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        }
    }

    public void onCreateDialog() {
        final String[] arrNamesTeams = { sTeam1_Name, sTeam2_Name };
        AlertDialog.Builder builder = new AlertDialog.Builder(GamePlay.this);
        builder.setTitle("Останнє слово")
            .setItems(arrNamesTeams, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                }
            });
        builder.create();
        builder.show();
}

}
