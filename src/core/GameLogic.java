package core;

/**
 * GameLogic defines and implements the methods for the core logic of the game.
 * 
 * @author Jordan Swartz
 * @version 1.0
 */

import static interfaces.C4Constants.*;
import interfaces.*;
import java.util.Random;
import java.util.Scanner;

public class GameLogic implements GameLogicInterface{
    private Random random;
    private Scanner scnr;
    private TokenCounter tokenCounter;
    private char[][] board;
    private int currentPlayer;
    private char currentToken;
    private int turn;


    public GameLogic(TokenCounter tokenCounter) {
        this.tokenCounter = tokenCounter;
        this.random = new Random();
        this.scnr = new Scanner(System.in);
        this.board = createBoard();
        this.currentPlayer = 1;
        this.currentToken = PLAYER1_TOKEN;
        this.turn = 0;
    }

    /**
     * Accessor method that returns the current game board instance
     * 
     * @return 2D array of chars: board
     */
    public char[][] getBoard() {
        return board;
    }

    /**
     * Creates a new game board and populates it with the default value '_'
     * 
     * @return new 2D array of chars: board
     */
    public char[][] createBoard() {
        char[][] board = new char[ROWS][COLUMNS];

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                board[i][j] = '_';
            }
        }

        return board;
    }

    /**
     * Handles alternation between player turns
     */
    public void manageTurns() {
        if (turn % 2 == 0) {
            //even player
           currentPlayer = PLAYER1;
           currentToken = PLAYER1_TOKEN;
        } else {
            //odd player
            currentPlayer = PLAYER2;
            currentToken = PLAYER2_TOKEN;
        }
    }

    /**
     * Increments turn variable
     */
    public void incrementTurn() {
        turn++;
    }

    /**
     * InputType: COLUMN_INPUT
     * Collect and return user column input
     * 
     * @param type
     * @return column num 
     * 
     * InputType: START_INPUT
     * Collect player choice 'P' or 'C' to determine game mode
     * 
     * @param type
     * @return opponent num
     */
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

    /**
     * Finds the first avaliable row for the given column
     * Returns -1 if no row is found
     * 
     * @param col
     * @return row index or -1
     */
    public int findRow(int col) {
        for (int i = 0; i < ROWS; i++) {
            if (board[i][col] == '_') {
                return i;
            }
        }

        return -1;
    }
    
    /**
     * Updates board location with currentToken
     * 
     * @param row
     * @param col
     */
    public void applyMove(int row, int col) {
        board[row][col] = currentToken;
    }

    //dynamic checking for wins

    //use tokenCounter to 

    TokenInfo tokenInfo = tokenCounter.getTokenInfo(0, 0, HORIZONTAL);
    TokenInfo[] row = tokenCounter.getHorizontalRow(0);
} 