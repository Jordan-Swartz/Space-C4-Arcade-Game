package js_game.core;

/**
 * TokenCounter contains the matrices for the analytics of the board states. 
 * Contains the private nested class 'TokenInfo'.
 * 
 * @author Jordan Swartz
 * @version 1.0   
 */

import static interfaces.C4Constants.*;
import core.GameLogic.Direction;

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

        public TokenInfo() {
            this.count = 0;
            this.token = '_';
        }

        public void setToken(char token) {
            this.token = token;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getCount() {
            return count;
        }

        public char getToken() {
            return token;
        }

    }

    private TokenInfo[][] horizontalCounts;
    private TokenInfo[][] verticalCounts;
    private TokenInfo[][] diagonalLTRCounts;
    private TokenInfo[][] diagonalRTLCounts;

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

    public TokenInfo[][] getHorizontalCounts() {
        return horizontalCounts;
    }

    public TokenInfo[][] getVerticalCounts() {
        return verticalCounts;
    }

    public TokenInfo[][] getDiagonalLTRCounts() {
        return diagonalLTRCounts;
    }

    public TokenInfo[][] getDiagonalRTLCounts() {
        return diagonalRTLCounts;
    }

    /**
     * Returns selected matrix
     * 
     * @param direction
     * @return TokenInfo[][] matrix
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
     * @param row
     * @return TokenInfo[] row
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
     * @param row
     * @param rowData
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
     * @param col
     * @return TokenInfo[] column
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
     * @param row
     * @param col
     * @param direction
     * @return TokenInfo object
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
     * @param row
     * @param col
     * @param direction
     * @param count
     * @param token
     */
    public void updateTokenInfo(int row, int col, Direction direction, int count, char token) {
        TokenInfo tokenInfo = getTokenInfo(row, col, direction);
        tokenInfo.setCount(count);
        tokenInfo.setToken(token);
    }

    /**
     * Displays selected TokenCounter matrix.
     * 
     * @param direction
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
