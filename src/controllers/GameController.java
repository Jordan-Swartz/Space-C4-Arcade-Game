package controllers;

import static interfaces.C4Constants.*;

import ai.ComputerLogic;
import core.GameLogic;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import ui.ConsoleUI;

public class GameController {
    private Stage stage;
    private ConsoleUI console;
    private GameLogic logic;
    private ComputerLogic computer;
  
    private ImageView[][] imageViewArray;
    
    @FXML
    private AnchorPane gameBoardPane;

    @FXML
    public void initialize() {
        //create board and add to scene
        StackPane gameBoard = createGameBoardGrid();
        gameBoardPane.getChildren().add(gameBoard);

        //set constraints for gameboard in anchorpane.
        AnchorPane.setTopAnchor(gameBoard, 0.0);
        AnchorPane.setBottomAnchor(gameBoard, 0.0);
        AnchorPane.setLeftAnchor(gameBoard, 0.0);
        AnchorPane.setRightAnchor(gameBoard, 0.0);
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
     * @return
     */
    public StackPane createGameBoardGrid() {
        //create grid for game board
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        imageViewArray = new ImageView[ROWS][COLUMNS];

        Image emptySlot = new Image("/resources/images/empty_slot.png");
        Image emptySlotInner = new Image("/resources/images/empty_slot_inner.png");
        Image player1Token = new Image("/resources/images/red_token.png");
        Image player2Token = new Image("/resources/images/purple_token.png");
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
                imageViewArray[row][col] = slot; 

                final int column = col;
                slot.setOnMouseClicked(event -> handleCircleClick(column));
            }
        }

        StackPane container = new StackPane();
        container.getChildren().add(grid);
        container.setAlignment(Pos.CENTER);

        return container;
    }

    public void handleCircleClick(int column) {

    }

    public void updateGUIBoard(int row, int col, int playerNum, boolean apply) {

    }


}
