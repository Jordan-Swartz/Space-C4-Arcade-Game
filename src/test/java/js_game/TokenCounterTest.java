package js_game;


import js_game.core.TokenCounter;
import js_game.core.GameLogic.Direction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenCounterTest {

    @Test
    public void tokenInfoDefaultsToZeroAndUnderscore() {
        TokenCounter counter = new TokenCounter();
        TokenCounter.TokenInfo info = counter.getHorizontalCounts()[0][0];

        assertEquals(0, info.getCount());
        assertEquals('_', info.getToken());
    }

    @Test
    public void updateTokenInfoSetsValuesCorrectly() {
        TokenCounter counter = new TokenCounter();
        counter.updateTokenInfo(1, 2, Direction.VERTICAL, 3, 'O');

        TokenCounter.TokenInfo info = counter.getVerticalCounts()[1][2];
        assertEquals(3, info.getCount());
        assertEquals('O', info.getToken());
    }

    @Test
    public void setHorizontalRowUpdatesAllValues() {
        TokenCounter counter = new TokenCounter();
        TokenCounter.TokenInfo[] rowData = new TokenCounter.TokenInfo[7];

        for (int i = 0; i < 7; i++) {
            rowData[i] = counter.new TokenInfo();
            rowData[i].setCount(i);
            rowData[i].setToken((char) ('A' + i));
        }

        counter.setHorizontalRow(0, rowData);
        for (int i = 0; i < 7; i++) {
            TokenCounter.TokenInfo info = counter.getHorizontalCounts()[0][i];
            assertEquals(i, info.getCount());
            assertEquals((char) ('A' + i), info.getToken());
        }
    }
}

