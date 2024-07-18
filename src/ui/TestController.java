package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TestController {

    @FXML
    private Label messageLabel;

    @FXML
    private void handleButtonClick() {
        messageLabel.setText("Button Clicked!");
    }
}