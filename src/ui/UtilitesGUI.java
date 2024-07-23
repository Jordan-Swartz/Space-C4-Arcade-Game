package ui;

import javax.management.RuntimeErrorException;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class UtilitesGUI {
    private static Font arcade;

    /**
     * Static block to load fonts when class is first accessed.
     */
    static {
        arcade = Font.loadFont(UtilitesGUI.class.getResourceAsStream("/resources/fonts/AerologicaRegular-K7day.ttf"), 20);

        if (arcade == null) {
            throw new RuntimeException("Custom font 'AerologicaRegular-K7day.ttf' not loaded!");
        }
    }

    /**
     * 
     * @param label
     */
    public static void applyFont(Label label) {
        label.setFont(arcade);
    }

    /**
     * 
     * @param btn
     */
    public static void applyFont(Button btn) {
        btn.setFont(arcade);
    }

    /**
     * 
     * @param btn
     */
    public static void setInitialSize(Label label, double size) {
        label.setFont(new Font(arcade.getName(), size));
    }

    /**
     * 
     * @param btn
     */
    public static void setInitialSize(Button btn, double size) {
        btn.setStyle(String.format("-fx-font-size: %.2fpx;", size));
    }
    
    /**
     * 
     * @param label
     * @param stage
     * @param minSize
     * @param maxSize
     */
    public static void makeLabelResponsive(Label label, Stage stage, double minSize, double maxSize) {
        label.styleProperty().bind(Bindings.createStringBinding(() -> {
            double fontSize = Math.max(minSize, Math.min(maxSize, stage.getWidth() / 20));
            return String.format("-fx-font-size: %.2fpx;", fontSize);
        }, stage.widthProperty()));
    }
}
