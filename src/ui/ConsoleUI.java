package ui;

import core.GameLogic;
import static interfaces.C4Constants.*;

public class ConsoleUI {
    private GameLogic logic;
    private char[][] board;
    
    public ConsoleUI(GameLogic logic) {
        this.logic = logic;
        this.board = logic.getBoard();
    }

    /**
     * 
     */
    public void startConsoleGame() {
        //display menu

        int choice = logic.getInput(START_INPUT);

        if (choice == PLAYER_PLAYER) {
            consoleGameFlow(PLAYER_PLAYER);
        } else {
            consoleGameFlow(COMPUTER_PLAYER);
        }
    }

    /**
     * 
     * @param gameMode
     */
    public void consoleGameFlow(int gameMode) {
        boolean end = false;
        logic.manageTurns();

        while (!end) {

        }
    }

    /**
     * 
     */
    public void displayBoard() {
        for (int i = ROWS - 1; i >= 0; i--) {
            for (int j = 0; j < COLUMNS; j++) {
                System.out.printf("| %c ", board[i][j]);
            }
            System.out.println("|");
        }
    }
}
