package ca.sict.adamstinziani;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Workshop5 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(5, 5, 5, 5));
        pane.setHgap(5);
        pane.setVgap(5);

        Button addButton = new Button("Add");
        Button firstButton = new Button("First");
        Button nextButton = new Button("Next");
        Button previousButton = new Button("Previous");
        Button lastButton = new Button("Last");
        Button updateButton = new Button("Update");

        pane.add(new Label("First Name:"), 0, 1);
        pane.add(new TextField(), 1, 1);

        pane.add(new Label("Last Name:"), 0, 3);
        pane.add(new TextField(), 1, 3);

        pane.add(new Label("City:"), 0, 5);
        pane.add(new TextField(), 1, 5);

        pane.add(new Label("Province:"), 2, 5);
        pane.add(new ComboBox<String>(), 3, 5);

        pane.add(new Label("Postal Code:"), 4, 5);
        pane.add(new TextField(), 5, 5);

        pane.add(addButton, 0, 50);
        pane.add(firstButton, 1, 50);
        pane.add(nextButton, 2, 50);
        pane.add(previousButton, 3, 50);
        pane.add(lastButton, 4, 50);
        pane.add(updateButton, 5, 50);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Address Book");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
