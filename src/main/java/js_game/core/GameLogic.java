package js_game.core;

/**
 * GameLogic defines and implements the methods for the core logic of the game.
 * It manages player turns, board updates, win conditions, and user inputs.
 * 
 * @author Jordan Swartz
 * @version 1.0
 */

import static js_game.interfaces.C4Constants.*;
import java.util.LinkedList;
import java.util.Scanner;
import js_game.ai.Graph.Move;
import js_game.core.TokenCounter.*;

public class GameLogic {
    private Scanner scnr;
    private TokenCounter tokenCounter;
    private char[][] board;
    private int currentPlayer;
    private char currentToken;
    private char otherToken;
    private int turn;
    private Move previousMove;


    public GameLogic(TokenCounter tokenCounter) {
        this.tokenCounter = tokenCounter;
        this.scnr = new Scanner(System.in);
        this.board = createBoard();
        this.currentPlayer = 1;
        this.currentToken = PLAYER1_TOKEN;
        this.otherToken = PLAYER2_TOKEN;
        this.turn = 0;
        this.previousMove = new Move (-1, -1, -1);
    }

    /**
     * Accessor method that returns the current game board instance.
     * 
     * @return 2D array of chars.
     */
    public char[][] getBoard() {
        return board;
    }

    /**
     * Accessor method that returns the current player's number.
     * 
     * @return The current-player number (1 or 2)
     */
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Accessor method that returns the current token.
     * 
     * @return The current-player token (X or O)
     */
    public char getCurrentToken() {
        return currentToken;
    }

    /**
     * Accessor method that returns the non-current token.
     * 
     * @return The other-player token (X or O)
     */
    public char getOtherToken() {
        return otherToken;
    }

    /**
     * Accessor method that returns the move of the previous turn.
     * 
     * @return A Move object representing the previous move.
     */
    public Move getPreviousMove() {
        return previousMove;
    }

    /**
     * Mutator method that sets the current token.
     * 
     * @param currentToken The current-player token (X or O)
     */
    public void setCurrentToken(char currentToken) {
        this.currentToken = currentToken;
    }

    /**
     * Mutator method that sets the non-current token.
     * 
     * @param otherToken The other-player token (X or O)
     */
    public void setOtherToken(char otherToken) {
        this.otherToken = otherToken;
    }

    /**
     * Creates a new game board and populates it with the default value '_'.
     * 
     * @return The new 2D array of chars.
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
     * Handles alternation between player turns.
     */
    public void manageTurns() {
        if (turn % 2 == 0) {
            //even player
           currentPlayer = PLAYER1;
           currentToken = PLAYER1_TOKEN;
           otherToken = PLAYER2_TOKEN;
        } else {
            //odd player
            currentPlayer = PLAYER2;
            currentToken = PLAYER2_TOKEN;
            otherToken = PLAYER1_TOKEN;
        }
    }

    /**
     * Increments turn variable.
     */
    public void incrementTurn() {
        turn++;
    }

    /**
     * Handles user input based on the input type.
     *
     * @param type The input type (either COLUMN_INPUT} for column selection
     * or {START_INPUT} for selecting game mode).
     *
     * @return The validated input value.
     */
    public int getInput(int type) {
        int input = -1;
        boolean valid = false;

        while (!valid) {
            if (type == COLUMN_INPUT) {
                System.out.println("Player" + currentPlayer + " - your turn. Choose a column number 1-7.");
                
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
                    input = 3;
                    valid = true;
                } else if (choice.equals("C")) {
                    input = 4;
                    valid = true;
                } else {
                    System.out.println("Ivalid input. Please choose again.");
                }

            }
        }

