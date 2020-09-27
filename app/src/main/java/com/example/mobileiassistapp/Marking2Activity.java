package com.example.mobileiassistapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Marking2Activity extends AppCompatActivity {

    Button btn_save;

    TextView ca;
    TextView total;
    EditText subject_id;
    EditText subject_name;
    Button next_btn;
    DatabaseHelperMarking myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marking2activity);
        Bundle bundle = getIntent().getExtras();

        myDb = new DatabaseHelperMarking(this);

        btn_save = (Button) findViewById(R.id.btn_save);
        next_btn = (Button) findViewById(R.id.next_btn);
        ca = (TextView) findViewById(R.id.ca_text_id);
        total = (TextView) findViewById(R.id.total_text_id);
        subject_id = (EditText) findViewById(R.id.subject_id);
        subject_name = (EditText) findViewById(R.id.subject_name);

        float ca_marks = bundle.getFloat("ca");
        float final_marks = bundle.getFloat("final");
        ca.setText(String.format("%.2f",ca_marks));
        total.setText(String.format("%.2f",calculateTotal(final_marks,ca_marks)));
        AddData();
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Marking2Activity.this, Marking3Activity.class);
                startActivity(intent);
            }
        });
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
                        Toast.makeText(Marking2Activity.this,"Data Not Inserted",Toast.LENGTH_LONG).show();
                    }
                    }
                }
        );
    }

    public float calculateTotal(float final_marks, float ca){
        float total = final_marks+ca;
        return total;
    }
}