package core;

import dto.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameLogicTest {
    private TokenCounter tokenCounter;
    private GameLogic logic;

    @BeforeEach
    public void setUp() {
        tokenCounter = new TokenCounter();
        logic = new GameLogic(tokenCounter);
        logic.setCurrentToken('X');
    }

    @AfterEach
    public void tearDown() {
        // teardown code
        
    }

    //pass
    @Test
    public void testAddition() {
        assertEquals(2, 1 + 1);
        System.out.println("hi");
    }

    //fail
    @Test
    public void testAddition2() {
        assertEquals(3, 1 + 1);
        System.out.println("hi");
    }

    @Test 
    public void testHorizontalWinMatrix_NoWin() {
        TokenData[] horizontalRow = new TokenData[7];
        for (int i = 0; i < 7; i++) {
            horizontalRow[i] = new TokenData(0, '_');
        }

        tokenCounter.setHorizontalRow(0, horizontalRow);

        //set test row that will show false
        horizontalRow[0] = new TokenData(1, 'X');
        horizontalRow[1] = new TokenData(2, 'X');
        horizontalRow[2] = new TokenData(1, 'X');


    }

    @Test 
    public void testHorizontalWinMatrix_Win() {
        
    }
}
