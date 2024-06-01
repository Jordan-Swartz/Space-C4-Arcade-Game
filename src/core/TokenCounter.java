package core;

/**
 * TokenCounter contains the matrices for the analytics of the board states. 
 * Contains the private nested class 'TokenInfo'.
 * 
 * @author Jordan Swartz
 * @version 1.0   
 */

import static interfaces.C4Constants.*;
import dto.TokenData;

public class TokenCounter {

    /**
     * TokenInfo contains the private object info for each matrix index.
     * 
     * @author Jordan Swartz
     * @version 1.0
     */

    private class TokenInfo {
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
    
    /**
     * Enum type that defines the directions within the matrices.
     */
    public enum Direction {
        HORIZONTAL,
        VERTICAL,
        DIAGONAL_LTR,
        DIAGONAL_RTL,
    }

    /**
     * Returns isolated row array from horizontal matrix.
     * 
     * @param row
     * @return TokenData[] row
     */
    public TokenData[] getHorizontalRow(int row) {
        TokenData[] rowData = new TokenData[COLUMNS];

        for (int i = 0; i < COLUMNS; i++) {
            rowData[i] = new TokenData(horizontalCounts[row][i].getCount(), 
                                       horizontalCounts[row][i].getToken());
        }

        return rowData;
    }

    /**
     * Returns isolated column array from vertical matrix.
     * 
     * @param col
     * @return TokenData[] column
     */
    public TokenData[] getVerticalCol(int col) {
       TokenData[] colData = new TokenData[ROWS];

       for (int i = 0; i < ROWS; i++) {
            colData[i] = new TokenData(verticalCounts[i][col].getCount(),
                                       verticalCounts[i][col].getToken());
       }

       return colData;
    }


    //add other GETTERS


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


}
