package com.example.mobileiassistapp;

import org.junit.Test;

import static org.junit.Assert.*;

public class MarkingActivityTest {

    @Test
    public void onCreate() {
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
}