package js_game.ui;

/**
 * The MainGUI class serves as the entry point for the graphical user interface (GUI)
 * of the Connect Four game.
 * This class initializes all necessary game components, loads the FXML layout for the
 * start menu, and sets up the primary JavaFX Stage.
 *
 * @author Jordan Swartz
 * @version 1.0
 */

import js_game.controllers.StartMenuController;

import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.*;
import javafx.stage.*;
import js_game.ai.ComputerLogic;
import js_game.ai.Graph;
import js_game.ai.MoveBFS;
import js_game.core.GameLogic;
import js_game.core.TokenCounter;
//import test.Test;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import js_game.interfaces.C4Constants;

public class MainGUI extends Application implements C4Constants {

     /**
	 * Entry point into the program.
	 * 
	 * @param args: used to launch GUI
	 */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the JavaFX application and initializes the game environment.
     * <p>
     * This method performs the following:
     * <ul>
     * <li>Creates core game objects, including GameLogic, TokenCounter, and AI components.</li>
     * <li>Loads the FXML file for the start menu interface.</li>
     * <li>Sets up the JavaFX Stage and scene.</li>
     * <li>Passes necessary objects to the start menu controller.</li>
     * </ul>
     * </p>
     *
     * @param stage The primary JavaFX Stage that displays the GUI.
     * @throws IOException If there is an issue loading the FXML file.
     */
    @Override
    public void start(Stage stage) throws IOException {
        //object instantiation
        Random random = new Random();
        TokenCounter tokenCounter = new TokenCounter();
        GameLogic logic = new GameLogic(tokenCounter);
        Graph graph = new Graph(logic, tokenCounter, random);
        MoveBFS bfs = new MoveBFS(graph, random);
        ComputerLogic computer = new ComputerLogic(logic, bfs);
        ConsoleUI console = new ConsoleUI(logic, computer);
        //Test test = new Test(console, logic);

        String fxmlFile = "/fxml/C4StartMenu.fxml";
        URL fxmlLocation = getClass().getResource(fxmlFile);

        if (fxmlLocation == null) {
            throw new RuntimeException("FXML file not found: " + fxmlFile);
        }

        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Parent root = loader.load();

        StartMenuController controller = loader.getController();
        controller.setStage(stage);
        controller.setConsole(console);
        controller.setLogic(logic);
        controller.setComputer(computer);

        Scene scene = new Scene(root);
        stage.setTitle("Test Application");
        stage.setScene(scene);

        //set the minimum width and height for the stage
        stage.setMinWidth(600);
        stage.setMinHeight(400);
 
        //set an initial size for the stage
        stage.setWidth(1024);
        stage.setHeight(768);

        stage.show();
    }

}
