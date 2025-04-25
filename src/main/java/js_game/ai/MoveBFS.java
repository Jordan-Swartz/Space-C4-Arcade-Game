package js_game.ai;

/**
 * The MoveBFS class implements a BFS-like algorithm to determine the best move
 * for the computer in a Connect Four game.
 *
 * @author Jordan Swartz
 * @version 1.0   
 */

import static js_game.interfaces.C4Constants.COLUMNS;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import js_game.ai.Graph.Move;

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
     * This method evaluates each valid move, simulates it, and calculates its
     * potential value. The best move is determined based on the highest move count.
     * If multiple moves have the same count, the center column is prioritized.
     *
     * @param start The starting position for the BFS-like algorithm (not used in the current logic).
     * @param token The token representing the computer's piece ('X' or 'O').
     * @return A Move object representing the best move for the computer.
     */
    public Move runMoveBFS(int start, char token) {
        Iterator<Move> validMoves = graph.getValidMoves().iterator();
        ArrayList<Move> bestMoves = new ArrayList<>(); 
        Move bestMove = new Move(-1, -1, -1);

        //evaluate each valid move
        while (validMoves.hasNext()) {
            //grab move data
            Move move = validMoves.next();
            int row = move.getRow();
            int column = move.getColumn();

            //simulate move
            graph.simulateMove(row, column, token);

            //find the best matrix count for that move
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
        Move previousMove = graph.getPreviousMove();

        if (previousMove.getRow() == 0 && previousMove.getColumn() == 3) {
            boolean isTwo = random.nextBoolean();
            int index = 4;

            if (isTwo) {
                index = 2;
            }     

            return new Move(0, index, -1);
        } else {
            for (Move move : topMoves) {
                if (move.getColumn() == center) {
                   return move;
                }
            }
        }
        
        //no center move found - return random placement from topMoves list
        int index = random.nextInt(topMoves.size());
        bestMove = topMoves.get(index);

        return bestMove;
    }
}
