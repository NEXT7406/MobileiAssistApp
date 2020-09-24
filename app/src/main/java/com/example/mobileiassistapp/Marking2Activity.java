package com.example.mobileiassistapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Marking2Activity extends AppCompatActivity {

    Button btn_save;
    Button btn_view_all;
    Button btn_delete;
    TextView ca;
    TextView total;
    EditText subject_id;
    EditText subject_name;
    EditText sub_id_to_delete;
    EditText sub_id_to_update;
    EditText sub_name_to_update;
    EditText ca_to_update;
    EditText total_to_update;
    Button update_btn;
    DatabaseHelperMarking myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marking2activity);
        Bundle bundle = getIntent().getExtras();

        myDb = new DatabaseHelperMarking(this);

        btn_save = (Button) findViewById(R.id.btn_save);
        btn_view_all = (Button) findViewById(R.id.view_all_btn);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        sub_id_to_delete = (EditText) findViewById(R.id.sub_id_to_delete);
        ca = (TextView) findViewById(R.id.ca_text_id);
        total = (TextView) findViewById(R.id.total_text_id);
        subject_id = (EditText) findViewById(R.id.subject_id);
        subject_name = (EditText) findViewById(R.id.subject_name);

        sub_id_to_update = (EditText) findViewById(R.id.sub_id_to_update);
        sub_name_to_update = (EditText)findViewById(R.id.sub_name_to_update);
        ca_to_update = (EditText)findViewById(R.id.ca_to_update);
        total_to_update = (EditText)findViewById(R.id.total_to_update);
        update_btn = (Button)findViewById(R.id.update_btn);

        float ca_marks = bundle.getFloat("ca");
        float final_marks = bundle.getFloat("final");
        ca.setText(String.format("%.2f",ca_marks));
        total.setText(String.format("%.2f",final_marks));
        /*btn_view_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

        startActivity(new Intent(Marking2Activity.this, Marking3Activity.class));

            }
        });*/
        AddData();
        viewAll();
        deleteData();
        updateData();
    }

    public void AddData(){
        btn_save.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    boolean isInserted =  myDb.insertData(subject_id.getText().toString(),subject_name.getText().toString(),Float.parseFloat(ca.getText().toString()),Float.parseFloat(total.getText().toString()));
                    if(isInserted == true){
                        Toast.makeText(Marking2Activity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(Marking2Activity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                    }
                    }
                }
        );
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
                            //Intent intent = new Intent(Marking2Activity.this, Marking3Activity.class);
                            //intent.putExtra("data","No Data Found");
                            //startActivity(intent);
                            return;
                        }else{
                            StringBuffer buffer = new StringBuffer();
                            while(res.moveToNext()){
                                buffer.append("Subject ID : "+res.getString(1)+"\n");
                                buffer.append("Subject Name : "+res.getString(2)+"\n");
                                buffer.append("CA Marks : "+res.getString(3)+"\n");
                                buffer.append("Total Marks : "+res.getString(4)+"\n\n");
                            }
                            //show all data
                            showMessage("Data",buffer.toString());
                            //Intent intent = new Intent(Marking2Activity.this, Marking3Activity.class);
                            //intent.putExtra("data",String.valueOf(res));
                            //startActivity(intent);
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
                        Integer deletedRows = myDb.deleteData(sub_id_to_delete.getText().toString());
                        if(deletedRows>0){
                            Toast.makeText(Marking2Activity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(Marking2Activity.this,"Data Not Deleted",Toast.LENGTH_LONG).show();
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
                        boolean isUpdated = myDb.updateData(sub_id_to_update.getText().toString(),sub_name_to_update.getText().toString(),Float.parseFloat(ca_to_update.getText().toString()),Float.parseFloat(total_to_update.getText().toString()));
                        if(isUpdated){
                            Toast.makeText(Marking2Activity.this,"Data Updated",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(Marking2Activity.this,"Data Not Updated",Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }
}