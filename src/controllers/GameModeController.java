package controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ui.UtilitesGUI;

public class GameModeController {
    private Stage stage;

    @FXML
    private VBox rootVBox;

    @FXML
    private Label titleLabel;
}

 

///MAKE LABELS AND TEXT RESPONSIVE WITH CORRECT TE


public void initialize() {
    //apply size/fonts
    UtilitesGUI.setInitialSize(titleLabel1, 80, 3);
    UtilitesGUI.setInitialSize(titleLabel2, 80, 3);
    UtilitesGUI.setInitialSize(titleLabel3, 80, 3);
    UtilitesGUI.setInitialSize(startButton, 50, 1);
    UtilitesGUI.setInitialSize(startLabel, 50, 1);
}

/**
 * 
 * @param stage
 */
public void setStage(Stage stage) {
    this.stage = stage;

    //bind labels to stage
    UtilitesGUI.makeLabelResponsive(titleLabel1, stage, 20, 160, 3);
    UtilitesGUI.makeLabelResponsive(titleLabel2, stage, 40, 160, 3);
    UtilitesGUI.makeLabelResponsive(titleLabel3, stage, 40, 160, 3);
    UtilitesGUI.makeLabelResponsive(startLabel, stage, 5, 20, 3);
    UtilitesGUI.makeButtonResponsive(startButton, stage, 10, 50, 3);

    rootVBox.prefWidthProperty().bind(stage.widthProperty());
    rootVBox.prefHeightProperty().bind(stage.heightProperty());
}