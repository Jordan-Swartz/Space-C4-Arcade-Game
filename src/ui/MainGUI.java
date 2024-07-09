package ui;

import core.*;
import interfaces.C4Constants;
import static interfaces.C4Constants.*;
import ai.*;
import java.util.Random;

import javafx.application.Application;
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
    public void start(Stage stage) {
        Random random = new Random();
        TokenCounter tokenCounter = new TokenCounter();
        GameLogic logic = new GameLogic(tokenCounter);
        Graph graph = new Graph(logic, tokenCounter, random);
        MoveBFS bfs = new MoveBFS(graph, random);
        ComputerLogic computer = new ComputerLogic(logic, bfs);
        ConsoleUI console = new ConsoleUI(logic, computer);
        Test test = new Test(console, logic);

        HBox box = new HBox(10);
        box.setAlignment(Pos.CENTER);

        Button btn1 = new Button("GUI");
        Button btn2 = new Button("Text Console");

        btn1.setPrefWidth(100);
        btn2.setPrefWidth(100);

        HBox.setMargin(btn1, new Insets(10, 10, 10, 10));
        HBox.setMargin(btn2, new Insets(10, 10, 10, 10));
        

        box.getChildren().addAll(btn1, btn2);
        stage.setScene(new Scene(box, 500, 500));
        stage.show();


        //test
        // test.runTest();
        test.runTest2();
    }

}
