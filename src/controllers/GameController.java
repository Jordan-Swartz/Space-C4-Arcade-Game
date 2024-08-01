package controllers;

import static interfaces.C4Constants.*;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GameController {
    private Circle[][] circleArray;
    
    @FXML
    private AnchorPane gameBoardPane;

    @FXML
    public void initialize() {
        StackPane gameBoard = createGameBoardGrid();
        gameBoardPane.getChildren().add(gameBoard);

        // Set constraints for the gameBoard inside the AnchorPane
        AnchorPane.setTopAnchor(gameBoard, 0.0);
        AnchorPane.setBottomAnchor(gameBoard, 0.0);
        AnchorPane.setLeftAnchor(gameBoard, 0.0);
        AnchorPane.setRightAnchor(gameBoard, 0.0);
    }

    public StackPane createGameBoardGrid() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        circleArray = new Circle[ROWS][COLUMNS];

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                StackPane cell = new StackPane();
                cell.setStyle("-fx-background-color: blue; -fx-padding: 5;");

                Circle circle = new Circle(30);
                circle.setFill(Color.NAVY);
                circle.setStroke(Color.BLACK);

                cell.getChildren().add(circle);
                grid.add(cell, col, ROWS - 1 - row); 
                circleArray[row][col] = circle;

                // final int column = col;
                // circle.setOnMouseClicked(event -> handleCircleClick(column));
            }
        }

        StackPane container = new StackPane();
        container.getChildren().add(grid);
        container.setAlignment(Pos.CENTER);

        return container;
    }
}
