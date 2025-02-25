package js_game.ui;

/**
 * The ConsoleUI class provides a text-based user interface for the Connect Four game.
 * This class manages user input, displays the game board, and controls game flow
 * for both Player vs. Player and Player vs. Computer modes.
 *
 *
 * @author Jordan Swartz
 * @version 1.0
 */

import js_game.core.GameLogic;
import js_game.core.GameLogic.Direction;
import js_game.ai.ComputerLogic;
import js_game.ai.Graph.Move;

import static js_game.interfaces.C4Constants.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConsoleUI {
    private GameLogic logic;
    private ComputerLogic computer;
    private char[][] board;
    
    public ConsoleUI(GameLogic logic, ComputerLogic computer) {
        this.logic = logic;
        this.computer = computer;
        this.board = logic.getBoard();
    }

    /**
     * Starts the selected console game-mode.
     */
    public void startConsoleGame() {
        //call menu
        displayMenu();

        //collect game-mode input
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
     * @param gameMode The selected game mode (either PLAYER_PLAYER or COMPUTER_PLAYER).
     */
    public void consoleGameFlow(int gameMode) {
        boolean end = false;
        int col = -1;
        int row = -1;
        logic.manageTurns();

        while (!end) {
            //display board
            displayBoard();
            char token = logic.getCurrentToken();

            //player v player
            if (gameMode == PLAYER_PLAYER) {
                //collect input
                boolean valid = false;

                while (!valid) {
                    col = logic.getInput(COLUMN_INPUT);
                    row = logic.findRow(col);

                    if (row != -1) {
                        logic.applyMove(row, col, token);
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
                            logic.applyMove(row, col, token);
                            valid = true;
                        } else {
                            System.out.println("Invalid input. Please choose again.");
                        }
                    }
                } else {
                    System.out.println("Computer thinking...");

                    Move computerMove = computer.getComputerMove();
                    row = computerMove.getRow();
                    col = computerMove.getColumn();
    
                    logic.applyMove(row, col, token);
                }
            }

            //check for win
            if (logic.checkForWin(Direction.HORIZONTAL, row, col, token)) {
                end = true;
                displayBoard();
                logic.gameEnd(WINNER);

            } else if (logic.checkForWin(Direction.VERTICAL, row, col, token)) {
                end = true;
                displayBoard();
                logic.gameEnd(WINNER);

            } else if (logic.checkForWin(Direction.DIAGONAL_LTR, row, col, token)) {
                end = true;
                displayBoard();
                logic.gameEnd(WINNER);

            } else if (logic.checkForWin(Direction.DIAGONAL_RTL, row, col, token)) {
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

                // logic.displayMatrixBoard(Direction.HORIZONTAL);
                // logic.displayMatrixBoard(Direction.VERTICAL);
                // logic.displayMatrixBoard(Direction.DIAGONAL_LTR);
                // logic.displayMatrixBoard(Direction.DIAGONAL_RTL);
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
        InputStream input = getClass().getResourceAsStream("/resources/text/connect4ascii.txt");
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
