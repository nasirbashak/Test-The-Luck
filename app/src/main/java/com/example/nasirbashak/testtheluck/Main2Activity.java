package com.example.nasirbashak.testtheluck;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Main2Activity extends AppCompatActivity {
    static int num1, num2, time = 200;
    static final String QM = "?";
    TextView t1, t2, mainText, timeView;
    Switch includeOrNot;

    static Random rand = new Random();


    static int[] visited;
    static int[] excluded;
    static int min, max, i;
    static int range;
    boolean exclude = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        t1 = (TextView) findViewById(R.id.text1);
        t2 = (TextView) findViewById(R.id.text2);
        mainText = (TextView) findViewById(R.id.mainText);
        timeView = (TextView) findViewById(R.id.timeView);
        includeOrNot = (Switch) findViewById(R.id.btnSwitch);

        Bundle bundle = getIntent().getExtras();
        String s1 = bundle.getString("num1");
        String s2 = bundle.getString("num2");
        //Toast.makeText(getApplicationContext(),s1+" 1st "+s2,Toast.LENGTH_LONG).show();
        t1.setText(s1);
        t2.setText(s2);

        num1 = Integer.parseInt(s1);
        num2 = Integer.parseInt(s2);
        mainText.setText(QM);
        range = num2 - num1 + 1;
        visited = new int[range];

        //includeOrNot.setEnabled(false);

        includeOrNot.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    exclude= true;
                }else{
                    exclude= false;
                }
            }
        });


    }


    public int test() {

        int num = generate();
        //System.out.println("Rand num is" + num);

        //System.out.println("Do u want to exclude??");
        //exclude = scan.nextBoolean();
        exclude = includeOrNot.isChecked();

        Toast.makeText(getApplicationContext(), "" + exclude, Toast.LENGTH_SHORT).show();

        if (exclude)
            includeOrNotMethod(num);

        return num;
    }

    private static void includeOrNotMethod(int num) {

        for (int j = 0; j < range; j++) {
            if (num == visited[j])
                return;
        }
        visited[i++] = num;

    }

    public static int generate() {

        int num = Math.abs(rand.nextInt(num2) + num1);

        if (isVisited(num)) {
            return generate();
        }

        return num;
    }


    private static boolean isVisited(int num) {

        for (int j = 0; j < range; j++) {
            if (num == visited[j])
                return true;
        }
        return false;
    }


    public void generateTheNum(View view) {

        if (i < range) {


            includeOrNot.setEnabled(true);

            int num = generate();

            Toast.makeText(getApplicationContext(), "" + exclude, Toast.LENGTH_SHORT).show();

            if (exclude)
                includeOrNotMethod(num);


            //Random rand = new Random();
            //int num = Math.abs(rand.nextInt(num2) + num1);

            //int num = test();
            mainText.setText(num + "");


            time = 115;
            setTimer();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mainText.setText(QM);
                    includeOrNot.setEnabled(false);
                }
            }, 2000);
        } else {
            Toast.makeText(getApplicationContext(), "Max Reached", Toast.LENGTH_LONG).show();
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