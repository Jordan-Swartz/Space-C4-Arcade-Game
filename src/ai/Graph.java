package ai;

/**
 * 
 * 
 * @author Jordan Swartz
 * @version 1.0   
 */

import java.util.*;
import core.GameLogic;
import core.TokenCounter;
import core.TokenCounter.TokenInfo;

public class Graph {

    /**
     * Move is a nested inner class that contains the info for a possible move
     *  
     * @author Jordan Swartz
     * @version 1.0
     */

    public static class Move {
        private int row;
        private int column;
        private int moveCount;
        private boolean isWinningMove;

        public Move(int row, int column, int moveCount) {
            this.row = row;
            this.column = column;
            this.moveCount = moveCount;
            this.isWinningMove = moveCount >= 4;
        }

        public int getRow() {
            return row;
        }

        public int getColumn() {
            return column;
        }

        public int getMoveCount() {
            return moveCount;
        }

        public boolean getIsWinningMove() {
            return isWinningMove;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public void setColumn(int column) {
            this.column = column;
        }

        public void setMoveCount(int moveCount) {
            this.moveCount = moveCount;
        }
    }

    private GameLogic logic;
    private TokenCounter tokenCounter;
    private TokenInfo[][] horizontalCounts;
    private TokenInfo[][] verticalCounts;
    private TokenInfo[][] diagonalLTRCounts;
    private TokenInfo[][] diagonalRTLCounts;
    
    public Graph(GameLogic logic, TokenCounter tokenCounter) {
        this.logic = logic;
        this.tokenCounter = tokenCounter;
        this.horizontalCounts = tokenCounter.getHorizontalCounts();
        this.verticalCounts = tokenCounter.getVerticalCounts();
        this.diagonalLTRCounts = tokenCounter.getDiagonalLTRCounts();
        this.diagonalRTLCounts = tokenCounter.getDiagonalRTLCounts();
    }

    /**
     * Updates gameboard with simulated move.
     * 
     * @param row
     * @param col
     * @param token
     */
    public void simulateMove(int row, int col, char token) {
         logic.applyMove(row, col, token);
    }

    /**
     * Evaluates the matricies with the simulated move to see which matrix 
     * has the best count. 
     * 
     * @return bestCount
     */
    public int evaluateMove(int row, int column) {
        //create simulated-move count array
        int[] counts = new int[4];
        counts[0] = logic.getHorizontalCount(row, column);
        counts[1] = logic.getVerticalCount(row, column);
        counts[2] = logic.getDiagonalLTRCount(row, column);
        counts[3] = logic.getDiagonalRTLCount(row, column);

        //find best count
        int bestCount = counts[0];
        for (int i = 1; i < counts.length; i++) {
            if (bestCount < counts[i]) {
                bestCount = counts[i];
            }
        }

        return bestCount;
    }
    
    /**
     * 
     * @param row
     * @param col
     * @param token
     */
    public void undoMove(int row, int col, char token) {

    }

    /**
     * 
     * @param row
     * @param col
     * @param Token
     */
    public void updateMatrices(int row, int col, char Token) {
        //RETURN MATRIX BACK TO ORIGINAL STATE
    }

    /**
     * 
     * @return
     */
    public Iterable<Move> getValidMoves() {
        Iterable<Move> validMoves = logic.getValidMoves();;
        return validMoves;
    }

}
