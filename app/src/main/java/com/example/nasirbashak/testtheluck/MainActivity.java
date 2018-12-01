package com.example.nasirbashak.testtheluck;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText number1, number2;
    TextView T1, T2;
    Button B1;
    Switch exSwitch;
    boolean exclude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number1 = (EditText) findViewById(R.id.editNumber1);
        number2 = (EditText) findViewById(R.id.editNumber2);
        exSwitch = (Switch) findViewById(R.id.btnSwitch);

        exSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(exSwitch.isChecked()){
                    exclude= true;
                    Toast.makeText(getApplicationContext(),""+exclude,Toast.LENGTH_SHORT).show();
                }else{
                    exclude=false;
                    Toast.makeText(getApplicationContext(),""+exclude,Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void exitActivity(View view) {
        finishAffinity();

    }

    public void goToSecond(View view) {
        String s1 = number1.getText().toString().trim();
        String s2= number2.getText().toString().trim();



        if(s1.length()>0 && s2.length()>0){
           // Toast.makeText(getApplicationContext(),s1+" "+s2,Toast.LENGTH_LONG).show();

            if(Integer.parseInt(s1)> Integer.parseInt(s2)){
                Toast.makeText(getApplicationContext(),"Starting range must be less than Ending range",Toast.LENGTH_LONG).show();
                return;
            }


            Intent intent = new Intent(MainActivity.this,Main2Activity.class);

            Bundle bundle = new Bundle();

            bundle.putString("num1",s1);
            bundle.putString("num2",s2);
            bundle.putBoolean("choice",exclude);
            intent.putExtras(bundle);
            startActivity(intent);

        }else{
            Toast.makeText(getApplicationContext(),"Please give the range",Toast.LENGTH_LONG).show();
        }
    }
}
