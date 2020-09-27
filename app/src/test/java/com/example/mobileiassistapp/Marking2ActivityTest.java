package com.example.mobileiassistapp;

import org.junit.Test;

import static org.junit.Assert.*;

public class Marking2ActivityTest {

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
}