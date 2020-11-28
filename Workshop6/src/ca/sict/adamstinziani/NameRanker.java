/*
 * Workshop # 6
 * Course: JAC444 - Fall 2020
 * Last Name: Stinziani
 * First Name: Adam
 * ID: 124521188
 * Section: NDD
 * This assignment represents my own work in accordance with Seneca Academic Policy.
 * Signature: Adam Stinziani
 * Date: 2020-11-10
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

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;

// contains logic to find rank of name - specifications according to workshop 6
public class NameRanker extends Application {

    // defined in class for reuse
    Stage primaryStage;

    // initiate the GUI
    public static void initiate(String[] args) {
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

        Label yearLabel = new Label("Enter the Year:");
        Label genderLabel = new Label("Enter the Gender:");
        Label nameLabel = new Label("Enter the Name:");

        TextField year = new TextField();
        TextField gender = new TextField();
        TextField name = new TextField();


        Button submitButt = new Button("Submit Query");
        Button exitButt = new Button("Exit");
        submitButt.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> submitQuery(year.getText(), gender.getText(), name.getText()));
        exitButt.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> primaryStage.close());

        pane.add(yearLabel, 0, 0);
        pane.add(year, 1, 0);
        pane.add(genderLabel, 0, 1);
        pane.add(gender, 1, 1);
        pane.add(nameLabel, 0, 2);
        pane.add(name, 1, 2);

        pane.add(submitButt, 0, 4);
        pane.add(exitButt, 1, 4);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Search Name Ranking Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // submit query to search for rank of name based on year, gender, name
    private void submitQuery(String year, String gender, String name) {
        // Validate input
        int parsedYear = Integer.parseInt(year);
        if (parsedYear < 2009 || parsedYear > 2018) {
            // Invalid year
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Invalid year. Year must be in between 2009 and 2018.");
            alert.showAndWait();
            return;
        }
        if (!gender.toUpperCase().equals("M") && !gender.toUpperCase().equals("F")) {
            // Invalid gender
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Invalid gender. Gender must be M or F.");
            alert.showAndWait();
            return;
        }
        NameRankings nameRankings = new NameRankings(parsedYear);

        String genderFull = null;
        int indexOfResult = -1;
        Number count = 0;

        if (gender.toUpperCase().equals("M")) {
            genderFull = "Boy";
            if (nameRankings.boyNames.contains(name.toUpperCase())) {
                indexOfResult = nameRankings.boyNames.indexOf(name.toUpperCase());
                count = nameRankings.boyNameCounts.get(indexOfResult);
            }
        }
        if (gender.toUpperCase().equals("F")) {
            genderFull = "Girl";
            if (nameRankings.girlNames.contains(name.toUpperCase())) {
                indexOfResult = nameRankings.girlNames.indexOf(name.toUpperCase());
                count = nameRankings.girlNameCounts.get(indexOfResult);
            }
        }

        String result;

        if (indexOfResult == -1) {
            result = String.format("%s name %s is NOT ranked in year %s", genderFull, name, year);
        } else {
            result = String.format("%s name %s is ranked #%s in year %s with %s %s named %s.", genderFull, name, nameRankings.ranks.get(indexOfResult), year, count, genderFull.toLowerCase() + "s", name);
        }

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(50, 50, 50, 50));
        pane.setHgap(10);
        pane.setVgap(20);

        Label resultLabel = new Label(result);
        Label continueLabel = new Label("Do you want to search for another name?");
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

    // contains logic to parse and store name rankings for a particular year
    static class NameRankings {
        public int year;
        public ArrayList<Number> ranks = new ArrayList<>();
        public ArrayList<String> boyNames = new ArrayList<>();
        public ArrayList<String> girlNames = new ArrayList<>();
        public ArrayList<Number> boyNameCounts = new ArrayList<>();
        public ArrayList<Number> girlNameCounts = new ArrayList<>();

        // parses baby name file with appropriate year from query
        public NameRankings(int year) {
            this.year = year;
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile("BabynamesFiles/babynamesranking" + year + ".txt", "r");
                while (true) {
                    try {
                        String line = randomAccessFile.readLine();
                        if (line == null) break;
                        String[] info = line.split("\t");
                        ranks.add(NumberFormat.getNumberInstance(java.util.Locale.US).parse(info[0]));
                        boyNames.add(info[1].substring(0, info[1].indexOf(' ') == -1 ? info[1].length() : info[1].indexOf(' ')).toUpperCase());
                        boyNameCounts.add(NumberFormat.getNumberInstance(java.util.Locale.US).parse(info[2]));
                        girlNames.add(info[3].substring(0, info[3].indexOf(' ') == -1 ? info[3].length() : info[3].indexOf(' ')).toUpperCase());
                        girlNameCounts.add(NumberFormat.getNumberInstance(java.util.Locale.US).parse(info[4]));
                    } catch (EOFException eofException) {
                        System.out.println("End of file reached.");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
