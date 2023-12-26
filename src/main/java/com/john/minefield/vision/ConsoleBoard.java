package com.john.minefield.vision;

import com.john.minefield.exception.ExitException;
import com.john.minefield.exception.ExplosionException;
import com.john.minefield.model.Board;
import com.john.minefield.model.ColoredText;

import java.awt.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class ConsoleBoard {
    private Board board;
    private Scanner input = new Scanner(System.in);

    public ConsoleBoard(Board board) {
        this.board = board;

        runGame();
    }

    private void runGame() {
        try {
            boolean wantToContinue = true;

            while(wantToContinue){
                gameCicle();

                System.out.println(ColoredText.BLUE + "An other match? (Y/n) " + ColoredText.RESET);
                String answer = input.nextLine();
                if("n".equalsIgnoreCase(answer)){
                    wantToContinue = false;
                }else {
                    board.restart();
                }
            }

        } catch (ExitException e){
            System.out.println(ColoredText.PURPLE + "BYE!" + ColoredText.RESET);
        }finally {
            input.close();
        }
    }

    private void gameCicle() {
        try{

            while (!board.reachedGoal()){
                System.out.println(board);

                String firstChoice = ColoredText.ORANGE +
                        "Type (x, y) or exit, to exit: " + ColoredText.RESET;
                String secondChoice = ColoredText.CYAN +
                        "1 -> Open or 2 -> (Un)mark: " + ColoredText.RESET;

                String typed = captureEnteredValue(firstChoice);

                Iterator<Integer> xy = Arrays.stream(typed.split(","))
                        .map(e -> Integer.parseInt(e.trim())).iterator();

                typed = captureEnteredValue(secondChoice);

                if("1".equals(typed)){
                    board.open(xy.next(), xy.next());
                } else if("2".equals(typed)){
                    board.changeTag(xy.next(), xy.next());
                }
            }
            System.out.println(board);
            System.out.println(ColoredText.GREEN + "YOU WIN!" + ColoredText.RESET);
        }catch (ExplosionException e){
            System.out.println(board);
            System.out.println(ColoredText.RED + "YOU LOSE!" + ColoredText.RESET);
        }
    }

    private String captureEnteredValue(String text){
        System.out.print(text);
        String typed = input.nextLine();

        if("exit".equalsIgnoreCase(typed)){
            throw new ExitException();
        }

        return typed;
    }

}
