package com.example.mobileiassistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobileiassistapp.Database.DBHelper;

public class saveGPAActivity extends AppCompatActivity {


    EditText title;
    EditText gpa;
    DBHelper db;
    Button save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_g_p_a);



        title=(EditText)findViewById(R.id.editTitle);
        gpa=(EditText)findViewById(R.id.editGPA);
        save=(Button)findViewById(R.id.button5);
        db=new DBHelper(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value1=title.getText().toString();
                Double value2= Double.valueOf(gpa.getText().toString());

                Boolean checkinsertdata=db.insertData(value1,value2);
                if(checkinsertdata==true){
                    Toast.makeText(saveGPAActivity.this,"INSERTED SUCCESSFULLY!",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(saveGPAActivity.this,"DATA NOT INSERTED!",Toast.LENGTH_SHORT).show();
                }
            }
        });






    }

    public void openhistoryActivity() {
        Intent intent = new Intent(this, historyActivity.class);
        startActivity(intent);

    }



}