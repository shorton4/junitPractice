package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

class ComputeWageTest {

    ComputeWage obj = new ComputeWage();

    @BeforeEach
    public void initEach(){

        //overtake the inputstream and put a value in side already
        ByteArrayInputStream in = new ByteArrayInputStream("John\n35".getBytes());
        System.setIn(in);

    }


    @Test
    void TestException() throws UnsupportedEncodingException {

        ByteArrayInputStream in = new ByteArrayInputStream("John\nxx\n35".getBytes());
        System.setIn(in);


        PrintStream standardOut = System.out;
        ByteArrayOutputStream captor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captor));

        obj.AcceptData();

        assertTrue(new String(captor.toByteArray(),"UTF-8").contains("You didn't enter a valid number"));

        System.setOut(standardOut);


    }


    @Test
    void acceptData() {

        obj.AcceptData();

        assertEquals("John", obj.getName());

        assertEquals(35,obj.getHours());



    }

    @Test
    void computeWage() {

        obj.AcceptData();
        obj.ComputeWage();

        assertEquals(525,obj.getGtotal());


    }

    @Test
    void display() {

        obj.AcceptData();
        obj.ComputeWage();

        PrintStream standardOut = System.out;
        ByteArrayOutputStream captor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captor));

        obj.display();

        assertEquals("The total wage of John is 525.0",captor.toString().trim());

        System.setOut(standardOut);

    }
}

