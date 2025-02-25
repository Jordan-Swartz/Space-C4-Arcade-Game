package js_game.interfaces;

/**
 * The C4Constants interface defines the constant values used in the Connect Four game.
 * These constants include board dimensions, player identifiers, game modes, tokens,
 * game status indicators, and input types.
 *
 * Constants defined here are used across multiple classes for consistency.
 *
 * @author Jordan Swartz
 * @version 1.0
 */

public interface C4Constants {

    /** The number of rows in the Connect Four board. */
    int ROWS = 6;
    /** The number of columns in the Connect Four board. */
    int COLUMNS = 7;

    /** Player 1 identifier. */
    int PLAYER1 = 1;
    /** Player 2 identifier. */
    int PLAYER2 = 2;

    /** Identifier for Player vs. Player game mode. */
    int PLAYER_PLAYER = 3;
    /** Identifier for Player vs. Computer (AI) game mode. */
    int COMPUTER_PLAYER = 4;

    /** The token representing Player 1. */
    char PLAYER1_TOKEN = 'X';
    /** The token representing Player 2. */
    char PLAYER2_TOKEN = 'O';

    /** Character indicating a winning state. */
    char WINNER = 'W';
    /** Character indicating a game that ended in a draw. */
    char DRAW = 'D';

    /** Input type representing the selection of the game mode at the start. */
    int START_INPUT = 1;
    /** Input type representing a column selection during gameplay. */
    int COLUMN_INPUT = 2;

}