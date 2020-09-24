package com.example.mobileiassistapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME="database.db";
    private static final int DATABASE_VERSION=1;

    private static final String TABLE_NAME="gpa_info";
    private static final String COL_ID="_id";
    private static final String COL_TITLE="title";
    private static final String COL_GPA="gpa";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);



    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query= "CREATE TABLE " + TABLE_NAME +
                        " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COL_TITLE + " TEXT, " +
                        COL_GPA + "DOUBLE);";
        db.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String title, String gpa){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_TITLE,title);
        contentValues.put(COL_GPA,gpa);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;

    }
}
