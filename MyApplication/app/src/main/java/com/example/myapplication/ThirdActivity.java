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
    private SeekBar seekBar1, seekBar2;
    private TextView textView1, textView2;
    private String [] words  = {"рука", "око", "голова", "слово", "земля", "життя", "день", "старий", "жінка", "нога", "ось", "сила", "людина",
            "місце", "новий", "рік", "чоловік", "обличчя", "голос", "двері", "думка", "батько", "серце", "бік", "україна", "бог", "дати", "ніколи",
            "хлопець", "розуміти", "пан", "ніч", "правда", "вода", "давати", "останній", "погляд", "ніхто", "дівчина", "сонце", "стіл", "небо",
            "тіло", "любити", "відповісти", "річ", "народ", "місто", "плече", "чекати", "знайти", "жити", "вікно", "син", "спитати", "смерть",
            "лежати", "мусити", "слухати", "стіна", "повернутися", "взяти", "хвилина", "іти", "боятися", "сьогодні", "мама", "ніж", "кінь",
            "зрозуміти", "довго", "колись", "молодий", "мало", "виходити", "запитати", "високий", "кімната", "справа", "мова", "село", "просити",
            "власний", "білий", "шукати", "князь", "Іван", "довгий", "писати", "вулиця", "палець", "намагатися", "почути", "година", "працювати",
            "дерево", "подивитися", "розмова", "швидко", "машина", "місяць", "глянути", "живий", "увага", "тисяча", "ставати", "брат", "історія",
            "маленький", "забути", "шлях", "вирішити", "вітер", "товариш", "відчувати", "дід", "вогонь", "мати", "починати", "кинути", "читати",
            "хвиля", "повітря", "вірити", "тримати", "козак", "ворог", "додому", "дорога", "сісти", "військо", "крок"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        addListenerOnButton2();

        textView1 = (TextView) findViewById(R.id.textView7);
        textView2 = (TextView) findViewById(R.id.textView8);

        seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
        seekBar2 = (SeekBar) findViewById(R.id.seekBar2);

        seekBar1.setMin(10);
        seekBar1.setMax(200);

        seekBar2.setMin(10);
        seekBar2.setMax(120);
        textView1.setText(Integer.toString(words.length));

        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textView1.setText("" + i + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textView2.setText("" + i + "%");
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
                    }
                }
        );
    }
}
