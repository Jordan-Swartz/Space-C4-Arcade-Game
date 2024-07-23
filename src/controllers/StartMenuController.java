package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import ui.UtilitesGUI;

public class StartMenuController {
    private Stage stage;

    @FXML
    private Label titleLabel1;

    @FXML
    private Label titleLabel2;

    @FXML
    private Label titleLabel3;

    @FXML
    private Button startButton; 

    public void initialize() {
        //apply fonts
        UtilitesGUI.applyFont(titleLabel1);
        UtilitesGUI.applyFont(titleLabel2);
        UtilitesGUI.applyFont(titleLabel3);
        UtilitesGUI.applyFont(startButton);

        //apply size
        UtilitesGUI.setInitialSize(titleLabel1, 50);
        UtilitesGUI.setInitialSize(titleLabel2, 50);
        UtilitesGUI.setInitialSize(titleLabel3, 50);
    }
}
