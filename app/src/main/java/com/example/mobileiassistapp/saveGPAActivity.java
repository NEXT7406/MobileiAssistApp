package com.example.mobileiassistapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class saveGPAActivity extends AppCompatActivity {
    EditText txt1,txt2;
    Button save,update,delete,show,home;
    DatabaseReference dbRef;
    SaveGpa gpa;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_g_p_a);

        txt1=findViewById(R.id.editTitle);
        txt2=findViewById(R.id.editGPA);

        save=findViewById(R.id.button5);
        update=findViewById(R.id.button6);
        delete=findViewById(R.id.button7);
        show=findViewById(R.id.button9);
        home=findViewById(R.id.button10);

        gpa=new SaveGpa();
        dbRef=FirebaseDatabase.getInstance().getReference().child("savegpa");



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (TextUtils.isEmpty(txt1.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty title", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txt2.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty gpa", Toast.LENGTH_SHORT).show();
                    else {

                        gpa.setTitle(txt1.getText().toString().trim());
                        gpa.setGpa(Double.parseDouble(txt2.getText().toString().trim()));
                        dbRef.child("save1").setValue(gpa);

                        Toast.makeText(saveGPAActivity.this, "Successfully Inserted!", Toast.LENGTH_SHORT).show();
                        clearControl();
                    }
                } catch (NumberFormatException num) {
                    Toast.makeText(getApplicationContext(), "Invalid gpa", Toast.LENGTH_SHORT).show();

                }
            }
        });




        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef=FirebaseDatabase.getInstance().getReference().child("savegpa/save1");
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChildren()) {
                            txt1.setText(dataSnapshot.child("title").getValue().toString());
                            txt2.setText(dataSnapshot.child("gpa").getValue().toString());

                        }else
                            Toast.makeText(getApplicationContext(),"Cannot find save1",Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbRef=FirebaseDatabase.getInstance().getReference();
                dbRef.child("savegpa").child("save1").child("gpa").setValue(txt2.getText().toString().trim());
                Toast.makeText(getApplicationContext(),"Successfully Updated!",Toast.LENGTH_SHORT).show();
                clearControl();


            }
        });


    delete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dbRef=FirebaseDatabase.getInstance().getReference().child("savegpa").child("save1");
            dbRef.removeValue();
            Toast.makeText(getApplicationContext(),"Successfully Deleted!",Toast.LENGTH_SHORT).show();
            clearControl();
        }
    });



        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomeActivity();
            }
        });




    }

    private void clearControl(){

        txt1.setText("");
        txt2.setText("");
    }

    //navigate to the home page when clicking on home button
    public void openHomeActivity() {
        Intent intent1 = new Intent(this, HomeActivity.class);
        startActivity(intent1);
    }

}