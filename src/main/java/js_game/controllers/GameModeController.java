package js_game.controllers;

import java.io.Console;
import static js_game.interfaces.C4Constants.*;

import js_game.ai.ComputerLogic;
import js_game.core.GameLogic;
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
import js_game.ui.ConsoleUI;
import js_game.ui.UtilitesGUI;

public class GameModeController {
    private Stage stage;
    private ConsoleUI console;
    private GameLogic logic;
    private ComputerLogic computer;

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
    public void initialize() {
        //apply size/fonts
        UtilitesGUI.setInitialSize(modeLabel1, 80, 3);
        UtilitesGUI.setInitialSize(modeLabel2, 80, 3);
        UtilitesGUI.setInitialSize(modeLabel3, 80, 3);
        UtilitesGUI.setInitialSize(modeLabel4, 80, 3);
        
        UtilitesGUI.setInitialSize(guiButton, 50, 3);
        UtilitesGUI.setInitialSize(consoleButton, 50, 3); 
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
     */
    public void handleGUIClick() {
        modeLabel2.setText("CHOOSE YOUR GAME TYPE!");
        guiButton.setText("SERVER");
        consoleButton.setText("LOCAL");

        UtilitesGUI.makeButtonResponsive(consoleButton, stage, 10, 50, 3);

        guiButton.setOnAction(e -> handleServerClick());
        consoleButton.setOnAction(e -> handleLocalClick());
    }

    /**
     * 
     */
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

        UtilitesGUI.makeButtonResponsive(guiButton, stage, 5, 22.5, 3);
        UtilitesGUI.makeButtonResponsive(consoleButton, stage, 5, 22.5, 3);

        guiButton.setText("COMPUTER");
        consoleButton.setText("LOCAL PLAYER");

        guiButton.setOnAction(e -> startGamePlay(COMPUTER_PLAYER));
        consoleButton.setOnAction(e -> startGamePlay(PLAYER_PLAYER));
    }

    public void startGamePlay(int opponent) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/C4GamePlay.fxml"));
            Parent root = loader.load();

            //load controller for game fxml
            GameController controller = loader.getController();

            controller.setConsole(console);
            controller.setLogic(logic);
            controller.setComputer(computer);
            controller.setOpponent(opponent);
            controller.setStage(stage);

            // Create a new stage
            // Stage stage = new Stage();
            // stage.setTitle("Test Window");

            // Set the scene for the new stage
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

