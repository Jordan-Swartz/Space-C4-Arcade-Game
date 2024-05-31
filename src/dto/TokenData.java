package dto;

/**
 * TokenData is the Data Transfer Object (DTO) for TokenInfo. It creates an immutable
 * copy of each TokenInfo object that allows for safe access by other classes. 
 * 
 * 
 * @author Jordan Swartz
 * @version 1.0   
 */

public class TokenData {
    private final int count;
    private final char token;

    public TokenData(int count, char token) {
        this.count = count;
        this.token = token;
    }

    public int getCount() {
        return count;
    }

    public char getToken() {
        return token;
    }
}
