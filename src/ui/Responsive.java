package ui;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Responsive {
    
    public static void makeLabelResponsive(Label label, Stage stage, double minSize, double maxSize) {
        label.styleProperty().bind(Bindings.createStringBinding(() -> {
            double fontSize = Math.max(minSize, Math.min(maxSize, stage.getWidth() / 20));
            return String.format("-fx-font-size: %.2fpx;", fontSize);
        }, stage.widthProperty()));
    }
}
