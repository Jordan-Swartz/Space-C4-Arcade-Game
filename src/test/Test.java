package test;

import core.*;
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
        // logic.manageTurns();

        for (int i = 0; i < 100; i++) {
            int col = logic.getInput(2);
            int row = logic.findRow(col);
            logic.applyMove(row, col);   
            
            //test
            logic.checkHorizontalWin(row, col);
            
            logic.incrementTurn();
            logic.manageTurns();

            console.displayBoard();
        }

    }
}
