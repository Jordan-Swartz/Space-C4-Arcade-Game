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

        public Move(int row, int column, int moveCount) {
            this.row = row;
            this.column = column;
            this.moveCount = moveCount;
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
     * 
     * @param row
     * @param col
     * @param token
     */
    public void simulateMove(int row, int col, char token) {
         logic.applyMove(row, col, token);
    }

    /**
     * 
     * @return
     */
    public int evaluateMove(int row, int column) {
        /*
         * Find which matrix to use as best count choice for this move. 
         */

        logic.getHorizontalCount(row, column);
        return 0;
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
