package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ui.UtilitesGUI;

public class GameModeController {
    private Stage stage;

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
        UtilitesGUI.setInitialSize(guiButton, 50, 1);
        UtilitesGUI.setInitialSize(consoleButton, 50, 1); 
    }
    
    /**
     * 
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    
        //bind labels to stage
        // UtilitesGUI.makeLabelResponsive(titleLabel1, stage, 20, 160, 3);
        // UtilitesGUI.makeLabelResponsive(titleLabel2, stage, 40, 160, 3);
        // UtilitesGUI.makeLabelResponsive(titleLabel3, stage, 40, 160, 3);
        UtilitesGUI.makeLabelResponsive(modeLabel1, stage, 10, 40, 3);
        UtilitesGUI.makeLabelResponsive(modeLabel2, stage, 5, 20, 3);
        UtilitesGUI.makeButtonResponsive(guiButton, stage, 10, 50, 3);
        UtilitesGUI.makeButtonResponsive(consoleButton, stage, 10, 50, 3);
    
        rootVBox.prefWidthProperty().bind(stage.widthProperty());
        rootVBox.prefHeightProperty().bind(stage.heightProperty());
    }
}

