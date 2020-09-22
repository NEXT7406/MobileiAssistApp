package com.example.mobileiassistapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    EditText emailtxt;
    EditText passwordtxt;
    EditText passwordre;
    Button Signbtn;

    ProgressBar loadsign;

    private FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailtxt=(EditText)findViewById(R.id.emailsign);
        passwordtxt = (EditText)findViewById(R.id.passwordsign);
        passwordre = (EditText)findViewById(R.id.passresign);
       loadsign = (ProgressBar)findViewById(R.id.progressBarSign);
       loadsign.setIndeterminate(false);
       loadsign.setVisibility(View.INVISIBLE);
        mAuth = FirebaseAuth.getInstance();

        Signbtn = (Button)findViewById(R.id.signupbtn);

        Signbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SignUp();

            }
        });

    }

    private void SignUp(){

        loadsign.setVisibility(View.VISIBLE);
        loadsign.setIndeterminate(true);
        String email, password;
        email = emailtxt.getText().toString();
        password = passwordtxt.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email...", Toast.LENGTH_LONG).show();
          loadsign.setIndeterminate(false);
          loadsign.setVisibility(View.INVISIBLE);
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_LONG).show();
           loadsign.setIndeterminate(false);
           loadsign.setVisibility(View.INVISIBLE);
            return;
        }

        if(passwordre.getText().toString().equals(passwordtxt.getText().toString())) {

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                sendVerificationEmail();
                            } else {
                                Toast.makeText(getApplicationContext(), "Registration failed! Please try again later", Toast.LENGTH_LONG).show();
                               loadsign.setIndeterminate(false);
                                loadsign.setVisibility(View.INVISIBLE);

                            }
                        }
                    });

        }else{
            Toast.makeText(getApplicationContext(), "Entered Passwords Does Not Match", Toast.LENGTH_LONG).show();
            loadsign.setIndeterminate(false);
            loadsign.setVisibility(View.INVISIBLE);
        }
















    }
    private void sendVerificationEmail()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // email sent
                            loadsign.setIndeterminate(false);
                            Toast.makeText(getApplicationContext(), "Registration successful! verify your email", Toast.LENGTH_LONG).show();
                            loadsign.setVisibility(View.INVISIBLE);

                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(intent);

                            // after email is sent just logout the user and finish this activity
                            FirebaseAuth.getInstance().signOut();

                            finish();
                        }
                        else
                        {
                            // email not sent, so display message and restart the activity or do whatever you wish to do
                            Toast.makeText(getApplicationContext(), "Registration not successful. try again", Toast.LENGTH_LONG).show();
                            // restart this activity
                            overridePendingTransition(0, 0);
                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(getIntent());

                        }
                    }
                });
    }

}