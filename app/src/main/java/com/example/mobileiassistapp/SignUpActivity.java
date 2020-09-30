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
    EditText emailTxt;
    EditText passwordTxt;
    EditText passwordRe;

    Button SignBtn;

    ProgressBar loadSign;

    private FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailTxt=(EditText)findViewById(R.id.emailsign);
        passwordTxt = (EditText)findViewById(R.id.passwordsign);
        passwordRe = (EditText)findViewById(R.id.passresign);
       loadSign = (ProgressBar)findViewById(R.id.progressBarSign);
       loadSign.setIndeterminate(false);
       loadSign.setVisibility(View.INVISIBLE);
        mAuth = FirebaseAuth.getInstance();

        SignBtn = (Button)findViewById(R.id.signupbtn);

        SignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SignUp();

            }
        });

    }

    private void SignUp(){

        loadSign.setVisibility(View.VISIBLE);
        loadSign.setIndeterminate(true);
        String email, password,passRe;
        email = emailTxt.getText().toString();
        passRe = passwordRe.getText().toString();
        password = passwordTxt.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email...", Toast.LENGTH_LONG).show();
          loadSign.setIndeterminate(false);
          loadSign.setVisibility(View.INVISIBLE);
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_LONG).show();
           loadSign.setIndeterminate(false);
           loadSign.setVisibility(View.INVISIBLE);
            return;
        }
        if (password.length() < 8) {
            Toast.makeText(getApplicationContext(), "Password must be at least 8 characters long", Toast.LENGTH_LONG).show();
            loadSign.setIndeterminate(false);
            loadSign.setVisibility(View.INVISIBLE);
            return;
        }
        if (passRe.length() < 8) {
            Toast.makeText(getApplicationContext(), "Re-entered password must be at least 8 characters long", Toast.LENGTH_LONG).show();
            loadSign.setIndeterminate(false);
            loadSign.setVisibility(View.INVISIBLE);
            return;
        }

        if(passwordRe.getText().toString().equals(passwordTxt.getText().toString())) {

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                sendVerificationEmail();
                            } else {
                                Toast.makeText(getApplicationContext(), "Registration failed! Please try again later", Toast.LENGTH_LONG).show();
                               loadSign.setIndeterminate(false);
                                loadSign.setVisibility(View.INVISIBLE);

                            }
                        }
                    });

        }else{
            Toast.makeText(getApplicationContext(), "Entered Passwords Does Not Match", Toast.LENGTH_LONG).show();
            loadSign.setIndeterminate(false);
            loadSign.setVisibility(View.INVISIBLE);
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

                            loadSign.setIndeterminate(false);
                            Toast.makeText(getApplicationContext(), "Registration successful! verify your email", Toast.LENGTH_LONG).show();
                            loadSign.setVisibility(View.INVISIBLE);

                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(intent);


                            FirebaseAuth.getInstance().signOut();

                            finish();
                        }
                        else
                        {

                            Toast.makeText(getApplicationContext(), "Registration not successful. try again", Toast.LENGTH_LONG).show();

                            overridePendingTransition(0, 0);
                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(getIntent());

                        }
                    }
                });
    }

}
