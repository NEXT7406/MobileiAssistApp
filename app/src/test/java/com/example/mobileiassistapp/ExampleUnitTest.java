package com.example.mobileiassistapp;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

private GpaActivity gpaActivity;
@Before
public void setup(){

    gpaActivity=new GpaActivity();
}

//test 01
@Test
    public void calculategpa(){
    double result=gpaActivity.calGPA(4,4,3,2,"C","A","C+","B-");
    double delta=Double.parseDouble("0.1");
    assertEquals(2.792307692307692,result,delta);

}




}