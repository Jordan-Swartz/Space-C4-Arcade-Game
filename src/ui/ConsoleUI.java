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

    public void startConsoleGame() {

    }

    public void displayBoard() {
        for (int i = ROWS - 1; i >= 0; i--) {
            for (int j = 0; j < COLUMNS; j++) {
                System.out.printf("| %c ", board[i][j]);
            }
            System.out.println("|");
        }
    }
}
