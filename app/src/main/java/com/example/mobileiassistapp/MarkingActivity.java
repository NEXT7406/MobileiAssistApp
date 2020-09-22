package com.example.mobileiassistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MarkingActivity extends AppCompatActivity {

    Button btn_cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marking);

        btn_cal = findViewById(R.id.btn_cal);

        btn_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MarkingActivity.this, Marking2Activity.class);
                startActivity(intent);

            }
        });
    }
}