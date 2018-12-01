package com.example.nasirbashak.testtheluck;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class Main2Activity extends AppCompatActivity {
    static int num1, num2, time = 200;
    static final String QM = "?";
    TextView t1, t2, mainText, timeView;
    Button btnCheck;

    boolean exclude2;

    static Random rand = new Random();


    static int[] visited;
    static int i;
    static int range;

    static ArrayList<Integer> generatedNumbers;
    static int count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        t1 = (TextView) findViewById(R.id.text1);
        t2 = (TextView) findViewById(R.id.text2);
        mainText = (TextView) findViewById(R.id.mainText);
        timeView = (TextView) findViewById(R.id.timeView);
        btnCheck = (Button) findViewById(R.id.btnchk);


        Bundle bundle = getIntent().getExtras();
        String s1 = bundle.getString("num1");
        String s2 = bundle.getString("num2");
        exclude2 = bundle.getBoolean("choice");
        Toast.makeText(getApplicationContext(), "" + exclude2, Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(),s1+" 1st "+s2,Toast.LENGTH_LONG).show();
        t1.setText(s1);
        t2.setText(s2);


        num1 = Integer.parseInt(s1);
        num2 = Integer.parseInt(s2);
        mainText.setText(QM);
        range = num2 - num1 + 1;

        count = 0;
        generatedNumbers = new ArrayList<>(range);


    }

    private int generateTheNumber() {
        int num = Math.abs(rand.nextInt(num2) + num1);

        if (found(num)) {
            return generateTheNumber();
        }

        return num;

    }

    private boolean found(int num) {

        for (Integer n : generatedNumbers) {
            if (num == n)
                return true;

        }

        generatedNumbers.add(num);
        return false;
    }

    int n;

    public void generateTheNum(View view) {


        if (exclude2) {
            if (count < range) {
                n = generateTheNumber();
                count++;

                mainText.setText(n + "");


                time = 115;
                setTimer();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mainText.setText(QM);
                        btnCheck.setEnabled(true);
                    }
                }, 2000);
                btnCheck.setEnabled(false);


            } else {
                Toast.makeText(getApplicationContext(), "Max Reached", Toast.LENGTH_LONG).show();

            }

        } else {
            n = Math.abs(rand.nextInt(num2) + num1);

            mainText.setText(n + "");


            time = 115;
            setTimer();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mainText.setText(QM);
                    btnCheck.setEnabled(true);
                }
            }, 2000);
            btnCheck.setEnabled(false);

        }


    }


    public void setTimer() {

        timeView.setText(time + "");
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                time = time - 1;
                timeView.setText(time + "");

                if (time > 0) {
                    setTimer();
                }
            }
        }, 1);


    }
}
/*
 * 4
 * 5
 * 1
 * 3
 * 4
 * 1
 * 4
 * 5
 * 5
 * 3
 * 4
 * 5
 * 4
 * 2
 * excluded
 * 2
 * 5
 * 1
 * 1
 * 3
 * 4
 * 4
 * 5
 *
 *
 *
 *
 * */