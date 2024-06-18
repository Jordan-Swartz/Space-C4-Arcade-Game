package test;

import core.*;
import core.TokenCounter.Direction;
import ui.*;

public class Test {
    private ConsoleUI console;
    private GameLogic logic;

    public Test(ConsoleUI console, GameLogic logic) {
        this.console = console;
        this.logic = logic;
    }
    
    public void runTest() {
        console.displayBoard();
        logic.displayMatrixBoard(Direction.HORIZONTAL);
        logic.displayMatrixBoard(Direction.VERTICAL);
        logic.displayMatrixBoard(Direction.DIAGONAL_LTR);
        logic.displayMatrixBoard(Direction.DIAGONAL_RTL);
        // logic.manageTurns();

        for (int i = 0; i < 100; i++) {

            int col = logic.getInput(2);
            int row = logic.findRow(col);
            logic.applyMove(row, col);   
            
            //test
            if (logic.checkHorizontalWin(row, col) == true) {
                System.out.println("4 in a row found - HZ");
            }

            if (logic.checkVerticalWin(row, col) == true) {
                System.out.println("4 in a row found - VT");
            }

            if (logic.checkDiagonalLTRWin(row, col) == true) {
                System.out.println("4 in a row found - D_LTR");
            }

            if (logic.checkDiagonalRTLWin(row, col) == true) {
                System.out.println("4 in a row found - D_RTL");
            }
            
            logic.incrementTurn();
            logic.manageTurns();

            console.displayBoard();
            logic.displayMatrixBoard(Direction.HORIZONTAL);
            logic.displayMatrixBoard(Direction.VERTICAL);
            logic.displayMatrixBoard(Direction.DIAGONAL_LTR);
            logic.displayMatrixBoard(Direction.DIAGONAL_RTL);
        }
    }

    public void runTest2() {
        console.startConsoleGame();
    }
}
