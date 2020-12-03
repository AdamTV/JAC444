/*
 * Workshop # 9
 * Course: JAC444 - Fall 2020
 * Last Name: Stinziani
 * First Name: Adam
 * ID: 124521188
 * Section: NDD
 * This assignment represents my own work in accordance with Seneca Academic Policy.
 * Signature: Adam Stinziani
 * Date: 2020-12-01
 */
package ca.sict.adamstinziani;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

// javafx GUI for Server class of this workshop
public class ServerMain extends Application {

    public static ScrollPane scrollPane;
    public static Stage primaryStage;
    public static Label label = new Label();

    // main entry point for this module
    public static void main(String[] args) {
        launch(args);
    }

    // thread-safe update to the server GUI
    public static void appendScrollPane(String text) {
        Platform.runLater(() -> setStage(text));
    }

    // sets server GUI scroll pane with corresponding text
    private static void setStage(String text) {
        label = new Label(label.getText() == null ? text : label.getText() + "\n" + text);
        scrollPane = new ScrollPane(label);
        scrollPane.setPadding(new Insets(0,50,0,50));
        primaryStage.setScene(new Scene(scrollPane, 1000, 275));
    }

    // override of javafx.Application.start
    @Override
    public void start(Stage primaryStageArg) {
        primaryStage = primaryStageArg;
        primaryStage.setTitle("Server");
        primaryStage.setOnCloseRequest(windowEvent -> Platform.exit());
        setStage("");
        primaryStage.show();
        Thread t;
        t = new Thread(() -> MultiThreadServer.main(null));
        t.setDaemon(true);
        t.start();

    }
}
