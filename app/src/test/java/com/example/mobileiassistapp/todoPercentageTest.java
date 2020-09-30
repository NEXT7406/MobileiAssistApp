package com.example.mobileiassistapp;

import org.junit.Test;

import static org.junit.Assert.*;

public class todoPercentageTest {

    @Test
    public void calculatePercentagePerItem() {
        float input = 3;
        float output;
        float expected = 33;
        double delta =0.4;

        TodoActivity todoActivity = new TodoActivity();
        output = todoActivity.calculatePercentagePerItem(input);
        assertEquals(expected,output,delta);


    }
}
