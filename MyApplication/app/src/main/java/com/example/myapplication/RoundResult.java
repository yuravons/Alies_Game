package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;

public class RoundResult extends AppCompatActivity {

    private ArrayList<String> resltList = new ArrayList<String>();
    private boolean results[];
    private ListView lvAllWords;
    private Integer CountResult = 0;
    private TextView tvCountResult, tvNamePlayingTeam;
    private Button btnEndRound;
    private String playingTeam;
    private Boolean isPenalty, isCommonLastWord;

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_result);

        resltList = getIntent().getStringArrayListExtra("ARRAY_WORDS");
        results = getIntent().getBooleanArrayExtra("WORDS_RESULTS");
        playingTeam = getIntent().getStringExtra("PLAYING_TEAM");

        isPenalty = getIntent().getBooleanExtra("PENALTY", false);
        isCommonLastWord = getIntent().getBooleanExtra("COMMON_LAST_WORD", false);

        tvCountResult = (TextView) findViewById(R.id.textView24);
        lvAllWords = (ListView) findViewById(R.id.qwert);
        btnEndRound = (Button) findViewById(R.id.button12);
        btnEndRound.setText("Продовжити");
        tvNamePlayingTeam = (TextView) findViewById(R.id.textView23);

        tvNamePlayingTeam.setText(playingTeam);

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_multiple_choice, resltList.toArray(new String[0]));
        lvAllWords.setAdapter(adapter);

        addListenerOnButton();

        int j = 0;
        for (String str : resltList) {
            if (results[j]) {
                lvAllWords.setItemChecked(j, true);
                CountResult += 1;
            } else {
                lvAllWords.setItemChecked(j, false);
                if (isPenalty) {
                    CountResult -= 1;
                }
            }
            j++;
        }

        lvAllWords.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                if (lvAllWords.isItemChecked(position)) {
                    if (isPenalty) {
                        CountResult += 2;
                    } else {
                        CountResult += 1;
                    }
                } else {
                    if (isPenalty) {
                        CountResult -= 2;
                    } else {
                        CountResult -= 1;
                    }
                }
                tvCountResult.setText(String.valueOf(CountResult));
            }
        });
        tvCountResult.setText(String.valueOf(CountResult));
    }

    public void addListenerOnButton() {
        btnEndRound.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        btnEndRound();
                    }
                }
        );
    }

    public void btnEndRound() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("RESULT_POINTS", CountResult.toString());
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}
