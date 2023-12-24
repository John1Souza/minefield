package com.john.minefield.model;

import com.john.minefield.exception.ExplosionException;
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
    void neighborDistanceTestOneLeft(){
        Field neighbor = new Field(3,2);
        boolean result = field.addNeighbors(neighbor);

        assertTrue(result);
    }
    @Test
    void neighborDistanceTestOneRight(){
        Field neighbor = new Field(3,4);
        boolean result = field.addNeighbors(neighbor);

        assertTrue(result);
    }
    @Test
    void neighborDistanceTestOneUp(){
        Field neighbor = new Field(2,3);
        boolean result = field.addNeighbors(neighbor);

        assertTrue(result);
    }

    @Test
    void neighborDistanceTestOneDown(){
        Field neighbor = new Field(4,3);
        boolean result = field.addNeighbors(neighbor);

        assertTrue(result);
    }

    @Test
    void neighborDistanceTestTwo(){
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

    @Test
    void defaultValueMarkedAttributeTest(){
        assertFalse(field.isMarked());
    }
    @Test
    void changeTagTest(){
        field.changeTag();
        assertTrue(field.isMarked());
    }

    @Test
    void changeTagMarkedTwoCallsTest(){
        field.changeTag();
        field.changeTag();
        assertFalse(field.isMarked());
    }

    @Test
    void openNotMarkedNotMinedTest(){
        assertTrue(field.open());
    }
    @Test
    void openMarkedNotMinedTest(){
        field.changeTag();
        assertFalse(field.open());
    }

    @Test
    void openMarkedMinedTest(){
        field.changeTag();
        field.undermine();
        assertFalse(field.open());
    }

    @Test
    void openNotMarkedMinedTest(){
        field.undermine();
        assertThrows(ExplosionException.class, () -> field.open());
    }
    @Test
    void openWithNeighborTest(){
        Field field11 = new Field(1, 1);

        Field field22 = new Field(2, 2);
        field22.addNeighbors(field11);

        field.addNeighbors(field22);
        field.open();

        assertTrue(field22.isOpen() && field11.isOpen());
    }

    @Test
    void openWithNeighborTest2(){
        Field field11 = new Field(1, 1);
        Field field12 = new Field(1, 1);
        field12.undermine();

        Field field22 = new Field(2, 2);
        field22.addNeighbors(field11);
        field22.addNeighbors(field12);

        field.addNeighbors(field22);
        field.open();

        assertTrue(field22.isOpen() && field11.isClosed());
    }

}