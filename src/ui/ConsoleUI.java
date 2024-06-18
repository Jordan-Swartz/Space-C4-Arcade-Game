package ui;

import core.GameLogic;
import static interfaces.C4Constants.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConsoleUI {
    private GameLogic logic;
    private char[][] board;
    
    public ConsoleUI(GameLogic logic) {
        this.logic = logic;
        this.board = logic.getBoard();
    }

    /**
     * Starts the selected console gamemode. 
     */
    public void startConsoleGame() {
        //call menu
        displayMenu();

        //collect gamemode input
        int choice = logic.getInput(START_INPUT);

        if (choice == PLAYER_PLAYER) {
            consoleGameFlow(PLAYER_PLAYER);
        } else {
            consoleGameFlow(COMPUTER_PLAYER);
        }
    }

    /**
     * Controls the game flow for text console UI.
     * 
     * @param gameMode
     */
    public void consoleGameFlow(int gameMode) {
        boolean end = false;
        int col = -1;
        int row = -1;
        logic.manageTurns();

        while (!end) {
            //display board
            displayBoard();

            //player v player
            if (gameMode == PLAYER_PLAYER) {
                //collect input
                boolean valid = false;

                while (!valid) {
                    col = logic.getInput(COLUMN_INPUT);
                    row = logic.findRow(col);

                    if (row != -1) {
                        logic.applyMove(row, col);
                        valid = true;
                    } else {
                        System.out.println("Invalid input. Please choose again.");
                    }
                }
            } else if (gameMode == COMPUTER_PLAYER) { 
                //player v computer          
                if (logic.getCurrentPlayer() == PLAYER1) {
                    //collect input
                    boolean valid = false;

                    while (!valid) {
                        col = logic.getInput(COLUMN_INPUT);
                        row = logic.findRow(col);

                        if (row != -1) {
                            logic.applyMove(row, col);
                            valid = true;
                        } else {
                            System.out.println("Invalid input. Please choose again.");
                        }
                    }
                } else {
                    System.out.println("Computer thinking...");

                    //collect computer input
                }
            }

            //check for win
            if (logic.checkHorizontalWin(row, col)) {
                end = true;
                displayBoard();
                logic.gameEnd(WINNER);
            } else if (logic.checkVerticalWin(row, col)) {
                end = true;
                displayBoard();
                logic.gameEnd(WINNER);
            } else if (logic.checkDiagonalLTRWin(row, col)) {
                end = true;
                displayBoard();
                logic.gameEnd(WINNER);
            } else if (logic.checkDiagonalRTLWin(row, col)) {
                end = true;
                displayBoard();
                logic.gameEnd(WINNER);
            } else if (logic.tieCheck()) { 
                end = true;
                displayBoard();
                logic.gameEnd(DRAW);
            } else {
                logic.incrementTurn();
                logic.manageTurns();
            }
        }
    }

    /**
     * Displays game board in its current state.
     */
    public void displayBoard() {
        for (int i = ROWS - 1; i >= 0; i--) {
            for (int j = 0; j < COLUMNS; j++) {
                System.out.printf("| %c ", board[i][j]);
            }
            System.out.println("|");
        }
    }

    /**
     * Displays the start menu.
     */
    public void displayMenu() {
        displayTitle();
        System.out.printf("Welcome! To start a game:\nEnter 'P' to play against another player.\nEnter 'C' to play against computer\n");
    }

    /**
     * Displays the ASCII text Title.
     */
    public void displayTitle() {
        InputStream input = getClass().getResourceAsStream("connect4ascii.txt");
        if (input != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("file not found.");
        }
    }
}
