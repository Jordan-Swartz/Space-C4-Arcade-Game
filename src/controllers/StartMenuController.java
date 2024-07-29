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
    private Button exitButton; 

    @FXML
    private Label startLabel;

    @FXML
    private ImageView view;

    @FXML 
    private AnchorPane rootAnchor;

    /**
     * 
     */
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

    /**
     * 
     */
    // public void startGame() {
    //      try {
    //         Parent gameView = FXMLLoader.load(getClass().getResource("/resources/fxml/C4ModeMenu.fxml"));
    //         Stage stage = (Stage) startButton.getScene().getWindow();
    //         stage.setScene(new Scene(gameView));
    //
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    @FXML
    private void startGame() {
        try {
             // Create a new FXMLLoader instance
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/C4ModeMenu.fxml"));
        
        // Load the view
        Parent modeView = loader.load();
        
        // Get the controller associated with the loaded view
        GameModeController controller = loader.getController();
        
        // Create a new stage
        Stage modeStage = new Stage();
        
        // Set the scene with the loaded view
        modeStage.setScene(new Scene(modeView));
        modeStage.setTitle("Select Mode");
        
        // Call the setStage method on the controller to pass the stage
        controller.setStage(modeStage);
        
        // Show the new stage
        modeStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     */
    @FXML
    private void exitGame() {
        Stage stage = (Stage) rootVBox.getScene().getWindow();
        stage.close();
    }

}
