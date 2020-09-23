package com.example.mobileiassistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MarkingActivity extends AppCompatActivity {

    Button btn_cal;
    EditText assg1;
    EditText assg2;
    EditText mid;
    EditText finalExam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marking);

        btn_cal = findViewById(R.id.btn_cal);
        assg1 = (EditText)findViewById(R.id.ass1);
        assg2 = (EditText)findViewById(R.id.ass2);
        mid = (EditText)findViewById(R.id.mid_marks);
        finalExam = (EditText)findViewById(R.id.final_marks);

        btn_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                float CAMarks = (Float.parseFloat(assg1.getText().toString())/100)*15 + (Float.parseFloat(assg2.getText().toString())/100)*15 + (Float.parseFloat(mid.getText().toString())/100)*20;
                float FinalMarks = (Float.parseFloat(finalExam.getText().toString())/100)*50;
                Intent intent = new Intent(MarkingActivity.this, Marking2Activity.class);
                intent.putExtra("ca",CAMarks);
                intent.putExtra("final",FinalMarks);
                startActivity(intent);

            }
        });
    }
}