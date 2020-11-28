/*
 * Workshop # 7
 * Course: JAC444 - Fall 2020
 * Last Name: Stinziani
 * First Name: Adam
 * ID: 124521188
 * Section: NDD
 * This assignment represents my own work in accordance with Seneca Academic Policy.
 * Signature: Adam Stinziani
 * Date: 2020-11-14
 */

package ca.sict.adamstinziani;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class CountryMapper extends Application {

    static Map<String, String> countryMap = new HashMap<>();
    // defined in class for reuse
    Stage primaryStage;

    // initiate the GUI
    public static void initiate(String[] args) {
        countryMap.put("Canada", "Ottawa");
        countryMap.put("China", "Beijing");
        countryMap.put("Colombia", "Bogotá");
        countryMap.put("Brazil", "Brasília");
        countryMap.put("Argentina", "Buenos Aires");
        countryMap.put("Denmark", "Copenhagen");
        countryMap.put("Syria", "Damascus");
        countryMap.put("Bangladesh", "Dhaka");
        countryMap.put("India", "New Delhi");
        countryMap.put("Ireland", "Dublin");
        countryMap.put("Cuba", "Havana");
        countryMap.put("Pakistan", "Islamabad");
        countryMap.put("Indonesia", "Jakarta");
        countryMap.put("Palestine", "Jerusalem");
        countryMap.put("Jamaica", "Kingston");
        countryMap.put("Kuwait", "Kuwait City");
        countryMap.put("Ukraine", "Kyiv");
        countryMap.put("Peru", "Lima");
        countryMap.put("Portugal", "Ottawa");
        countryMap.put("Philippines", "Manila");
        countryMap.put("Panama", "Panama City");
        countryMap.put("North Korea", "Pyongyang");
        countryMap.put("France", "Paris");
        countryMap.put("Japan", "Tokyo");
        countryMap.put("United States", "Washington, D.C.");
        launch(args);
    }

    // override JavaFX.Application.start method
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(50, 50, 50, 50));
        pane.setHgap(10);
        pane.setVgap(20);
        Label qLabel = new Label("Enter a country name:");
        TextField countryName = new TextField();
        Button submitButt = new Button("Submit Query");
        Button exitButt = new Button("Exit");
        submitButt.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> submitQuery(countryName.getText()));
        exitButt.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> primaryStage.close());
        pane.add(qLabel, 0, 0);
        pane.add(countryName, 1, 0);
        pane.add(submitButt, 0, 2);
        pane.add(exitButt, 1, 2);
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Search Name Ranking Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // submit query to search country map for its capital
    private void submitQuery(String countryName) {
        // Validate input
        if (!countryMap.containsKey(countryName)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText(String.format("Country name not found in country map. Available options are: %s", countryMap.keySet()));
            alert.showAndWait();
            return;
        }

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(50, 50, 50, 50));
        pane.setHgap(10);
        pane.setVgap(20);

        Label resultLabel = new Label(String.format("The capital of %s is %s",countryName ,countryMap.get(countryName)));
        Label continueLabel = new Label("Do you want to search another country?");
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");
        yesButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> start(primaryStage));
        noButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> primaryStage.close());

        pane.add(resultLabel, 0, 0);
        pane.add(continueLabel, 0, 1);
        pane.add(yesButton, 0, 2);
        pane.add(noButton, 1, 2);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
    }
}