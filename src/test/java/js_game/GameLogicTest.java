package js_game;

import js_game.core.GameLogic;
import js_game.core.TokenCounter;
import js_game.ai.Graph.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameLogicTest {

    private GameLogic logic;

    @BeforeEach
    public void setup() {
        logic = new GameLogic(new TokenCounter());
    }

    @Test
    public void boardInitializesEmpty() {
        char[][] board = logic.getBoard();
        for (char[] row : board) {
            for (char cell : row) {
                assertEquals('_', cell);
            }
        }
    }

    @Test
    public void findRowReturnsLowestEmptyRow() {
        int col = 3;
        int row = logic.findRow(col);
        assertEquals(0, row);

        logic.applyMove(row, col, 'X');
        int nextRow = logic.findRow(col);
        assertEquals(1, nextRow);
    }

    @Test
    public void applyMoveUpdatesBoardCorrectly() {
        logic.applyMove(2, 4, 'O');
        assertEquals('O', logic.getBoard()[2][4]);
    }

    @Test
    public void turnManagementAlternatesCorrectly() {
        logic.manageTurns();  // first turn
        assertEquals(1, logic.getCurrentPlayer());

        logic.incrementTurn();
        logic.manageTurns();  // second turn
        assertEquals(2, logic.getCurrentPlayer());
    }

    @Test
    public void horizontalCountReturnsExpectedCount() {
        TokenCounter counter = new TokenCounter();
        GameLogic localLogic = new GameLogic(counter);

        TokenCounter.TokenInfo[] testRow = new TokenCounter.TokenInfo[7];
        for (int i = 0; i < 7; i++) {
            testRow[i] = counter.new TokenInfo();
            testRow[i].setToken('_');
        }

        testRow[3].setToken('X');
        testRow[2].setToken('X');
        testRow[4].setToken('X');

        counter.setHorizontalRow(0, testRow);

        int count = localLogic.getHorizontalCount(0, 3, 'X');
        assertEquals(3, count);
    }
}
