package core;

/**
 * TokenCounter contains the matrices for the analytics of the board states. 
 * Contains the private nested class 'TokenInfo'.
 * 
 * @author Jordan Swartz
 * @version 1.0   
 */

import static interfaces.C4Constants.*;

public class TokenCounter {

    /**
     * TokenInfo contains the object info for each matrix index
     * 
     * @author Jordan Swartz
     * @version 1.0
     */

    private class TokenInfo {
        int count;
        char token;

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


    //methods to retireve data from matricies

    /**
     * 
     * @param row
     * @return
     */
    public TokenInfo[] getHorizontalRow(int row) {
        return horizontalCounts[row];
    }

    /**
     * 
     * @param col
     * @return
     */
    public TokenInfo[] getVerticalCol(int col) {
        TokenInfo[] column = new TokenInfo[ROWS];

        for (int i = 0; i < ROWS; i++) {
            column[i] = verticalCounts[i][col];
        }

        return column;
    }

    /**
     * 
     * @param row
     * @param col
     * @param direction
     * @return
     */
    public TokenInfo getTokenInfo(int row, int col, Direction direction) {
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

    public void updateTokenInfo(int row, int col, Direction direction, int count, char token) {
        TokenInfo tokenInfo = getTokenInfo(row, col, direction);
        tokenInfo.setCount(count);
        tokenInfo.setToken(token);
    }

    ///// class access gamelogic -> tokencounter -> TokenInfo
    // redo tokenInfo and update methods so that the tokenCounter class is doing the actual 
    //updating. So in game logic it would be:
    // int tokenCount = tokenCounter.getTokenCount()
    //char tokenType = tokenCounter.getTokenType()

    //NOTE: MIGHT NOT NEED THE GET ROW, GET COL if you are modifying the data from tokenCOunter class
    // for (
    //  maybe do checking in TokenCOunter class
    //
    //

}
