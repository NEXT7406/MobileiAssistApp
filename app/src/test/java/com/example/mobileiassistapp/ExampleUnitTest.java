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
    public void calculategpaTest1(){
    double result=gpaActivity.calGPA(4,4,3,2,"C","A","C+","B-");
    double delta=Double.parseDouble("0.1");
    assertEquals(2.792307692307692,result,delta);

}

//test 02
    @Test
    public void calculategpaTest2(){

        double result=gpaActivity.calGPA(2,4,1,4,"D+","C+","A-","B-");
        double delta=Double.parseDouble("0.1");
        assertEquals(2.3909090,result,delta);

    }


//test 03
@Test
public void calculategpaTest3(){

    double result=gpaActivity.calGPA(4,4,1,2,"A","B-","A+","D+");
    double delta=Double.parseDouble("0.1");
    assertEquals(3.03636363,result,delta);

}
//test 04
    @Test
public void calculategpaTest4(){

    double result=gpaActivity.calGPA(1,3,1,2,"E","B-","C","D+");
    double delta=Double.parseDouble("0.1");
    assertEquals(1.81428571428,result,delta);

}




    @Test
    public void calculateCAMarks() {
        float assg1 = 70;
        float assg2 = 80;
        float mid = 90;
        float expected = Float.parseFloat("40.50");
        float delta = Float.parseFloat("0.1");

        MarkingActivity markingActivity = new MarkingActivity();

        float output =  markingActivity.calculateCAMarks(assg1,assg2,mid);

        assertEquals(expected,output,delta);
    }

    @Test
    public void calculateTotal() {
        float ca = 50;
        float final_marks= 44;
        float expect = 94;
        float delta = Float.parseFloat("0.1");

        Marking2Activity marking2Activity = new Marking2Activity();

        float output = marking2Activity.calculateTotal(final_marks,ca);

        assertEquals(expect,output,delta);
    }

    @Test
    public void calculateFinalMarks() {
        float finalMarksOutOf100 = 70;
        float expect = 35;
        float delta = Float.parseFloat("0.1");

        MarkingActivity markingActivity = new MarkingActivity();
        float output = markingActivity.calculateFinalMarks(finalMarksOutOf100);

        assertEquals(expect,output,delta);
    }


}

