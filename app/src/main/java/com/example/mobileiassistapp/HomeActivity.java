package com.example.mobileiassistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    Button btngpa;
    Button btntarget;
    Button btnmarking;
    Button btntodo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btngpa = (Button)findViewById(R.id.gpabtn);
        btntarget=(Button)findViewById(R.id.targetbtn);
        btnmarking=(Button)findViewById(R.id.markingbtn);
        btntodo =(Button)findViewById(R.id.todobtn);

        btngpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(HomeActivity.this, GpaActivity.class);
                HomeActivity.this.startActivity(myIntent);

            }
        });
        btntarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(HomeActivity.this, TargetActivity.class);
                HomeActivity.this.startActivity(myIntent);

            }
        });
        btnmarking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(HomeActivity.this, MarkingActivity.class);
                HomeActivity.this.startActivity(myIntent);

            }
        });
        btntodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(HomeActivity.this, TodoActivity.class);
                HomeActivity.this.startActivity(myIntent);

            }
        });

    }
}