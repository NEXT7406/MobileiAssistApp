package com.example.mobileiassistapp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
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