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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    EditText emailTxt;
    EditText passTxt;
    ProgressBar loading;
    Button btnForgot;
    Button btnSign;


    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();


        emailTxt = (EditText) findViewById(R.id.emaitxtlogin);
        passTxt = (EditText) findViewById(R.id.passtxt);
        loading = (ProgressBar) findViewById(R.id.progressBarLog);
        btnLogin = (Button) findViewById(R.id.loginbtn1);
        btnForgot = (Button) findViewById(R.id.forgotbtn);
        btnSign = (Button) findViewById(R.id.signbtn);

        loading.setIndeterminate(false);
        loading.setVisibility(View.INVISIBLE);

        btnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, ForgetPwActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, SignUpActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });
    }

    private void  Login(){

        loading.setVisibility(View.VISIBLE);
        loading.setIndeterminate(true);

        String email,password;
        email= emailTxt.getText().toString();
        password = passTxt.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email...", Toast.LENGTH_LONG).show();
          loading.setIndeterminate(false);
        loading.setVisibility(View.INVISIBLE);
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_LONG).show();
         loading.setIndeterminate(false);
          loading.setVisibility(View.INVISIBLE);
            return;
        }


        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {


                           EmailVerified();

                        }
                        else {

                            Toast.makeText(getApplicationContext(), "Login failed! Please try again later", Toast.LENGTH_LONG).show();
                            loading.setIndeterminate(false);
                            loading.setVisibility(View.INVISIBLE);

                        }
                    }
                });







    }

    private  void  EmailVerified(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user.isEmailVerified()){
           loading.setIndeterminate(false);
         loading.setVisibility(View.INVISIBLE);
           Intent myIntent = new Intent(MainActivity.this, HomeActivity.class);
           MainActivity.this.startActivity(myIntent);
            Toast.makeText(MainActivity.this, "Successfully logged in", Toast.LENGTH_SHORT).show();




        }else {
           loading.setIndeterminate(false);
           loading.setVisibility(View.INVISIBLE);

            Toast.makeText(getApplicationContext(), "Login failed! Make sure you verified the email", Toast.LENGTH_SHORT).show();
            FirebaseAuth.getInstance().signOut();

        }


    }

}