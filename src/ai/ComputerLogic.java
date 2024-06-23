package ai;

import core.*;

public class ComputerLogic {
    private GameLogic logic;
    
    public ComputerLogic(GameLogic logic) {
        this.logic = logic;
    }

    public int[] getComputerMove() {
        int[] computerMove = {0,0};
        return computerMove;
    }

    public boolean checkPotentialWin(char token) {
        return false;
    }

    //start with BFS to do a quick search for winning or blocking oppponent win
    //switch to DFS if not found to look for best possible move
    //use token matrices to evaluate potential moves considering long term strat
    //possible heuristic evaluation to score board state 9 sequences, threats, etc)
}
