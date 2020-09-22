package com.example.mobileiassistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class displayGPAActivity extends AppCompatActivity {

    Button home;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_g_p_a);


        TextView text=(TextView)findViewById(R.id.textView12);
        text.setText(getIntent().getStringExtra("gpa"));




        home=(Button)findViewById(R.id.button3);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomeActivity();
            }
        });


        save=(Button)findViewById(R.id.button4);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opensaveGPAActivity();
            }
        });



    }
    public void openHomeActivity(){
        Intent intent1=new Intent(this,HomeActivity.class);
        startActivity(intent1);
    }

    public void opensaveGPAActivity(){
        Intent intent=new Intent(this,saveGPAActivity.class);
        startActivity(intent);
    }
}