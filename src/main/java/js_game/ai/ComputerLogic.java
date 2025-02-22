package js_game.ai;

/**
 * ComputerLogic defines and implements the methods for the basic AI computer algorithm.
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
     * Finds the computer's best move.
     * 
     * @return computerBestMove
     */
    public Move getComputerMove() {
       //Get computer best move/Check for win
       Move computerBestMove = bfs.runMoveBFS(0, logic.getCurrentToken());
       if (checkPotentialWin(computerBestMove)) {
           return computerBestMove;
       }

       //Get opponent best move/Check for win-block
       Move opponentBestMove = bfs.runMoveBFS(0, logic.getOtherToken());
       if (checkPotentialWin(opponentBestMove)) {
           return opponentBestMove;
       }

       return computerBestMove;
    }

    /**
     * Checks to see if move is flagged as a winning move. 
     * 
     * @param move
     * @return true is move is winning move
     */
    public boolean checkPotentialWin(Move move) {
        return move.getIsWinningMove();
    }
}
