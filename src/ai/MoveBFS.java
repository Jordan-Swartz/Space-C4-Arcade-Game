package ai;

import static interfaces.C4Constants.COLUMNS;

/**
 * MoveBFS contains the BFS-like algorithm to find the best computer move. 
 * 
 * @author Jordan Swartz
 * @version 1.0   
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import ai.Graph.Move;

public class MoveBFS {
    private Graph graph;
    private Random random;
    
    public MoveBFS(Graph graph, Random random) {
        this.graph = graph;
        this.random = random;
    }

    /**
     * Runs a BFS-like algorithm to find the best computer move.
     * 
     * @param start
     * @param token
     * @return bestMove
     */
    public Move runMoveBFS(int start, char token) {
        Iterator<Move> validMoves = graph.getValidMoves().iterator();
        ArrayList<Move> bestMoves = new ArrayList<>(); 
        Move bestMove = new Move(-1, -1, -1);

        while (validMoves.hasNext()) {
            //grab move data
            Move move = validMoves.next();
            int row = move.getRow();
            int column = move.getColumn();

            //simulate move
            graph.simulateMove(row, column, token);

            //find best matrix count for that move
            int moveCount = graph.evaluateMove(row, column, token);

            //win found
            if (moveCount >= 4) {          
                graph.undoMove(row, column);
                //return new move with win flag triggered
                return new Move(row, column, moveCount);
            }

            //add moveCount to best list 
            move.setMoveCount(moveCount);
            bestMoves.add(move);

            //undo move
            graph.undoMove(move.getRow(), move.getColumn());
        }

        //evaluate list of counts to get best one
        ArrayList<Move> topMoves = new ArrayList<>();
        bestMove = bestMoves.get(0);

        for (Move move : bestMoves) {
            if (move.getMoveCount() == bestMove.getMoveCount()) {
                topMoves.add(move);
            } else if (move.getMoveCount() > bestMove.getMoveCount()) {
                bestMove = move;
                topMoves.clear();
                topMoves.add(move);
            }
        }

        int center = COLUMNS / 2;
        for (Move move : topMoves) {
            if (move.getColumn() == center) {
               return move;
            }
        }
        
        //no center move found - return random placement from topMoves list
        int index = random.nextInt(topMoves.size());
        bestMove = topMoves.get(index);

        return bestMove;
    }
}
