package com.john.minefield.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {
    private Field field;

    @BeforeEach
    void fieldInit(){
        field = new Field(3, 3);
    }

    @Test
    void neighborDistancecTestOneLeft(){
        Field neighbor = new Field(3,2);
        boolean result = field.addNeighbors(neighbor);

        assertTrue(result);
    }
    @Test
    void neighborDistancecTestOneUp(){
        Field neighbor = new Field(2,3);
        boolean result = field.addNeighbors(neighbor);

        assertTrue(result);
    }

    @Test
    void neighborDistancecTestOneDown(){
        Field neighbor = new Field(4,3);
        boolean result = field.addNeighbors(neighbor);

        assertTrue(result);
    }

    @Test
    void neighborDistancecTestTwo(){
        Field neighbor = new Field(2,2);
        boolean result = field.addNeighbors(neighbor);

        assertTrue(result);
    }
    @Test
    void notANeighborTest(){
        Field neighbor = new Field(1,1);
        boolean result = field.addNeighbors(neighbor);

        assertFalse(result);
    }
}