package core;

import static interfaces.C4Constants.*;
import interfaces.*;
import java.util.Random;
import java.util.Scanner;


public class GameLogic implements GameLogicInterface{
    private Random random;
    private Scanner scnr;
    private char[][] board;
    private int currentPlayer;
    private int turn;


    public GameLogic() {
        this.random = new Random();
        this.scnr = new Scanner(System.in);
        this.board = createBoard();
        this.currentPlayer = 1;
        this.turn = 0;
    }

    public char[][] getBoard() {
        return board;
    }

    public char[][] createBoard() {
        char[][] board = new char[ROWS][COLUMNS];

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                board[i][j] = '_';
            }
        }

        return board;
    }

    public void manageTurns() {
        if (turn % 2 == 0) {
            //even player
           currentPlayer = 1;
        } else {
            //odd player
            currentPlayer = 2;
        }
    }

    public void incrementTurn() {
        turn++;
    }

    public int getInput(int type) {
        int input = -1;
        boolean valid = false;

        while (!valid) {
            if (type == COLUMN_INPUT) {
                System.out.println("Player" + currentPlayer + "-your turn. Choose a column number 1-7.");
                
                if (scnr.hasNextInt()) {
                    input = (scnr.nextInt() - 1);
                    scnr.nextLine(); //clear scanner

                    if (input < 0 || input > 6) {
                        System.out.println("Input out of bounds. Please choose again.");
                    } else {
                        valid = true;
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number");
                    scnr.nextLine(); //clear scanner
                }
                

            } else if (type == START_INPUT) {
                String choice = scnr.nextLine();

                if (choice.equals("P")) {
                    input = 1;
                    valid = true;
                } else if (choice.equals("C")) {
                    input = 2;
                    valid = true;
                } else {
                    System.out.println("Ivalid input. Please choose again.");
                }

            }
        }

        return input;
    }

    
} 