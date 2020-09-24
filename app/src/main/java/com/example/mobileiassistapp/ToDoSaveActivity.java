package com.example.mobileiassistapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ToDoSaveActivity extends AppCompatActivity {

    String uuid;
    String uKey =null;
    EditText todoTitle;
    EditText todoMsg;
    Button saveBtn;
    FirebaseDatabase database;
    CalendarView calView;
    SimpleDateFormat format;
    TextView titleSave;
    String currDay;
    String currYear;
    String currMonth;

    String[] dateSplit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_save);
        titleSave = (TextView)findViewById(R.id.titleSaveActivity);
        todoTitle = (EditText) findViewById(R.id.titleTxt);
        todoMsg = (EditText) findViewById(R.id.messegeTxt);
        calView = (CalendarView)findViewById(R.id.calendarView);
        saveBtn = (Button) findViewById(R.id.savetodoBtn);

        uuid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        database = FirebaseDatabase.getInstance();
        format = new SimpleDateFormat("dd/MM/yyyy");
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTodo();
            }
        });


        String dateString1 = format.format(calView.getDate());

        dateSplit = dateString1.split("/");

        currDay = dateSplit[0];
        currMonth = dateSplit[1];
        currYear = dateSplit[2];



        if (getIntent().getExtras() != null) {
            Bundle extras = getIntent().getExtras();
            Todo todo = (Todo)extras.get("todo");
            if (todo != null) {


                todoTitle.setText(todo.getName());
                todoMsg.setText(todo.getMessage());
                uKey = todo.getID();

                saveBtn.setText("Update To-do");
                titleSave.setText("UPDATE TO DO");





                try {


                    Date date = format.parse(todo.getDate());

                    long millis = date.getTime();

                    calView.setDate(millis, true, true);

                    String[] dateSplit = todo.getDate().split("/");

                    currDay = dateSplit[0];
                    currMonth = dateSplit[1];
                    currYear = dateSplit[2];


                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        calView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {

                currDay = String.valueOf(i2);
                currYear = String.valueOf(i);
                currMonth = String.valueOf(i1+1);



            }
        });




    }
    void saveTodo() {




        if(todoTitle.getText().length() > 0) {


            String key = database.getReference("users").child(uuid).child("todoList").child("Active").push().getKey();


            if (uKey == null) {



               // SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");

                String dateString = currDay+"/"+currMonth+"/"+currYear;

                Todo todo = new Todo();
                todo.setName(todoTitle.getText().toString());
                todo.setMessage(todoMsg.getText().toString());
                todo.setDate(dateString);
                todo.setID(key);



                Map<String, Object> childUpdates = new HashMap<>();
                childUpdates.put(key, todo.toFirebaseObject());
                database.getReference("users").child(uuid).child("todoList").child("Active").updateChildren(childUpdates, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                        if (databaseError == null) {
                            finish();

                        }
                    }
                });
            } else

            {

             //  SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");

                String dateString = currDay+"/"+currMonth+"/"+currYear;

                Todo todo = new Todo();
                todo.setName(todoTitle.getText().toString());
                todo.setMessage(todoMsg.getText().toString());
                todo.setDate(dateString);
                todo.setID(uKey);


                Intent intent = new Intent();
                intent.putExtra("UpdateStat", "1");
                setResult(2, intent);


                Map<String, Object> childUpdates = new HashMap<>();
                childUpdates.put(uKey, todo.toFirebaseObject());

                database.getReference("users").child(uuid).child("todoList").child("Active").updateChildren(childUpdates, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError == null) {
                            finish();
                        }
                    }
                });


            }
        }
        else{
            Toast.makeText(getApplicationContext(), "Add To-Do name continue", Toast.LENGTH_SHORT).show();

        }
    }
    @Override
    public void onBackPressed() {

        Intent intent=new Intent();
        intent.putExtra("UpdateStat","0");
        setResult(2,intent);
        finish();

    }

}