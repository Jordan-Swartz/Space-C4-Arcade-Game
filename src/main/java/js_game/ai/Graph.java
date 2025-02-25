package js_game.ai;

/**
 * Graph contains the logic and structure for the MoveBFS class
 * 
 * @author Jordan Swartz
 * @version 1.0   
 */

import java.util.*;
import js_game.core.GameLogic;
import js_game.core.TokenCounter;
import js_game.core.TokenCounter.TokenInfo;

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

        /**
         * Accessor method that returns the row index of the move.
         *
         * @return The row index of the move.
         */
        public int getRow() {
            return row;
        }

        /**
         * Accessor method that returns the row index of the move.
         *
         * @return The column index of the move.
         */
        public int getColumn() {
            return column;
        }

        /**
         * Accessor method that returns the move count.
         *
         * @return The number of tokens aligned as a result of this move.
         */
        public int getMoveCount() {
            return moveCount;
        }

        /**
         * Accessor method that returns the index-object's count.
         *
         * @return true if this move results in a win, otherwise false.
         */
        public boolean getIsWinningMove() {
            return isWinningMove;
        }

        /**
         * Sets the row index of the move.
         *
         * @param row The row to update.
         */
        public void setRow(int row) {
            this.row = row;
        }

        /**
         * Sets the column index of the move.
         *
         * @param column The column to update.
         */
        public void setColumn(int column) {
            this.column = column;
        }

        /**
         * Sets the move count associated with this move.
         *
         * @param moveCount The moveCount to update.
         */
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
     * Simulates placing a token on the game board.
     * This is used to evaluate potential AI moves before committing them.
     *
     * @param row The row index where the token is placed.
     * @param col The column index where the token is placed.
     * @param token The character representing the AI's token ('X' or 'O').
     */
    public void simulateMove(int row, int col, char token) {
         char[][] board = logic.getBoard();
         board[row][col] = token;
    }

    /**
     * Evaluates the board state after a simulated move to determine its effectiveness.
     * This method checks different matrix directions (horizontal, vertical, diagonals)
     * and returns the highest token alignment count.
     *
     * @param row The row index of the simulated move.
     * @param column The column index of the simulated move.
     * @param token The character representing the AI's token ('X' or 'O').
     * @return The highest token alignment count found.
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
     * Undoes a previously simulated move, restoring the board state.
     * This is used after evaluating a move so that other moves can be tested.
     *
     * @param row The row index of the move to undo.
     * @param col The column index of the move to undo.
     */
    public void undoMove(int row, int col) {
        char[][] board = logic.getBoard();
        board[row][col] = '_';
        updateMatrices(row, col);
    }

    /**
     * Resets the token matrices at a given position.
     * This ensures that token counts are properly updated after undoing a move.
     *
     * @param row The row index to reset.
     * @param col The column index to reset.
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
     * Retrieves a list of valid moves for the current turn.
     *
     * @return An Iterable<Move> containing valid move positions.
     */
    public Iterable<Move> getValidMoves() {
        Iterable<Move> validMoves = logic.getValidMoves();;
        return validMoves;
    }

    /**
     * Retrieves the previous move made in the game.
     * 
     * @return A Move object representing the last move.
     */
    public Move getPreviousMove() {
        return logic.getPreviousMove();
    }
}