        return input;
    }

    /**
     * Finds the first available row for the given column.
     * Returns -1 if no row is found.
     * 
     * @param col The column index.
     * @return The row index of the available position, or -1 if the column is full.
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
     * Updates board location with currentToken.
     * 
     * @param row The row index
     * @param col The column index.
     * @param token The player's token.
     */
    public void applyMove(int row, int col, char token) {
        board[row][col] = token;      
        previousMove = new Move (row, col, -1);
    }

    /**
     * Returns a read-only list containing valid moves.
     *
     * @return An iterable list of valid move positions.
     */
    public Iterable<Move> getValidMoves() {
        LinkedList<Move> validMoves = new LinkedList<>();

        for (int i = 0; i < COLUMNS; i++) {
            int row = findRow(i);
            if (row != -1) {
                validMoves.add(new Move(row, i, -1));
            }
        }

        
        return validMoves;
    }

    /**
     * Testing method used to display selected matrix board.
     * 
     * @param direction
     */
    public void displayMatrixBoard(Direction direction) {
        tokenCounter.displayMatrix(direction);
    }


    /**
     * Calculates the consecutive tokens horizontally from a retrieved row.
     * 
     * @param row The row index.
     * @param col The column index.
     * @param token The player's token.
     * @return The count of consecutive tokens found in the horizontal direction.
     */
    public int getHorizontalCount(int row, int col, char token) {
        //grab row from HZ matrix
        TokenInfo[] rowArray =  tokenCounter.getHorizontalRow(row);
        int count = 1;

        //search left/update count
        for (int i = col-1; i >= 0; i--) {
            if (rowArray[i].getToken() == token) {
                count++;
            } else {
                break;
            }
        }

        //search right/update count
        for (int j = col+1; j <= 6; j++) {
            if (rowArray[j].getToken() == token) {
                count++;
            } else {
                break;
            }
        }

        //check for win count
        tokenCounter.updateTokenInfo(row, col, Direction.HORIZONTAL, count, token);
        return count;
    }

    /**
     * Calculates the consecutive tokens vertically from a retrieved column.
     *
     * @param row The row index.
     * @param col The column index.
     * @param token The player's token.
     * @return The count of consecutive tokens found in the vertical direction.
     */
    public int getVerticalCount(int row, int col, char token) {
        //grab column from VT matrix
        TokenInfo[] colArray = tokenCounter.getVerticalCol(col);
        int count = 1;

        //search down/update count
        for (int i = row-1; i >= 0; i--) {
            if (colArray[i].getToken() == token) {
                count++;
            } else {
                break;
            }
        }

        //search up/update count
        for (int j = row+1; j <= 5; j++) {
            if (colArray[j].getToken() == token) {
                count++;
            } else {
                break;
            }
        }

        //check for win count
        tokenCounter.updateTokenInfo(row, col, Direction.VERTICAL, count, token);
        return count;
    }

    /**
     * Calculates the consecutive tokens from left-to-right.
     *
     * @param row The row index.
     * @param col The column index.
     * @param token The player's token.
     * @return The count of consecutive tokens found from left-to-right.
     */
    public int getDiagonalLTRCount(int row, int col, char token) {
        TokenInfo[][] matrix = tokenCounter.getTokenMatrix(Direction.DIAGONAL_LTR);
        int count = 1;

        //move up/right 
        for (int i = row+1, j = col+1; i <= ROWS-1 && j <= COLUMNS-1; i++, j++) {
            if (matrix[i][j].getToken() == token) {
                count++;
            } else {
                break;
            }
        }

        //move down/left 
        for (int i = row-1, j = col-1; i >= 0 && j >= 0; i--, j--) {
            if (matrix[i][j].getToken() == token) {
                count++;
            } else {
                break;
            }
        }

        tokenCounter.updateTokenInfo(row, col, Direction.DIAGONAL_LTR, count, token);
        return count;
    }

    /**
     * Calculates the consecutive tokens from right-to-left.
     *
     * @param row The row index.
     * @param col The column index.
     * @param token The player's token.
     * @return The count of consecutive tokens found from right-to-left.
     */
    public int getDiagonalRTLCount(int row, int col, char token) {
        TokenInfo[][] matrix = tokenCounter.getTokenMatrix(Direction.DIAGONAL_RTL);
        int count = 1;

        //move up/left 
        for (int i = row+1, j = col-1; i <= ROWS-1 && j >= 0; i++, j--) {
            if (matrix[i][j].getToken() == token) {
                count++;
            } else {
                break;
            }
        }

         //move down/right 
         for (int i = row-1, j = col+1; i >= 0 && j <= COLUMNS-1; i--, j++) {
            if (matrix[i][j].getToken() == token) {
                count++;
            } else {
                break;
            }
        }

        tokenCounter.updateTokenInfo(row, col, Direction.DIAGONAL_RTL, count, token);
        return count;
    }

    /**
     * Enum type that defines the directions within the matrices.
     */
    public enum Direction {
        HORIZONTAL,
        VERTICAL,
        DIAGONAL_LTR,   //bottom (L) to top (R)
        DIAGONAL_RTL,   //bottom (R) to top (L)
    }

    /**
     * Checks if there is a winner in the specified direction.
     *
     * @param direction The direction to check.
     * @param row The row index.
     * @param col The column index.
     * @param token The player's token.
     * @return {@code true} if a winning sequence is found, otherwise {@code false}.
     */
    public boolean checkForWin(Direction direction, int row, int col, char token) {
        int count = -1;

        switch(direction) {
            case HORIZONTAL:
                count = getHorizontalCount(row, col, token);
                break;
            case VERTICAL:
                count = getVerticalCount(row, col, token);
                break;
            case DIAGONAL_LTR:
                count = getDiagonalLTRCount(row, col, token);
                break;
            case DIAGONAL_RTL:
                count = getDiagonalRTLCount(row, col, token);
                break;
        }

        return count >= 4;
    }

    /**
     * Checks to see if there are any remaining spaces on the board. 
     * 
     * @return true if no empty spaces remaining
     */
    public boolean tieCheck() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (board[i][j] == '_') {
                    return false;
                }   
            }
        }
        return true;
    }

    public void gameEnd(char condition) {
        if (condition == WINNER) {
            System.out.printf("%c got 4 in a row! Player%d wins!\n", currentToken, currentPlayer); 
        } else if (condition == DRAW) {
            System.out.printf("Game Over! It's a tie!\n");
        }
    }

} 