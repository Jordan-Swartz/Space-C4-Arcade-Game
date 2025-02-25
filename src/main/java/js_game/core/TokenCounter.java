package js_game.core;

/**
 * TokenCounter contains the matrices for the analytics of the board states.
 * It tracks token counts in different directions (horizontal, vertical, diagonal)
 *
 * Contains the nested class {@code TokenInfo} for managing individual token data.
 *
 * @author Jordan Swartz
 * @version 1.0   
 */

import static js_game.interfaces.C4Constants.*;
import js_game.core.GameLogic.Direction;

public class TokenCounter {

    /**
     * TokenInfo contains the private object info for each matrix index.
     * 
     * @author Jordan Swartz
     * @version 1.0
     */

    public class TokenInfo {
        private int count;
        private char token;

        /**
         * Initializes a new TokenInfo object with default values.
         */
        public TokenInfo() {
            this.count = 0;
            this.token = '_';
        }

        /**
         * Mutator method that sets the index-object's token.
         */
        public void setToken(char token) {
            this.token = token;
        }

        /**
         * Mutator method that sets the index-object's count.
         */
        public void setCount(int count) {
            this.count = count;
        }

        /**
         * Accessor method that returns the index-object's count.
         *
         * @return The index-object's count
         */
        public int getCount() {
            return count;
        }

        /**
         * Accessor method that returns the index-object's token.
         *
         * @return The index-object's token
         */
        public char getToken() {
            return token;
        }

    }

    /**
     * Matrices to store token counts for each direction
     */
    private TokenInfo[][] horizontalCounts;
    private TokenInfo[][] verticalCounts;
    private TokenInfo[][] diagonalLTRCounts;
    private TokenInfo[][] diagonalRTLCounts;

    /**
     * Initializes a new TokenCounter with empty matrices.
     */
    public TokenCounter() {
        horizontalCounts = new TokenInfo[ROWS][COLUMNS];
        verticalCounts = new TokenInfo[ROWS][COLUMNS];
        diagonalLTRCounts = new TokenInfo[ROWS][COLUMNS];
        diagonalRTLCounts = new TokenInfo[ROWS][COLUMNS];

        //populate matrices with default values
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                horizontalCounts[i][j] = new TokenInfo();
                verticalCounts[i][j] = new TokenInfo();
                diagonalLTRCounts[i][j] = new TokenInfo();
                diagonalRTLCounts[i][j] = new TokenInfo();
            }
        }
    }

    /**
     * Accessor method that returns the horizontal count.
     *
     * @return The horizontal count.
     */
    public TokenInfo[][] getHorizontalCounts() {
        return horizontalCounts;
    }

    /**
     * Accessor method that returns the vertical count.
     *
     * @return The vertical count.
     */
    public TokenInfo[][] getVerticalCounts() {
        return verticalCounts;
    }

    /**
     * Accessor method that returns the left-to-right diagonal count.
     *
     * @return The left-to-right diagonal count.
     */
    public TokenInfo[][] getDiagonalLTRCounts() {
        return diagonalLTRCounts;
    }

    /**
     * Accessor method that returns the right-to-left diagonal count.
     *
     * @return The right-to-left diagonal count.
     */
    public TokenInfo[][] getDiagonalRTLCounts() {
        return diagonalRTLCounts;
    }

    /**
     * Retrieves the matrix based on the specified direction.
     * 
     * @param direction The direction of the matrix to access.
     * @return The corresponding matrix
     */
    public TokenInfo[][] getTokenMatrix(Direction direction) {
        switch (direction) {
            case HORIZONTAL:
                return horizontalCounts;
            case VERTICAL:
                return verticalCounts;
            case DIAGONAL_LTR:
                return diagonalLTRCounts;
            case DIAGONAL_RTL:
                return diagonalRTLCounts;
            default:
                return null;
        }
    }

    /**
     * Returns isolated row array from horizontal matrix.
     * 
     * @param row The row index.
     * @return An array containing the TokenInfo objects for the specified row.
     */
    public TokenInfo[] getHorizontalRow(int row) {
        TokenInfo[] rowData = new TokenInfo[COLUMNS];

        for (int i = 0; i < COLUMNS; i++) {
            rowData[i] = horizontalCounts[row][i];
        }

        return rowData;
    }

    /**
     * Package-private method for unit testing.
     * Sets up the horizontal row with testing values.
     * 
     * @param row The row index.
     * @param rowData An array containing test TokenInfo values.
     */
    void setHorizontalRow(int row, TokenInfo[] rowData) {
        for (int i = 0; i < rowData.length; i++) {
            horizontalCounts[row][i].setCount(rowData[i].getCount());
            horizontalCounts[row][i].setToken(rowData[i].getToken());
        }
    }

    /**
     * Returns isolated column array from vertical matrix.
     * 
     * @param col The column index.
     * @return An array containing the TokenInfo objects for the specified column.
     */
    public TokenInfo[] getVerticalCol(int col) {
       TokenInfo[] colData = new TokenInfo[ROWS];

       for (int i = 0; i < ROWS; i++) {
            colData[i] = verticalCounts[i][col];
       }

       return colData;
    }

    /**
     * Private helper for updateTokenInfo that returns the correct TokenInfo Object.
     * 
     * @param row The row index.
     * @param col The column index.
     * @param direction The direction of the matrix to access.
     * @return The TokenInfo object at the specified position.
     */
    private TokenInfo getTokenInfo(int row, int col, Direction direction) {
        switch (direction) {
            case HORIZONTAL:
                return horizontalCounts[row][col];
            case VERTICAL:
                return verticalCounts[row][col];
            case DIAGONAL_LTR:
                return diagonalLTRCounts[row][col]; 
            case DIAGONAL_RTL:
                return diagonalRTLCounts[row][col];
            default:
                return null;
        }
    }

    /**
     * Updates TokenInfo Values within the specified matrix.
     * 
     * @param row The row index.
     * @param col The column index.
     * @param direction The direction of the matrix to update.
     * @param count The count of consecutive tokens.
     * @param token The token character ('X' or 'O').
     */
    public void updateTokenInfo(int row, int col, Direction direction, int count, char token) {
        TokenInfo tokenInfo = getTokenInfo(row, col, direction);
        tokenInfo.setCount(count);
        tokenInfo.setToken(token);
    }

    /**
     * Displays selected TokenCounter matrix.
     * 
     * @param direction The direction of the matrix to display.
     */
    public void displayMatrix(Direction direction) {
        TokenInfo[][] matrix;
        String matrixName = "";

        switch(direction) {
            case HORIZONTAL:
                matrix = horizontalCounts;
                matrixName = "Horizontal Matrix";
                break;
            case VERTICAL:
                matrix = verticalCounts;
                matrixName = "Vertical Matrix";
                break;
            case DIAGONAL_LTR:
                matrix = diagonalLTRCounts; 
                matrixName = "Diagonal LTR Matrix";
                break;
            case DIAGONAL_RTL:
                matrix = diagonalRTLCounts;
                matrixName = "Diagonal RTL Matrix";
                break;
            default:
                matrix = null;
                break;
        }

        System.out.printf("%s:\n", matrixName);
        for (int i = ROWS - 1; i >= 0; i--) {
            for (int j = 0; j < COLUMNS; j++) {
                char count = (char)('0' + matrix[i][j].getCount());
                System.out.printf("| %c ", count);
            }
            System.out.println("|");
        }
    }

}
