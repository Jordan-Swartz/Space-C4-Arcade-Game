package ui;

import javax.management.RuntimeErrorException;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
     * Sets font style of label.
     * 
     * @param label
     */
    public static void applyFont(Label label) {
        label.setFont(arcade);
    }

    /**
     * Sets font style of button.
     * 
     * @param btn
     */
    public static void applyFont(Button btn) {
        btn.setFont(arcade);
    }

    /**
     * Sets initial size of label.
     * 
     * @param label
     */
    public static void setInitialSize(Label label, double size) {
        label.setFont(new Font(arcade.getName(), size));
    }

    /**
     * Sets initial size of button.
     * 
     * @param btn
     */
    public static void setInitialSize(Button btn, double size) {
        btn.setFont(new Font(arcade.getName(), size));
    }
    
    /**
     * Binds labels to stage to make them responsive while also preserving custom font.
     * 
     * @param label
     * @param stage
     * @param minSize
     * @param maxSize
     */
    public static void makeLabelResponsive(Label label, Stage stage, double minSize, double maxSize) {
        label.fontProperty().bind(Bindings.createObjectBinding(() -> {
            double fontSize = Math.max(minSize, Math.min(maxSize, stage.getWidth() / 20));
            return new Font(arcade.getName(), fontSize);
        }, stage.widthProperty()));
    }

    /**
     * Binds 
     * 
     * @param view
     * @param rootAnchor
     * @param imagePath
     */
    // public static void makeImageResponsive(ImageView view, Stage stage, AnchorPane rootAnchor, String imagePath) {
    //     Image image = new Image(UtilitesGUI.class.getResourceAsStream(imagePath));
    //     view.setImage(image);
    //     view.setPreserveRatio(true);

    //     view.fitWidthProperty().bind(rootAnchor.widthProperty());
    //     view.fitHeightProperty().bind(rootAnchor.heightProperty());

    //     // view.fitWidthProperty().bind(Bindings.createDoubleBinding(() -> {
    //     //     double widthRatio = stage.getWidth() / rootAnchor.getPrefWidth();
    //     //     return rootAnchor.getPrefWidth() * widthRatio;
    //     // }, stage.widthProperty()));

    //     // view.fitHeightProperty().bind(Bindings.createDoubleBinding(() -> {
    //     // double heightRatio = stage.getHeight() / rootAnchor.getPrefHeight();
    //     // return rootAnchor.getPrefHeight() * heightRatio;
    //     // }, stage.heightProperty()));
    // }
}
