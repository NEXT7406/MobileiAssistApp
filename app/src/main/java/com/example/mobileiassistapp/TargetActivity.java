package com.example.mobileiassistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TargetActivity extends AppCompatActivity {

    private EditText canumber;
    private EditText caoutnum;
    private EditText dgrade;
    private Button cal;
    private TextView answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);

        canumber = (EditText) findViewById(R.id.editTextNumber);
        caoutnum = (EditText) findViewById(R.id.editTextNumber3);
        dgrade = (EditText) findViewById(R.id.editTextNumber4);
        cal = (Button) findViewById(R.id.button);
        answer = (TextView) findViewById(R.id.editTextNumber2);

        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int canumber1 = Integer.parseInt(canumber.getText().toString());
                int caoutnum1 = Integer.parseInt(caoutnum.getText().toString());
                int dgrade1 = Integer.parseInt(dgrade.getText().toString());
                int sum = (dgrade1 - canumber1) * 100 / (100 - caoutnum1);
                answer.setText("Answer:" + String.valueOf(sum));

                if (sum >= 100){
                    answer.setText("Target Grade Unachievable");
                }
                else {
                    answer.setText("Target:" + String.valueOf(sum));
                }

            }
        });
    }
}
