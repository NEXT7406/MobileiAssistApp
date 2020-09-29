package com.example.mobileiassistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class GpaActivity extends AppCompatActivity {
    Button calculate;
    Button home;

    double  tot_credit, tot_grade, gpa, grad1, grad2, grad3, grad4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpa);


        //calculate button
      calculate= (Button)findViewById(R.id.button2);

      //on click to the calculate button
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spinner credit1 = (Spinner) findViewById(R.id.spinner);
                Spinner credit2 = (Spinner) findViewById(R.id.spinner2);
                Spinner credit3 = (Spinner) findViewById(R.id.spinner3);
                Spinner credit4 = (Spinner) findViewById(R.id.spinner4);
                Spinner grade1 = (Spinner) findViewById(R.id.spinner5);
                Spinner grade2 = (Spinner) findViewById(R.id.spinner6);
                Spinner grade3 = (Spinner) findViewById(R.id.spinner7);
                Spinner grade4 = (Spinner) findViewById(R.id.spinner8);

               calGPA(Double.parseDouble(credit1.getSelectedItem().toString()),Double.parseDouble(credit2.getSelectedItem().toString()),Double.parseDouble(credit3.getSelectedItem().toString()),
                       Double.parseDouble(credit4.getSelectedItem().toString()),grade1.getSelectedItem().toString(),grade2.getSelectedItem().toString(),
                       grade3.getSelectedItem().toString(),grade4.getSelectedItem().toString());


            }
        });

        //on click to home button
        home = (Button) findViewById(R.id.button);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomeActivity();
            }
        });


        //drop down spinners with id
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(GpaActivity.this, android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.Credit));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(myAdapter);

        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(GpaActivity.this, android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.Credit));
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(myAdapter1);

        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(GpaActivity.this, android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.Credit));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(myAdapter2);

        Spinner spinner4 = (Spinner) findViewById(R.id.spinner4);
        ArrayAdapter<String> myAdapter3 = new ArrayAdapter<String>(GpaActivity.this, android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.Credit));
        myAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(myAdapter3);


        Spinner spinner5 = (Spinner) findViewById(R.id.spinner5);
        ArrayAdapter<String> myAdapter4 = new ArrayAdapter<String>(GpaActivity.this, android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.Grade));
        myAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(myAdapter4);

        Spinner spinner6 = (Spinner) findViewById(R.id.spinner6);
        ArrayAdapter<String> myAdapter5 = new ArrayAdapter<String>(GpaActivity.this, android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.Grade));
        myAdapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner6.setAdapter(myAdapter5);

        Spinner spinner7 = (Spinner) findViewById(R.id.spinner7);
        ArrayAdapter<String> myAdapter6 = new ArrayAdapter<String>(GpaActivity.this, android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.Grade));
        myAdapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner7.setAdapter(myAdapter6);

        Spinner spinner8 = (Spinner) findViewById(R.id.spinner8);
        ArrayAdapter<String> myAdapter7 = new ArrayAdapter<String>(GpaActivity.this, android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.Grade));
        myAdapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner8.setAdapter(myAdapter7);


    }


        //method to navigate to the home page when clicking on home button
    public void openHomeActivity() {
        Intent intent1 = new Intent(this, HomeActivity.class);
        startActivity(intent1);
    }


    public double calGPA(double c1,double c2,double c3,double c4, String g1,String g2,String g3,String g4){


                //Conditions
                //subject 1

                if (g1.equals("A+")) {
                    grad1 = 4.00;

                } else if (g1.equals("A")) {
                    grad1 = 4.00;

                } else if (g1.equals("A-")) {
                    grad1 = 3.70;

                } else if (g1.equals("B+")) {
                    grad1 = 3.30;

                } else if (g1.equals("B")) {
                    grad1 = 3.00;

                } else if (g1.equals("B-")) {
                    grad1 = 2.70;

                } else if (g1.equals("C+")) {
                    grad1 = 2.30;

                } else if (g1.equals("C")) {
                    grad1 = 2.00;

                } else if (g1.equals("C-")) {
                    grad1 = 1.70;

                } else if (g1.equals("D+")) {
                    grad1 = 1.30;

                } else if (g1.equals("D")) {
                    grad1 = 1.00;

                } else{
                    grad1 = 0.00;

                    //subject 2

                } if (g2.equals("A+")) {
                    grad2 = 4.00;

                } else if (g2.equals("A")) {
                    grad2 = 4.00;

                } else if (g2.equals("A-")) {
                    grad2 = 3.70;

                } else if (g2.equals("B+")) {
                    grad2 = 3.30;

                } else if (g2.equals("B")) {
                    grad2 = 3.00;

                } else if (g2.equals("B-")) {
                    grad2 = 2.70;

                } else if (g2.equals("C+")) {
                    grad2 = 2.30;

                } else if (g2.equals("C")) {
                    grad2 = 2.00;

                } else if (g2.equals("C-")) {
                    grad2 = 1.70;

                } else if (g2.equals("D+")) {
                    grad2 = 1.30;

                } else if (g2.equals("D")) {
                    grad2 = 1.00;

                } else{
                    grad2 = 0.00;

                }
                //subject 3

                if (g3.equals("A+")) {
                    grad3 = 4.00;

                } else if (g3.equals("A")) {
                    grad3 = 4.00;

                } else if (g3.equals("A-")) {
                    grad3 = 3.70;

                } else if (g3.equals("B+")) {
                    grad3 = 3.30;

                } else if (g3.equals("B")) {
                    grad3 = 3.00;

                } else if (g3.equals("B-")) {
                    grad3 = 2.70;

                } else if (g3.equals("C+")) {
                    grad3 = 2.30;

                } else if (g3.equals("C")) {
                    grad3 = 2.00;

                } else if (g3.equals("C-")) {
                    grad3 = 1.70;

                } else if (g3.equals("D+")) {
                    grad3 = 1.30;

                } else if (g3.equals("D")) {
                    grad3 = 1.00;

                } else{
                    grad3 = 0.00;

                }
                //subject 4

                if (g4.equals("A+")) {
                    grad4 = 4.00;

                } else if (g4.equals("A")) {
                    grad4 = 4.00;

                } else if (g4.equals("A-")) {
                    grad4 = 3.70;

                } else if (g4.equals("B+")) {
                    grad4 = 3.30;

                } else if (g4.equals("B")) {
                    grad4 = 3.00;

                } else if (g4.equals("B-")) {
                    grad4 = 2.70;

                } else if (g4.equals("C+")) {
                    grad4 = 2.30;

                } else if (g4.equals("C")) {
                    grad4 = 2.00;

                } else if (g4.equals("C-")) {
                    grad4 = 1.70;

                } else if (g4.equals("D+")) {
                    grad4 = 1.30;

                } else if (g4.equals("D")) {
                    grad4 = 1.00;

                } else{
                    grad4 = 0.00;

                }





                //calculations
                tot_credit = c1 + c2 + c3 + c4;
                tot_grade = (grad1 * c1) + (grad2 * c2) + (grad3 * c3) + (grad4 * c4);
                gpa = (tot_grade / tot_credit);

                //pass data to the displayGPAActivity
                Intent intent=new Intent(GpaActivity.this,displayGPAActivity.class);
                intent.putExtra("gpa",String.valueOf(gpa));
                startActivity(intent);
        return gpa;
    }

}














