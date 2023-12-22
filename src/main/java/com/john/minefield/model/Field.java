package com.john.minefield.model;

import java.util.ArrayList;
import java.util.List;

public class Field {
    private final int row;
    private final int column;

    private boolean isOpen = false;
    private boolean undermined;
    private boolean marked;

    private List<Field> neighbors = new ArrayList<>();
    Field(int row, int column){
        this.row = row;
        this.column = column;
    }

    boolean addNeighbors(Field neighbor){
        boolean differentRow = row != neighbor.row;
        boolean differentColumn = column != neighbor.column;
        boolean diagonal = differentRow && differentColumn;

        int deltaRow = Math.abs(row - neighbor.row);
        int deltaColumn = Math.abs(column - neighbor.column);
        int generalDelta = deltaColumn + deltaRow;

        if(generalDelta == 1 && !diagonal){
            neighbors.add(neighbor);
            return true;
        }else if (generalDelta == 2 && diagonal){
            neighbors.add(neighbor);
            return true;
        }else{
            return false;
        }
    }
}
