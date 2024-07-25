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
    private static Font font1;
    private static Font font2;
    private static Font font3;

    /**
     * Static block to load fonts when class is first accessed.
     */
    static {
        font1 = Font.loadFont(UtilitesGUI.class.getResourceAsStream("/resources/fonts/AerologicaRegular-K7day.ttf"), 20);
        font2 = Font.loadFont(UtilitesGUI.class.getResourceAsStream("/resources/fonts/PixelGamerPersonalUse-rg61L.otf"), 20); 
        font3 = Font.loadFont(UtilitesGUI.class.getResourceAsStream("/resources/fonts/TechnoRaceItalic-eZRWe.otf"), 20);

        if (font1 == null) {
            throw new RuntimeException("Custom font 'AerologicaRegular-K7day.ttf' not loaded!");
        } else if (font2 == null) {
            throw new RuntimeException("Custom font 'AerologicaRegular-K7day.ttf' not loaded!");
        } else if (font3 == null) {
            throw new RuntimeException("Custom font 'AerologicaRegular-K7day.ttf' not loaded!");
        }
    }

    

    /**
     * 
     * @param num
     * @return
     */
    public static Font getFont(int num) {
        if (num == 1) {
            return font1;
        } else if (num == 2) {
            return font2;
        } else if (num == 3) {
            return font3;
        } else {
            return null;
        }
    }

    /**
     * Sets initial size of label.
     * 
     * @param label
     */
    public static void setInitialSize(Label label, double size, int fontNum) {
        Font font = getFont(fontNum);
        label.setFont(new Font(font.getName(), size));
    }

    /**
     * Sets initial size of button.
     * 
     * @param btn
     */
    public static void setInitialSize(Button btn, double size, int fontNum) {
        Font font = getFont(fontNum);
        btn.setFont(new Font(font.getName(), size));
    }
    
    /**
     * Binds labels to stage to make them responsive while also preserving custom font.
     * 
     * @param label
     * @param stage
     * @param minSize
     * @param maxSize
     */
    public static void makeLabelResponsive(Label label, Stage stage, double minSize, double maxSize, int fontNum) {
        Font font = getFont(fontNum);
        label.fontProperty().bind(Bindings.createObjectBinding(() -> {
            double fontSize = Math.max(minSize, Math.min(maxSize, stage.getWidth() / 5));
            return new Font(font.getName(), fontSize);
        }, stage.widthProperty()));
    }

    /**
     * Binds buttons to stage to make them responsive while also preserving custom font.
     * 
     * @param button
     * @param stage
     * @param minSize
     * @param maxSize
     */
    public static void makeButtonResponsive(Button button, Stage stage, double minSize, double maxSize, int fontNum) {
        Font font = getFont(fontNum);
        button.fontProperty().bind(Bindings.createObjectBinding(() -> {
            double fontSize = Math.max(minSize, Math.min(maxSize, stage.getWidth() / 30));
            return new Font(font.getName(), fontSize);
        }, stage.widthProperty()));

        button.prefWidthProperty().bind(stage.widthProperty().divide(6)); // Adjust width ratio as needed
        button.prefHeightProperty().bind(stage.heightProperty().divide(10)); // Adjust height ratio as needed
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
