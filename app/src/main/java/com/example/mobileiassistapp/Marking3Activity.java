package com.example.mobileiassistapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class Marking3Activity extends AppCompatActivity {
    ListView data_list;
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marking3);

        data_list = (ListView) findViewById(R.id.data_list);

        Intent i = getIntent();

        myDb = new DatabaseHelper(this);
        Cursor res = myDb.getAllData();

        if(res.moveToFirst()){
            String subject_Id = res.getString(1);
            String subject_Name = res.getString(2);
            String CA_Marks = res.getString(3);
            String total_Marks = res.getString(4);
        }

        //CursorAdapter ca = new SimpleCursorAdapter(this,R.layout.activity_marking3,res,new String[])
    }


}