package js_game.ai;

/**
 * Graph contains the logic and structure for the MoveBFS class
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
    private Random random;
    private TokenInfo[][] horizontalCounts;
    private TokenInfo[][] verticalCounts;
    private TokenInfo[][] diagonalLTRCounts;
    private TokenInfo[][] diagonalRTLCounts;
    
    public Graph(GameLogic logic, TokenCounter tokenCounter, Random random) {
        this.logic = logic;
        this.random = random;
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
         char[][] board = logic.getBoard();
         board[row][col] = token;
    }

    /**
     * Evaluates the matricies with the simulated move to see which matrix 
     * has the best count. 
     * 
     * @return bestCount
     */
    public int evaluateMove(int row, int column, char token) {
        //create simulated-move count array
        int[] counts = new int[] {
            logic.getDiagonalLTRCount(row, column, token),
            logic.getDiagonalRTLCount(row, column, token),
            logic.getHorizontalCount(row, column, token),
            logic.getVerticalCount(row, column, token)
        };

        //find best count
        int index = random.nextInt(2);
        int bestCount = counts[index];

        for (int count : counts) {
            if (count > bestCount) {
                bestCount = count;
            }
        }

        return bestCount;
    }
    
    /**
     * Updates gameboard back to original state.
     * 
     * @param row
     * @param col
     * @param token
     */
    public void undoMove(int row, int col) {
        char[][] board = logic.getBoard();
        board[row][col] = '_';
        updateMatrices(row, col);
    }

    /**
     * Updates matricies back to original states.
     * 
     * @param row
     * @param col
     * @param Token
     */
    public void updateMatrices(int row, int col) {
        TokenInfo[][][] matrices = new TokenInfo[][][] {
            horizontalCounts,
            verticalCounts,
            diagonalLTRCounts,
            diagonalRTLCounts
        };

        for (TokenInfo[][] matrix : matrices) {
            matrix[row][col].setCount(0);
            matrix[row][col].setToken('_');
        }
    }

    /**
     * Retrieves list of valid moves for current turn.
     * 
     * @return Iterable<Move>
     */
    public Iterable<Move> getValidMoves() {
        Iterable<Move> validMoves = logic.getValidMoves();;
        return validMoves;
    }

    /**
     * Retrieves the previous Move
     * 
     * @return previousMove
     */
    public Move getPreviousMove() {
        return logic.getPreviousMove();
    }
}
