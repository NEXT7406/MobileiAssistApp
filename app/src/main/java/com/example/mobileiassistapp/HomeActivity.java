package com.example.mobileiassistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    Button btnGpa;
    Button btnTarget;
    Button btnMarking;
    Button btnTodo;
    Button btnAbout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnGpa = (Button)findViewById(R.id.gpabtn);
        btnTarget=(Button)findViewById(R.id.targetbtn);
        btnMarking=(Button)findViewById(R.id.markingbtn);
        btnTodo =(Button)findViewById(R.id.todobtn);
        btnAbout=(Button)findViewById(R.id.Aboutbtn);

        btnGpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(HomeActivity.this, GpaActivity.class);
                HomeActivity.this.startActivity(myIntent);

            }
        });
        btnTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(HomeActivity.this, TargetActivity.class);
                HomeActivity.this.startActivity(myIntent);

            }
        });
        btnMarking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(HomeActivity.this, MarkingActivity.class);
                HomeActivity.this.startActivity(myIntent);

            }
        });
        btnTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(HomeActivity.this, TodoActivity.class);
                HomeActivity.this.startActivity(myIntent);

            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(HomeActivity.this, AboutActivity.class);
                HomeActivity.this.startActivity(myIntent);

            }
        });

    }
}
