package com.example.mobileiassistapp;

import org.junit.Test;

import static org.junit.Assert.*;

public class todoFilledPercentageTest {

    @Test
    public void calFilledPercentage() {

        float input = 10;
        float output;
        float expected =10;
        double delta = 0.1;

        TodoActivity todoActivity = new TodoActivity();
        output = todoActivity.calFilledPercentage(input);
        assertEquals(expected,output,delta);




    }
}