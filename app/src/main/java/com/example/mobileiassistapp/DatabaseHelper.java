package com.example.mobileiassistapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Mad.db";
    public static final String TABLE_NAME = "subject_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "SUBJECT_ID";
    public static final String COL_3 = "SUBJECT_NAME";
    public static final String COL_4 = "CA_MARKS";
    public static final String COL_5 = "TOTAL_MARKS";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,SUBJECT_ID TEXT, SUBJECT_NAME TEXT, CA_MARKS FLOAT, TOTAL_MARKS FLOAT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }

    public boolean insertData(String subject_id,String subject_name,Float ca, float total){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,subject_id);
        contentValues.put(COL_3,subject_name);
        contentValues.put(COL_4,ca);
        contentValues.put(COL_5,total);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        Log.i("res", String.valueOf(res));
        return res;
    }

    public Integer deleteData(String subject_id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"SUBJECT_ID = ?",new String [] {subject_id});
    }
}
