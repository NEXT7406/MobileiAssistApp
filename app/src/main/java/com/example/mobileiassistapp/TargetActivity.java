package com.example.mobileiassistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

public class TargetActivity extends AppCompatActivity {

    private EditText canumber;
    private EditText caoutnum;
    private EditText dgrade;
    private Button cal;
    private TextView answer;
    private Spinner spinner;

    Spinner_TargetActivity sp;

    //spinner section


    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);

        canumber = (EditText) findViewById(R.id.editTextNumber);
        caoutnum = (EditText) findViewById(R.id.editTextNumber3);
      //  dgrade = (EditText) findViewById(R.id.editTextNumber4);
        cal = (Button) findViewById(R.id.button);
        answer = (TextView) findViewById(R.id.editTextNumber2);

        //spinner
        spinner = findViewById(R.id.spinner);
        sp= new Spinner_TargetActivity();

        List<Spinner_TargetActivity> gradelist = new ArrayList<>();
        Spinner_TargetActivity Spinner_TargetActivity1 = new Spinner_TargetActivity("A+", 85);
        gradelist.add(Spinner_TargetActivity1);

        Spinner_TargetActivity Spinner_TargetActivity2 = new Spinner_TargetActivity("A", 75);
        gradelist.add(Spinner_TargetActivity2);

        Spinner_TargetActivity Spinner_TargetActivity3 = new Spinner_TargetActivity("A-", 70);
        gradelist.add(Spinner_TargetActivity3);

        Spinner_TargetActivity Spinner_TargetActivity4 = new Spinner_TargetActivity("B+", 65);
        gradelist.add(Spinner_TargetActivity4);

        Spinner_TargetActivity Spinner_TargetActivity5 = new Spinner_TargetActivity("B", 60);
        gradelist.add(Spinner_TargetActivity5);

        Spinner_TargetActivity Spinner_TargetActivity6 = new Spinner_TargetActivity("B-", 55);
        gradelist.add(Spinner_TargetActivity6);

        Spinner_TargetActivity Spinner_TargetActivity7 = new Spinner_TargetActivity("C+", 50);
        gradelist.add(Spinner_TargetActivity7);

        Spinner_TargetActivity Spinner_TargetActivity8 = new Spinner_TargetActivity("C", 45);
        gradelist.add(Spinner_TargetActivity8);

        Spinner_TargetActivity Spinner_TargetActivity9 = new Spinner_TargetActivity("C-", 40);
        gradelist.add(Spinner_TargetActivity9);

        Spinner_TargetActivity Spinner_TargetActivity10 = new Spinner_TargetActivity("D+", 35);
        gradelist.add(Spinner_TargetActivity10);

        Spinner_TargetActivity Spinner_TargetActivity11 = new Spinner_TargetActivity("D", 30);
        gradelist.add(Spinner_TargetActivity11);

        ArrayAdapter<Spinner_TargetActivity> adapter = new ArrayAdapter<Spinner_TargetActivity>(this, android.R.layout.simple_spinner_item , gradelist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Spinner_TargetActivity Spinner_TargetActivity = (Spinner_TargetActivity) adapterView.getSelectedItem();
                String test = spinner.getSelectedItem().toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int dgrade1 =0;
                if(spinner.getSelectedItem().toString()=="A+"){
                    dgrade1=85;
                }
                if(spinner.getSelectedItem().toString()=="A"){
                    dgrade1=75;
                }
                if(spinner.getSelectedItem().toString()=="A-"){
                    dgrade1=70;
                }
                if(spinner.getSelectedItem().toString()=="B+"){
                    dgrade1=65;
                }
                if(spinner.getSelectedItem().toString()=="B"){
                    dgrade1=60;
                }  if(spinner.getSelectedItem().toString()=="B-"){
                    dgrade1=55;
                }  if(spinner.getSelectedItem().toString()=="C+"){
                    dgrade1=50;
                }
                if(spinner.getSelectedItem().toString()=="C"){
                    dgrade1=45;
                }
                if(spinner.getSelectedItem().toString()=="C-"){
                    dgrade1=40;
                }
                if(spinner.getSelectedItem().toString()=="D+"){
                    dgrade1=35;
                }
                if(spinner.getSelectedItem().toString()=="D"){
                    dgrade1=30;
                }

                int canumber1 = Integer.parseInt(canumber.getText().toString());
                int caoutnum1 = Integer.parseInt(caoutnum.getText().toString());
              //  int dgrade1 = Integer.parseInt(dgrade.getText().toString());

               // int dgrade1 =sp.getTgrademarks();
                System.out.println(dgrade1);
                System.out.println("SLIIT");
                int sum = (dgrade1 - canumber1) * 100 / (100 - caoutnum1);
                answer.setText("Answer:" + String.valueOf(sum));

                if (sum >= 100){
                    answer.setText("Target Grade Unachievable");
                }
                else {
                    answer.setText("Target:" + String.valueOf(sum));
                }

            }
        });

    }



    public void calculatetarget(View v) {
        Spinner_TargetActivity Spinner_TargetActivity = (Spinner_TargetActivity )spinner.getSelectedItem();
        targetmarks(Spinner_TargetActivity);
    }

    private static int targetmarks(Spinner_TargetActivity Spinner_TargetActivity){
        String name = Spinner_TargetActivity.getTgrade();
        int tmarks = Spinner_TargetActivity.getTgrademarks();
        return tmarks;
    }
}
