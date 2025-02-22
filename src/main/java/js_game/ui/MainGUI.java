package js_game.ui;

import core.*;
import interfaces.C4Constants;
import static interfaces.C4Constants.*;
import ai.*;
import controllers.StartMenuController;

import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.*;
import javafx.stage.*;
import test.Test;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class MainGUI extends Application implements C4Constants{

     /**
	 * Entry point into the program.
	 * 
	 * @param args: used to launch GUI
	 */
    public static void main(String[] args) {
        launch(args);
    }

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
        Test test = new Test(console, logic);

        //
        String fxmlFile = "/resources/fxml/C4StartMenu.fxml";
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

         // Set the minimum width and height for the stage
        stage.setMinWidth(600);
        stage.setMinHeight(400);
 
         // Set an initial size for the stage
        stage.setWidth(1024);
        stage.setHeight(768);

        stage.show();
        
        // test.runTest2();
    }

}
