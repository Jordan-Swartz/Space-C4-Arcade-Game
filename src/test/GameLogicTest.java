package test;

import core.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameLogicTest {
    @BeforeEach
    void setUp() {
        // setup code
    }

    @AfterEach
    void tearDown() {
        // teardown code
        
    }

    @Test
    void testAddition() {
        assertEquals(2, 1 + 1);
        System.out.println("hi");
    }

    @Test
    void testAddition2() {
        assertEquals(3, 1 + 1);
        System.out.println("hi");
    }
}
