package core;

/**
 * GameLogic defines and implements the methods for the core logic of the game.
 * 
 * @author Jordan Swartz
 * @version 1.0
 */

import static interfaces.C4Constants.*;
import java.util.Scanner;
import core.TokenCounter.*;
import dto.TokenData;

public class GameLogic {
    private Scanner scnr;
    private TokenCounter tokenCounter;
    private char[][] board;
    private int currentPlayer;
    private char currentToken;
    private int turn;


    public GameLogic(TokenCounter tokenCounter) {
        this.tokenCounter = tokenCounter;
        this.scnr = new Scanner(System.in);
        this.board = createBoard();
        this.currentPlayer = 1;
        this.currentToken = PLAYER1_TOKEN;
        this.turn = 0;
    }

    /**
     * Accessor method that returns the current game board instance.
     * 
     * @return 2D array of chars: board
     */
    public char[][] getBoard() {
        return board;
    }

    /**
     * Accessor method that returns the current player's number.
     * 
     * @return currentPlayer 
     */
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Accessor method that returns the current token.
     * 
     * @return currentToken
     */
    public char getCurrentToken() {
        return currentToken;
    }

    /**
     * Mutator method that sets the current token.
     * 
     * @param currentToken
     */
    public void setCurrentToken(char currentToken) {
        this.currentToken = currentToken;
    }

    /**
     * Creates a new game board and populates it with the default value '_'.
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
     * Handles alternation between player turns.
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
     * Increments turn variable.
     */
    public void incrementTurn() {
        turn++;
    }

    /**
     * InputType: COLUMN_INPUT
     * Collect and return user column input.
     * 
     * @param type
     * @return column num 
     * 
     * InputType: START_INPUT
     * Collect player choice 'P' or 'C' to determine game mode.
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
     * Finds the first avaliable row for the given column.
     * Returns -1 if no row is found.
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
     * Updates board location with currentToken.
     * 
     * @param row
     * @param col
     */
    public void applyMove(int row, int col) {
        board[row][col] = currentToken;
    }


    //dynamic checking methods for win types.

    /**
     * Testing method used to display selected matrix board.
     * 
     * @param direction
     */
    public void displayMatrixBoard(Direction direction) {
        tokenCounter.displayMatrix(direction);
    }


    /**
     * Checks for horizontal win and updates HZ TokenCounter matrix.
     * 
     * @param row
     * @param col
     * @return true if hz win found
     */
    public boolean checkHorizontalWin(int row, int col) {
        //grab row from HZ matrix
        TokenData[] rowArray =  tokenCounter.getHorizontalRow(row);
        int count = 1;

        //search left/update count
        for (int i = col-1; i >= 0; i--) {
            if (rowArray[i].getToken() == currentToken) {
                count++;
            } else {
                break;
            }
        }

        //search right/update count
        for (int j = col+1; j <= 6; j++) {
            if (rowArray[j].getToken() == currentToken) {
                count++;
            } else {
                break;
            }
        }

        //check for win count
        tokenCounter.updateTokenInfo(row, col, Direction.HORIZONTAL, count, currentToken);
        if (count >= 4) {
            return true;
        } else {
            return false;
        }
    }   

    /**
     * Checks for vertical win and updates VT TokenCounter matrix.
     * 
     * @param row
     * @param col
     * @return true if vt win found
     */
    public boolean checkVerticalWin(int row, int col) {
        //grab column from VT matrix
        TokenData[] colArray = tokenCounter.getVerticalCol(col);
        int count = 1;

        //search down/update count
        for (int i = row-1; i >= 0; i--) {
            if (colArray[i].getToken() == currentToken) {
                count++;
            } else {
                break;
            }
        }

        //search up/update count
        for (int j = row+1; j <= 5; j++) {
            if (colArray[j].getToken() == currentToken) {
                count++;
            } else {
                break;
            }
        }

        //check for win count
        tokenCounter.updateTokenInfo(row, col, Direction.VERTICAL, count, currentToken);
        if (count >= 4) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks for D_LTR win and updates D_LTR TokenCounter matrix.
     * 
     * @param row
     * @param col
     * @return true if D_LTR win found
     */
    public boolean checkDiagonalLTRWin(int row, int col) {
        TokenData[][] matrix = tokenCounter.getTokenMatrix(Direction.DIAGONAL_LTR);
        int count = 1;

        //move up/right 
        for (int i = row+1, j = col+1; i <= ROWS-1 && j <= COLUMNS-1; i++, j++) {
            if (matrix[i][j].getToken() == currentToken) {
                count++;
            } else {
                break;
            }
        }

        //move down/left 
        for (int i = row-1, j = col-1; i >= 0 && j >= 0; i--, j--) {
            if (matrix[i][j].getToken() == currentToken) {
                count++;
            } else {
                break;
            }
        }

        tokenCounter.updateTokenInfo(row, col, Direction.DIAGONAL_LTR, count, currentToken);
        if (count >= 4) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks for D_RTL win and updates D_RTL TokenCounter matrix.
     * 
     * @param row
     * @param col
     * @return true if D_RTL win found
     */
    public boolean checkDiagonalRTLWin(int row, int col) {
        TokenData[][] matrix = tokenCounter.getTokenMatrix(Direction.DIAGONAL_RTL);
        int count = 1;

        //move up/left 
        for (int i = row+1, j = col-1; i <= ROWS-1 && j >= 0; i++, j--) {
            if (matrix[i][j].getToken() == currentToken) {
                count++;
            } else {
                break;
            }
        }

         //move down/right 
         for (int i = row-1, j = col+1; i >= 0 && j <= COLUMNS-1; i--, j++) {
            if (matrix[i][j].getToken() == currentToken) {
                count++;
            } else {
                break;
            }
        }

        tokenCounter.updateTokenInfo(row, col, Direction.DIAGONAL_RTL, count, currentToken);
        if (count >= 4) {
            return true;
        } else {
            return false;
        }
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