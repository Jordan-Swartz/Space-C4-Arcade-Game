package js_game;

import js_game.core.*;
import js_game.ai.*;
import js_game.ai.Graph.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class ComputerLogicTest {

    private GameLogic logic;
    private TokenCounter counter;
    private Graph graph;
    private MoveBFS moveBFS;
    private ComputerLogic ai;

    @BeforeEach
    public void setup() {
        counter = new TokenCounter();
        logic = new GameLogic(counter);
        graph = new Graph(logic, counter, new Random(1));
        moveBFS = new MoveBFS(graph, new Random(1));
        ai = new ComputerLogic(logic, moveBFS);
    }

    @Test
    public void computerMakesWinningMove() {
        char token = 'X';
        int row = 0;

        //apply tokens and update TokenCounter
        for (int col = 0; col < 3; col++) {
            logic.applyMove(row, col, token);
            //update matrix logic
            logic.getHorizontalCount(row, col, token);
        }

        logic.setCurrentToken(token);
        Move move = ai.getComputerMove();

        assertEquals(0, move.getRow());
        assertEquals(3, move.getColumn());
        assertTrue(move.getIsWinningMove());
    }


    @Test
    public void computerBlocksOpponentWinningMove() {
        char opponent = 'O';
        char computer = 'X';
        int row = 0;

        for (int col = 0; col < 3; col++) {
            logic.applyMove(row, col, opponent);
            //update matrix logic
            logic.getHorizontalCount(row, col, opponent);
        }

        logic.setCurrentToken(computer);
        logic.setOtherToken(opponent);

        Move move = ai.getComputerMove();

        assertEquals(0, move.getRow());
        assertEquals(3, move.getColumn());
        assertTrue(move.getIsWinningMove());
    }


    @Test
    public void checkPotentialWinTrueWhenCountIsFour() {
        Move winning = new Move(0, 1, 4);
        assertTrue(ai.checkPotentialWin(winning));
    }

    @Test
    public void checkPotentialWinFalseWhenCountIsLessThanFour() {
        Move notWinning = new Move(1, 1, 3);
        assertFalse(ai.checkPotentialWin(notWinning));
    }
}
