package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;

public class RoundResult extends AppCompatActivity {

    private ArrayList<String> resltList = new ArrayList<String>();
    private boolean results [];
    private ListView lvAllWords;
    private Integer CountResult = 0;
    private TextView tvCountResult;

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_result);

        resltList = getIntent().getStringArrayListExtra("ARRAY_WORDS");
        results = getIntent().getBooleanArrayExtra("WORDS_RESULTS");

        tvCountResult = (TextView)findViewById(R.id.textView24);
        lvAllWords = (ListView)findViewById(R.id.qwert);

         adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_multiple_choice, resltList.toArray(new String[0]));
        lvAllWords.setAdapter(adapter);

        int j=0;
        for (String str : resltList) {
            if (results[j]){
                lvAllWords.setItemChecked(j,true);
                CountResult +=1;
            }
            else {
                lvAllWords.setItemChecked(j,false);
                CountResult -=1;
            }
            j++;
        }

        lvAllWords.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                if (lvAllWords.isItemChecked(position)){
                    CountResult +=1;
                }
                else {
                    CountResult -=1;
                }
               tvCountResult.setText(String.valueOf(CountResult));
            }
        });
        tvCountResult.setText(String.valueOf(CountResult));
    }
}
