package com.example.mobileiassistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class saveGPAActivity extends AppCompatActivity {
Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_g_p_a);

        save=(Button)findViewById(R.id.button5);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openhistoryActivity();
            }
        });

    }

    public void openhistoryActivity(){
        Intent intent=new Intent(this,historyActivity.class);
        startActivity(intent);
    }
}