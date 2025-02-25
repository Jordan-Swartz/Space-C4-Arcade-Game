package js_game.ui;

/**
 * The UtilitesGUI class provides utility methods for managing fonts,
 * responsive UI elements, and initial styling in a JavaFX-based Connect Four GUI.
 *
 * <p>It includes methods for:
 * <ul>
 * <li>Loading and retrieving custom fonts.</li>
 * <li>Setting initial font sizes for labels and buttons.</li>
 * <li>Making UI elements responsive to stage resizing.</li>
 * </ul>
 * </p>
 *
 * @author Jordan Swartz
 * @version 1.0
 */

import javax.management.RuntimeErrorException;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UtilitesGUI {
    private static Font font1;
    private static Font font2;
    private static Font font3;

    /**
     * Static block to load fonts when class is first accessed.
     */
    static {
        font1 = Font.loadFont(UtilitesGUI.class.getResourceAsStream("/fonts/AerologicaRegular-K7day.ttf"), 20);
        font2 = Font.loadFont(UtilitesGUI.class.getResourceAsStream("/fonts/PixelGamerPersonalUse-rg61L.otf"), 20);
        font3 = Font.loadFont(UtilitesGUI.class.getResourceAsStream("/fonts/TechnoRaceItalic-eZRWe.otf"), 20);

        if (font1 == null) {
            throw new RuntimeException("Custom font 'AerologicaRegular-K7day.ttf' not loaded!");
        } else if (font2 == null) {
            throw new RuntimeException("Custom font 'AerologicaRegular-K7day.ttf' not loaded!");
        } else if (font3 == null) {
            throw new RuntimeException("Custom font 'AerologicaRegular-K7day.ttf' not loaded!");
        }
    }



    /**
     * Retrieves a specific custom font based on the provided index.
     *
     * @param num The font index (1, 2, or 3).
     * @return The corresponding Font object, or null if the index is invalid.
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
     * Sets the initial font size for a label.
     *
     * @param label The Label whose font size will be set.
     * @param size The desired font size.
     * @param fontNum The index of the custom font to apply.
     */
    public static void setInitialSize(Label label, double size, int fontNum) {
        Font font = getFont(fontNum);
        label.setFont(new Font(font.getName(), size));
    }

    /**
     * Sets the initial font size for a button.
     *
     * @param btn The Button whose font size will be set.
     * @param size The desired font size.
     * @param fontNum The index of the custom font to apply.
     */
    public static void setInitialSize(Button btn, double size, int fontNum) {
        Font font = getFont(fontNum);
        btn.setFont(new Font(font.getName(), size));
    }

    /**
     * Binds a label's font size to the window width, making it responsive.
     * <p>
     * The font size will stay within the specified range, adjusting dynamically
     * as the window is resized.
     * </p>
     *
     * @param label The {@code Label} to be made responsive.
     * @param stage The JavaFX {@code Stage} whose width controls the font size.
     * @param minSize The minimum allowed font size.
     * @param maxSize The maximum allowed font size.
     * @param fontNum The index of the custom font to apply.
     */
    public static void makeLabelResponsive(Label label, Stage stage, double minSize, double maxSize, int fontNum) {
        Font font = getFont(fontNum);
        label.fontProperty().bind(Bindings.createObjectBinding(() -> {
            double fontSize = Math.max(minSize, Math.min(maxSize, stage.getWidth() / 5));
            return new Font(font.getName(), fontSize);
        }, stage.widthProperty()));
    }

    /**
     * Binds a button's font size and dimensions to the window size, making it responsive.
     * <p>
     * The font size adjusts dynamically while preserving a custom font. Additionally,
     * the button's width and height are bound to a fraction of the window size.
     * </p>
     *
     * @param button The {@code Button} to be made responsive.
     * @param stage The JavaFX {@code Stage} whose size affects the button.
     * @param minSize The minimum allowed font size.
     * @param maxSize The maximum allowed font size.
     * @param fontNum The index of the custom font to apply.
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

}
