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
    private TokenInfo[][] veritcalCounts;
    private TokenInfo[][] diagonalLTRCounts;
    private TokenInfo[][] diagonalRTLCounts;

    public TokenCounter() {
        horizontalCounts = new TokenInfo[ROWS][COLUMNS];
        veritcalCounts = new TokenInfo[ROWS][COLUMNS];
        diagonalLTRCounts = new TokenInfo[ROWS][COLUMNS];
        diagonalRTLCounts = new TokenInfo[ROWS][COLUMNS];

        //populate matrices with default values
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                horizontalCounts[i][j] = new TokenInfo();
                veritcalCounts[i][j] = new TokenInfo();
                diagonalLTRCounts[i][j] = new TokenInfo();
                diagonalRTLCounts[i][j] = new TokenInfo();
            }
        }
    }

    //methods to retireve data from matricies

}
