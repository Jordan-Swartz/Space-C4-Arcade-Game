package ai;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import ai.Graph.Move;

public class BFS {

    /*
     * refernce graph
     * bfs method that create a list of possible moves for this current turn
     * for each move, simulate turn
     * store count
     * undo move, then proceed to next one until possible moves list is empty
     * return best move
     */

    private Graph graph;
    
    public BFS(Graph graph) {
        this.graph = graph;
    }

    public Move runBFS(int start, char token) {
        Iterator<Move> validMoves = graph.getValidMoves().iterator();
        ArrayList<Integer> bestMoveCounts = new ArrayList<>();
        Move bestMove = null;
        int bestCount = -1;
        int row = -1;
        int column = -1;

        while (validMoves.hasNext()) {
            //grab move
            Move move = validMoves.next();
            row = move.getRow();
            column = move.getColumn();

            //simulate move
            graph.simulateMove(row, column, token);

            //find best matrix count for that move
            int moveCount = graph.evaluateMove(row, column);

            //add moveCount to best list
            bestMoveCounts.add(moveCount);

            //undo move
            graph.undoMove(move.getRow(), move.getColumn(), token);

        }

        //evaluate list of counts to get best one
        // int bestCount = bestMoveCounts.get(0);

        for (int i = 0; i < bestMoveCounts.size(); i++) {
            if (bestCount < bestMoveCounts.get(i)) {
                bestCount = bestMoveCounts.get(i);
                bestMove = //grab arrayList index for move???
            }
        }

        return bestMove;
    }
}
