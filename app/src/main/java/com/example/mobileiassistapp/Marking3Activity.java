package com.example.mobileiassistapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Marking3Activity extends AppCompatActivity {

    Button btn_delete;
    EditText id_to_delete;
    EditText id_to_update;
    EditText sub_name_to_update;
    EditText ca_to_update;
    EditText total_to_update;
    Button update_btn;
    Button btn_view_all;
    DatabaseHelperMarking myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marking3);

        myDb = new DatabaseHelperMarking(this);

        btn_view_all = (Button) findViewById(R.id.view_all_btn);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        id_to_delete = (EditText) findViewById(R.id.id_to_delete);

        id_to_update = (EditText) findViewById(R.id.id_to_update);
        sub_name_to_update = (EditText)findViewById(R.id.sub_name_to_update);
        ca_to_update = (EditText)findViewById(R.id.ca_to_update);
        total_to_update = (EditText)findViewById(R.id.total_to_update);
        update_btn = (Button)findViewById(R.id.update_btn);

        viewAll();
        deleteData();
        updateData();

        ca_to_update.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!TextUtils.isEmpty(ca_to_update.getText().toString())){
                    if(!hasFocus && Float.parseFloat(ca_to_update.getText().toString())>50){
                        Toast.makeText(getApplicationContext(), "Marks should be less than 50", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Marks can not be empty", Toast.LENGTH_LONG).show();
                }

            }
        });

        total_to_update.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!TextUtils.isEmpty((total_to_update.getText().toString()))){
                    if(!hasFocus && Float.parseFloat(total_to_update.getText().toString())>100){
                        Toast.makeText(getApplicationContext(), "Marks should be less than 100", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Marks can not be empty", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void viewAll(){
        btn_view_all.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0){
                            //show message
                            showMessage("Error","Nothing Found");
                            return;
                        }else{
                            StringBuffer buffer = new StringBuffer();
                            while(res.moveToNext()){
                                buffer.append("ID : "+res.getString(0)+"\n");
                                buffer.append("Subject ID : "+res.getString(1)+"\n");
                                buffer.append("Subject Name : "+res.getString(2)+"\n");
                                buffer.append("CA Marks : "+res.getString(3)+"\n");
                                buffer.append("Total Marks : "+res.getString(4)+"\n\n");
                            }
                            //show all data
                            showMessage("Data",buffer.toString());
                        }
                    }
                }
        );
    }

    public void showMessage(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }

    public void deleteData(){
        btn_delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(id_to_delete.getText().toString());
                        if(deletedRows>0){
                            Toast.makeText(Marking3Activity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(Marking3Activity.this,"Data Not Deleted",Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    public void updateData(){
        update_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer updatedRows = myDb.updateData(id_to_update.getText().toString(),sub_name_to_update.getText().toString(),Float.parseFloat(ca_to_update.getText().toString()),Float.parseFloat(total_to_update.getText().toString()));
                        if(updatedRows>0){
                            Toast.makeText(Marking3Activity.this,"Data Updated",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(Marking3Activity.this,"Data Not Updated",Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }
}