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

        //textView
        TextView text=(TextView)findViewById(R.id.textView12);

        //set the value of gpa to textview
        text.setText(getIntent().getStringExtra("gpa"));

        //Button
        home=(Button)findViewById(R.id.button3);
        save=(Button)findViewById(R.id.button4);


        //on click for the home button which navigates to HomeActivity
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomeActivity();
            }
        });


       //onclick for the save button which navigates to the saveGPAActivity
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opensaveGPAActivity();
            }
        });



    }

    //method to call homeActivity
    public void openHomeActivity(){
        Intent intent1=new Intent(this,HomeActivity.class);
        startActivity(intent1);
    }

    //method to call saveGPAActivity
    public void opensaveGPAActivity(){
        Intent intent=new Intent(this,saveGPAActivity.class);
        startActivity(intent);
    }
}