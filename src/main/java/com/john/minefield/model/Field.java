package com.john.minefield.model;

import com.john.minefield.exception.ExplosionException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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

    void changeTag(){
        if(!isOpen){
            marked = !marked;
        }
    }

    boolean open(){
        if(!isOpen && !marked){
            isOpen = true;

            if(undermined){
                throw new ExplosionException();
            }
            if(securityNeighbor()){
                neighbors.forEach(Field::open);
            }
            return true;

        }else{
            return false;
        }
    }

    boolean securityNeighbor(){
        return neighbors.stream().noneMatch(v -> v.undermined);
    }

    void undermine(){
        undermined = true;
    }
    public boolean isMarked(){
        return marked;
    }

    public boolean isOpen(){
        return isOpen;
    }
    public boolean isClosed(){
        return !isOpen;
    }
}
