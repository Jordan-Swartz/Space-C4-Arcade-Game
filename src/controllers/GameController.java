package controllers;

import static interfaces.C4Constants.*;

import ai.ComputerLogic;
import ai.Graph.Move;
import core.GameLogic;
import core.GameLogic.Direction;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import ui.ConsoleUI;
import ui.UtilitesGUI;

public class GameController {
    private Stage stage;
    private Label statusLabel;
    private Label titleLabel;
    private Image player1Token;
    private Image player2Token; 
    private ConsoleUI console;
    private GameLogic logic;
    private ComputerLogic computer;
    private boolean end;
  
    private ImageView[][] imageViewArray;
    private int opponent;
    
    @FXML
    private AnchorPane statusLabelPane; 

    @FXML
    private AnchorPane titleLabelPane; 
    
    @FXML
    private AnchorPane gameBoardPane;

    @FXML
    private VBox rootVBox;

    @FXML
    public void initialize() {
        //initialize token images
        player1Token = new Image("/resources/images/red_token.png");
        player2Token = new Image("/resources/images/purple_token.png");

        //create titleLabel
        titleLabel = new Label("CONNECT FOUR GAME");
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.getStyleClass().add("custom-label-1");
        titleLabel.getStyleClass().add("hover-effect");

        //append to anchorpane
        titleLabelPane.getChildren().add(titleLabel);
        AnchorPane.setTopAnchor(titleLabel, 0.0);
        AnchorPane.setBottomAnchor(titleLabel, 0.0);
        AnchorPane.setLeftAnchor(titleLabel, 0.0);
        AnchorPane.setRightAnchor(titleLabel, 0.0);

        //create statusLabel
        statusLabel = new Label("Click a column to begin!");
        statusLabel.setAlignment(Pos.CENTER);

        //append to anchorpane
        statusLabelPane.getChildren().add(statusLabel);
        AnchorPane.setTopAnchor(statusLabel, 0.0);
        AnchorPane.setBottomAnchor(statusLabel, 0.0);
        AnchorPane.setLeftAnchor(statusLabel, 0.0);
        AnchorPane.setRightAnchor(statusLabel, 0.0);
 
        UtilitesGUI.setInitialSize(statusLabel, 30, 3);
        UtilitesGUI.setInitialSize(titleLabel, 60, 3);
    }

    /**
     * 
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;

        rootVBox.prefWidthProperty().bind(stage.widthProperty());
        rootVBox.prefHeightProperty().bind(stage.heightProperty());

        startGUIGame();
    }

    /**
     * 
     * @param console
     */
    public void setConsole(ConsoleUI console) {
        this.console = console;
    }

    /**
     * 
     * @param logic
     */
    public void setLogic(GameLogic logic) {
        this.logic = logic;
    }

    /**
     * 
     * @param computer
     */
    public void setComputer(ComputerLogic computer) {
        this.computer = computer;
    }

    /**
     * 
     * @param opponent
     */
    public void setOpponent(int opponent) {
        this.opponent = opponent;
    }

    public void startGUIGame() {
        logic.manageTurns();

        //create board and add to scene.
        StackPane gameBoard = createGameBoardGrid();
        gameBoardPane.getChildren().add(gameBoard);
 
        AnchorPane.setTopAnchor(gameBoard, 0.0);
        AnchorPane.setBottomAnchor(gameBoard, 0.0);
        AnchorPane.setLeftAnchor(gameBoard, 0.0);
        AnchorPane.setRightAnchor(gameBoard, 0.0);
    }

