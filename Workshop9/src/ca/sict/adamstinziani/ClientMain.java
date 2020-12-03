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
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

// javafx GUI for Client class of this workshop
public class ClientMain extends Application {

    public static GridPane gridPane = new GridPane();
    public static ScrollPane scrollPane = new ScrollPane();
    public static TextField textField = new TextField();
    public static Button button = new Button("Send");
    public static Stage primaryStage;
    public static Scene scene;
    public static Label label = new Label();
    static int i = 0;
    static String name;

    // main entry point for this module
    public static void main(String[] args) {
        launch(args);
    }

    // sets the client's GUI with data received in parameter
    public static void setStage(String s) {
        gridPane = new GridPane();
        primaryStage.setTitle("Client");
        label = new Label(label.getText() == null ? s : label.getText() + "\n" + s);
        scrollPane = new ScrollPane(label);
        scrollPane.setMinWidth(1000);
        textField.setMinWidth(400);
        gridPane.setMaxHeight(500);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(50, 50, 50, 50));
        gridPane.setHgap(10);
        gridPane.setVgap(20);
        gridPane.add(new Label("Messages"), 0, 0);
        gridPane.add(scrollPane, 0, 1);
        gridPane.add(textField, 0, 2);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> buttonClicked());
        gridPane.add(button, 1, 2);
        scene = new Scene(gridPane);
        primaryStage.setScene(scene);
    }

    // function for handling the click button event when sending messages
    private static void buttonClicked() {
        if (textField.getText().equals("exit")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Exited chat room.");
            primaryStage.close();
            alert.showAndWait();
            Platform.exit();
        }
        if (i == 0) {
            appendScrollPane("Your name: " + textField.getText());
            Client.name = textField.getText();
            name = textField.getText();
            i++;
        } else {
            try {
                appendScrollPane(name + ": " + textField.getText());
                Client.echoString = textField.getText();
                Client.sendMessageToServer();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Client.echoString = null;
        }
        textField.clear();
    }

    // thread-safe update to client's GUI
    public static void appendScrollPane(String text) {
        Platform.runLater(() -> setStage(text));
    }

    // override of javafx.Application.start
    @Override
    public void start(Stage primaryStageArg) {
        primaryStage = primaryStageArg;
        primaryStage.setOnCloseRequest(windowEvent -> Platform.exit());
        setStage("");
        primaryStage.show();
        var t = new Thread(() -> Client.main(null));
        t.setDaemon(true);
        t.start();
    }
}
