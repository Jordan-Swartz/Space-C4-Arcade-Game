package controllers;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ui.UtilitesGUI;

public class StartMenuController {
    private Stage stage;

    @FXML
    private VBox rootVBox;

    @FXML
    private Label titleLabel1;

    @FXML
    private Label titleLabel2;

    @FXML
    private Label titleLabel3;

    @FXML
    private Button startButton; 

    @FXML
    private ImageView view;

    @FXML 
    private AnchorPane rootAnchor;

    /**
     * 
     */
    public void initialize() {
        //apply fonts
        // UtilitesGUI.applyFont(titleLabel1);
        // UtilitesGUI.applyFont(titleLabel2);
        // UtilitesGUI.applyFont(titleLabel3);
        // UtilitesGUI.applyFont(startButton);

        //apply size
        UtilitesGUI.setInitialSize(titleLabel1, 80, 2);
        UtilitesGUI.setInitialSize(titleLabel2, 80, 2);
        UtilitesGUI.setInitialSize(titleLabel3, 80, 2);
        UtilitesGUI.setInitialSize(startButton, 50, 1);
    }

    /**
     * 
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;

        //bind labels to stage
        UtilitesGUI.makeLabelResponsive(titleLabel1, stage, 40, 120, 2);
        UtilitesGUI.makeLabelResponsive(titleLabel2, stage, 40, 120, 2);
        UtilitesGUI.makeLabelResponsive(titleLabel3, stage, 40, 120, 2);
        UtilitesGUI.makeButtonResponsive(startButton, stage, 10, 50, 1);

        //bind images to stage
        // UtilitesGUI.makeImageResponsive(view, stage, rootAnchor, "/resources/images/arcadeLogo.png");

        rootVBox.prefWidthProperty().bind(stage.widthProperty());
        rootVBox.prefHeightProperty().bind(stage.heightProperty());

        // Adjust padding/margin dynamically
        // stage.widthProperty().addListener((observable, oldValue, newValue) -> adjustPadding());
        // adjustPadding();
    }

    // private void adjustPadding() {
    //     double stageWidth = stage.getWidth();
    //     double leftPadding = stageWidth * 0.03; // Adjust this ratio as needed

    //     rootAnchor.setPadding(new Insets(0, 0, 0, leftPadding));
    // }
}
