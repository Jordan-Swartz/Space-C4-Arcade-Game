package controllers;

import java.io.Console;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ui.ConsoleUI;
import ui.UtilitesGUI;

public class GameModeController {
    private Stage stage;
    private ConsoleUI console;

    @FXML
    private VBox rootVBox;

    @FXML
    private Label modeLabel1;

    @FXML
    private Label modeLabel2;

    @FXML
    private Label modeLabel3;

    @FXML
    private Label modeLabel4;

    @FXML
    private Button guiButton;

    @FXML
    private Button consoleButton;

    /**
     * 
     */
    public void setConsole(ConsoleUI console) {
        this.console = console;
    }

    /**
     * 
     */
    public void initialize() {
        //apply size/fonts
        UtilitesGUI.setInitialSize(modeLabel1, 80, 3);
        UtilitesGUI.setInitialSize(modeLabel2, 80, 3);
        UtilitesGUI.setInitialSize(modeLabel3, 80, 3);
        UtilitesGUI.setInitialSize(modeLabel4, 80, 3);
        
        UtilitesGUI.setInitialSize(guiButton, 50, 1);
        UtilitesGUI.setInitialSize(consoleButton, 50, 1); 
    }
    
    /**
     * 
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    
        //bind labels/buttons to stage
        UtilitesGUI.makeLabelResponsive(modeLabel1, stage, 10, 40, 3);
        UtilitesGUI.makeLabelResponsive(modeLabel2, stage, 5, 20, 3);
        UtilitesGUI.makeLabelResponsive(modeLabel3, stage, 20, 120, 3);
        UtilitesGUI.makeLabelResponsive(modeLabel4, stage, 20, 120, 3);

        UtilitesGUI.makeButtonResponsive(guiButton, stage, 10, 50, 3);
        UtilitesGUI.makeButtonResponsive(consoleButton, stage, 5, 22.5, 3);
    
        rootVBox.prefWidthProperty().bind(stage.widthProperty());
        rootVBox.prefHeightProperty().bind(stage.heightProperty());
    }

    /**
     * 
     */
    public void handleGUIClick() {
        modeLabel2.setText("CHOOSE YOUR GAME TYPE!");
        guiButton.setText("SERVER");
        consoleButton.setText("LOCAL");

        guiButton.setOnAction(e -> handleServerClick());
        consoleButton.setOnAction(e -> handleLocalClick());
    }

    /**
     * 
     */
    // public void handleTextConsoleClick() {
    //     stage.close();
    //     console.startConsoleGame();
    // }

    public void handleTextConsoleClick() {
    Platform.runLater(() -> {
        stage.close();
        
        new Thread(() -> {
            if (console != null) {
                console.startConsoleGame();
            }
        }).start();
    });
}

    /**
     * 
     */
    public void handleServerClick() {

    }

    /**
     * 
     */
    public void handleLocalClick() {
        modeLabel2.setText("SELECT YOUR OPPONENT!");

        guiButton.setText("COMPUTER PLAYER");
        consoleButton.setText("LOCAL PLAYER");
    }
}

