package com.example.mobileiassistapp.Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "GPADATABASE", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table gpaInfoTable(ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT, GPA DOUBLE)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("drop Table if exists gpaInfoTable");
    }


    public Boolean insertData(String title, Double gpa) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("title",title);
        contentValues.put("gpa",gpa);
        long result=db.insert("gpaInfoTable",null,contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }

    }


}
