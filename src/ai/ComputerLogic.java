package ai;

import ai.Graph.Move;
// import ai.Graph.Move;
import core.*;

public class ComputerLogic {
    private GameLogic logic;
    private Graph graph;
    private BFS bfs;
    
    public ComputerLogic(GameLogic logic, Graph graph, BFS bfs) {
        this.logic = logic;
        this.graph = graph;
        this.bfs = bfs;
    }

    public Move getComputerMove() {
        Move computerMove = findComputerMove();

        // //win/block is found
        // if (computerMove != null && computerMove.getIsWinningMove()) {
        //     return computerMove;
        // }

        return computerMove;
    }

    public Move findComputerMove() {
        Move move = null;

        //Get computer best move/Check for win
        Move playerBestMove = bfs.runBFS(0, logic.getCurrentToken());
        if (checkPotentialWin(playerBestMove)) {
            return playerBestMove;
        }

        //Get opponent best move/Check for win-block
        Move opponentBestMove = bfs.runBFS(0, logic.getOtherToken());
        if (checkPotentialWin(opponentBestMove)) {
            return opponentBestMove;
        }
        
        /*
         * Calculate other metrics for move if 4 isnt found
         */


        return move;
    }

    /**
     * 
     * @param move
     * @return
     */
    public boolean checkPotentialWin(Move move) {
        if (move.getIsWinningMove()) {
            return true;
        } else {
            return false;
        }
    }
    //start with BFS to do a quick search for winning or blocking oppponent win
    //switch to DFS if not found to look for best possible move
    //use token matrices to evaluate potential moves considering long term strat
    //possible heuristic evaluation to score board state 9 sequences, threats, etc)


    /*
     * Graph: nodes are potential next moves
     * first, check for potnetial win or block opponent win
     * Use bfs to simulate a move and keep track of its potential matrix count (maybe
     * the highest count out of the matrices for that simulated move. if therye equal use
     * another metric to decide which is selected)
     * best count out of nodes determines move, if they are the same use another metric
     */
}
