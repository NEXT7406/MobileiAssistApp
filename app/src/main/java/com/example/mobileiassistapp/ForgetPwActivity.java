package com.example.mobileiassistapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPwActivity extends AppCompatActivity {

    Button resetbtn;
    EditText emailres;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pw);
        resetbtn =(Button)findViewById(R.id.resetbtn);
        emailres = (EditText)findViewById(R.id.resettxt) ;
        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FirebaseAuth.getInstance().sendPasswordResetEmail(emailres.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    // Log.d(TAG, "Email sent.");
                                    Toast.makeText(ForgetPwActivity.this,
                                            "Email sent", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        });


    }
}