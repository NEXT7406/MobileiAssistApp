package com.example.mobileiassistapp;

import org.junit.Test;

import static org.junit.Assert.*;

public class MarkingActivityTest2 {

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