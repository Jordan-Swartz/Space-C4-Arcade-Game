package ai;

/**
 * 
 * 
 * @author Jordan Swartz
 * @version 1.0
 */

import ai.Graph.Move;
import core.*;

public class ComputerLogic {
    private GameLogic logic;
    private MoveBFS bfs;
    
    public ComputerLogic(GameLogic logic, MoveBFS bfs) {
        this.logic = logic;
        this.bfs = bfs;
    }

    /**
     * 
     * @return
     */
    public Move getComputerMove() {
       //Get computer best move/Check for win
       Move playerBestMove = bfs.runMoveBFS(0, logic.getCurrentToken());
       if (checkPotentialWin(playerBestMove)) {
           return playerBestMove;
       }

       //Get opponent best move/Check for win-block
       Move opponentBestMove = bfs.runMoveBFS(0, logic.getOtherToken());
       if (checkPotentialWin(opponentBestMove)) {
           return opponentBestMove;
       }

       return playerBestMove;
    }

    /**
     * 
     * @param move
     * @return
     */
    public boolean checkPotentialWin(Move move) {
        return move.getIsWinningMove();
    }
}