    /**
     * 
     * @return
     */
    public StackPane createGameBoardGrid() {
        //create grid for game board
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        imageViewArray = new ImageView[ROWS][COLUMNS];

        Image emptySlot = new Image("/resources/images/empty_slot.png");
        Image emptySlotInner = new Image("/resources/images/empty_slot_inner.png");
        Image backgroundImage = new Image("/resources/images/black_space.png"); 

        double cellSize = 90;

        //create the cells for the game board
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                StackPane cell = new StackPane();
                StackPane cellInner = new StackPane();

                BackgroundImage background = new BackgroundImage(
                    backgroundImage,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(cellSize, cellSize, false, false, false, false)
                );

                cell.setBackground(new Background(background));
                cell.setPadding(new Insets(0));

                // create image slot for token and add to cell
                ImageView slot = new ImageView(emptySlot);
                slot.setFitWidth(cellSize);
                slot.setFitHeight(cellSize);

                ImageView innerSlot = new ImageView(emptySlotInner);
                innerSlot.setFitWidth(cellSize - 20); 
                innerSlot.setFitHeight(cellSize - 20); 

                cellInner.getChildren().add(innerSlot);
                cell.getChildren().addAll(slot, cellInner);
                // cell.getChildren().add(slot); 

                //add cell to grid and add refernce to array
                grid.add(cell, col, ROWS - 1 - row); 
                imageViewArray[row][col] = innerSlot; 

                final int column = col;
                innerSlot.setOnMouseClicked(event -> handleCircleClick(column));
            }
        }

        StackPane container = new StackPane();
        container.getChildren().add(grid);
        container.setAlignment(Pos.CENTER);

        return container;
    }

    /**
     * 
     * @param column
     */
    public void handleCircleClick(int column) {
        if (end) {
            return;
        }

        char token = logic.getCurrentToken();
        int playerNumber = logic.getCurrentPlayer();

        if (opponent == PLAYER_PLAYER) {
            if (!performPlayerMove(column, playerNumber, token)) {
                return;
            }

        } else if (opponent == COMPUTER_PLAYER) {       
            if (!performPlayerMove(column, playerNumber, token)) {
                return;
            }
            
            if (!end) {
                token = logic.getCurrentToken();
                playerNumber = logic.getCurrentPlayer();
                performComputerMove(playerNumber, token);
            }
        }
    }

    /**
     * 
     * @param column
     * @param playerNumber
     * @param token
     * @return
     */
    public boolean performPlayerMove(int column, int playerNumber, char token) {
        int row = logic.findRow(column);
            
        if (row != -1) {
            logic.applyMove(row, column, token);
            updateGUIBoard(row, column, playerNumber, true);
            checkForGameEnd(row, column, playerNumber, token);
            return true;

        } else {
            updateGUIBoard(0, 0, playerNumber, false);
            return false;       
        }

    }

    /**
     * 
     * @param playerNumber
     * @param token
     */
    public void performComputerMove(int playerNumber, char token) {
        statusLabel.setText("Computer Thinking...");
        PauseTransition pause = new PauseTransition(Duration.seconds(2));

        pause.setOnFinished(event -> {
            Move computerMove = computer.getComputerMove();
            int row = computerMove.getRow();
            int column = computerMove.getColumn();
    
            logic.applyMove(row, column, token);
            updateGUIBoard(row, column, playerNumber, true);
            checkForGameEnd(row, column, playerNumber, token);
        });

        pause.play();
    }

    /**
     * 
     * @param row
     * @param col
     * @param playerNum
     * @param apply
     */
    public void updateGUIBoard(int row, int col, int playerNum, boolean apply) {
        if (apply) {
            ImageView innerSlot = imageViewArray[row][col];
            String labelText = "";

            if (playerNum == PLAYER1) {
                innerSlot.setImage(player1Token);
                labelText = String.format("Player %d chose column %d and row %d!", playerNum, col + 1, row + 1);
                statusLabel.setText(labelText);

            } else if (playerNum == PLAYER2) {
                innerSlot.setImage(player2Token);

                if (opponent == COMPUTER_PLAYER) {
                    labelText = String.format("Computer chose column %d and row %d!", col + 1, row + 1);
                } else {
                    labelText = String.format("Player %d chose column %d and row %d!", playerNum, col + 1, row + 1);
                }

                statusLabel.setText(labelText);

            }
        } else {
            statusLabel.setText("Column Full! Please choose again!");
        }
        
    }

    /**
     * 
     * @param row
     * @param col
     * @param token
     */
    public void checkForGameEnd(int row, int col, int playerNumber, char token) {
        if (logic.checkForWin(Direction.HORIZONTAL, row, col, token)) {
            end = true;
            logic.gameEnd(WINNER);
            gameEnd(WINNER, playerNumber);
            console.displayBoard();

        } else if (logic.checkForWin(Direction.VERTICAL, row, col, token)) {
            end = true;
            logic.gameEnd(WINNER);
            gameEnd(WINNER, playerNumber);
            console.displayBoard();

        } else if (logic.checkForWin(Direction.DIAGONAL_LTR, row, col, token)) {
            end = true;
            logic.gameEnd(WINNER);
            gameEnd(WINNER, playerNumber);
            console.displayBoard();

        } else if (logic.checkForWin(Direction.DIAGONAL_RTL, row, col, token)) {
            end = true;
            logic.gameEnd(WINNER);
            gameEnd(WINNER, playerNumber);
            console.displayBoard();

        } else if (logic.tieCheck()) { 
            end = true;
            logic.gameEnd(DRAW);
            gameEnd(DRAW, playerNumber);
            console.displayBoard();

        } else {
            logic.incrementTurn();
            logic.manageTurns();
            
        }
    }

    public void gameEnd(char condition, int playerNum) {
        String labelText = "";

        if (condition == WINNER) {
            if (opponent == PLAYER_PLAYER) {
                labelText = String.format("Winner! Player %s got four in a row!", playerNum);
                statusLabel.setText(labelText);
            } else if (opponent == COMPUTER_PLAYER) {
                labelText = String.format("Winner! Computer got four in a row!");
                statusLabel.setText(labelText);
            }

        } else if (condition == DRAW) {
            labelText = String.format("Game Over! It's a tie!");
            statusLabel.setText(labelText);
        }

    }


}
