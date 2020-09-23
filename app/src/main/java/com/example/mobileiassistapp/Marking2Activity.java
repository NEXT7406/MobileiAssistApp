package com.example.mobileiassistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Marking2Activity extends AppCompatActivity {

    Button btn_save;
    TextView ca;
    TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marking2activity);
        Bundle bundle = getIntent().getExtras();

        btn_save = (Button) findViewById(R.id.btn_save);
        ca = (TextView) findViewById(R.id.ca_text_id);
        total = (TextView) findViewById(R.id.total_text_id);

        float ca_marks = bundle.getFloat("ca");
        float final_marks = bundle.getFloat("final");
        ca.setText(Float.toString(ca_marks));
        total.setText(Float.toString((final_marks+ca_marks)));
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Marking2Activity.this, Marking3Activity.class));

            }
        });

    }
}