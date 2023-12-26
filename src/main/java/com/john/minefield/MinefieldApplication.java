package com.john.minefield;

import com.john.minefield.model.Board;
import com.john.minefield.vision.ConsoleBoard;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MinefieldApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinefieldApplication.class, args);

		Board board = new Board(6, 6, 3);

		new ConsoleBoard(board);

	}

}
