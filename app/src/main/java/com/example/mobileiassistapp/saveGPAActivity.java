package com.example.mobileiassistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobileiassistapp.Database.DatabaseHelper;

public class saveGPAActivity extends AppCompatActivity {
    DatabaseHelper mydb;
    EditText title,gpa;
    Button save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_g_p_a);
        mydb=new DatabaseHelper(this);

        title=(EditText)findViewById(R.id.editTitle);
        gpa=(EditText)findViewById(R.id.editGPA);
        save=(Button)findViewById(R.id.button5);
        addData();

       /* save = (Button) findViewById(R.id.button5);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openhistoryActivity();
            }
        });*/

    }

    public void openhistoryActivity() {
        Intent intent = new Intent(this, historyActivity.class);
        startActivity(intent);

    }

    public void addData(){
        save.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       boolean isInserted= mydb.insertData(title.getText().toString() ,gpa.getText().toString() );
                       if(isInserted =true) {
                           Toast.makeText(saveGPAActivity.this, "DATA INSERTED!", Toast.LENGTH_SHORT).show();
                           openhistoryActivity();
                       }
                       else
                           Toast.makeText(saveGPAActivity.this,"DATA NOT INSERTED!",Toast.LENGTH_SHORT).show();
                    }
                }
        );

    }



}