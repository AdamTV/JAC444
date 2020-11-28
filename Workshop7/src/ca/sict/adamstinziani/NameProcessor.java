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
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// contains logic to find duplicate names in name rank files - specifications according to workshop 7
public class NameProcessor extends Application {

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
        Label qLabel = new Label("Enter a file name from baby name rankings:");
        TextField fileName = new TextField();
        Button submitButt = new Button("Submit Query");
        Button exitButt = new Button("Exit");
        submitButt.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> submitQuery(fileName.getText()));
        exitButt.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> primaryStage.close());
        pane.add(qLabel, 0, 0);
        pane.add(fileName, 1, 0);
        pane.add(submitButt, 0, 2);
        pane.add(exitButt, 1, 2);
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Search Name Ranking Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // submit query to search for rank of name based on year, gender, name
    private void submitQuery(String fileName) {
        // Validate input
        boolean found = false;
        for (int i = 2009; i < 2019; i++) {
            if (fileName.toLowerCase().equals("babynamesranking" + i + ".txt")) {
                found = true;
                break;
            }
        }
        if (!found) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Invalid file name. Year must be in between 2009 and 2018 and response must specify file name.");
            alert.showAndWait();
            return;
        }

        NameRankings rankings = new NameRankings(fileName);

        // this is my favourite line of code in this workshop :D
        List<String> commonNames = IntStream.range(0, rankings.boyNames.size()).filter(i -> IntStream.range(
                0, rankings.girlNames.size()).anyMatch(j -> rankings.boyNames.get(i).toLowerCase().equals(
                rankings.girlNames.get(j).toLowerCase()))).mapToObj(i -> rankings.boyNames.get(i))
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s names used for both genders\nThey are: ", commonNames.size()));

        Consumer<List<String>> appendList = (List<String> list) -> {
            IntStream.range(0, list.size()).forEach(i -> {
                if (i % 25 == 0) {
                    sb.append("\n");
                }
                sb.append(list.get(i)).append(", ");
            });
            sb.delete(sb.length() - 2, sb.length());
            sb.append("\n\n");
        };

        appendList.accept(commonNames);

        // part 2
        Collections.sort(rankings.boyNames);
        Collections.sort(rankings.girlNames);

        Consumer<List<String>> removeDuplicates = (List<String> list) -> {
            for (String commonName : commonNames) {
                list.removeIf(s -> commonName.toLowerCase().equals(s.toLowerCase()));
            }
        };

        removeDuplicates.accept(rankings.boyNames);
        removeDuplicates.accept(rankings.girlNames);
        sb.append("Sorted boy names with duplicates removed:");
        appendList.accept(rankings.boyNames);
        sb.append("Sorted girl names with duplicated removed:");
        appendList.accept(rankings.girlNames);

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(50, 50, 50, 50));
        pane.setHgap(10);
        pane.setVgap(20);

        Label resultLabel = new Label(sb.toString());
        ScrollPane resultPane = new ScrollPane(resultLabel);
        Label continueLabel = new Label("Do you want to search another file?");
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");
        yesButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> start(primaryStage));
        noButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> primaryStage.close());

        pane.add(resultPane, 0, 0);
        pane.add(continueLabel, 0, 1);
        pane.add(yesButton, 0, 2);
        pane.add(noButton, 1, 2);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
    }

    // contains logic to parse and store name rankings for a particular year
    static class NameRankings {
        public List<Number> ranks = new ArrayList<>();
        public ArrayList<String> boyNames = new ArrayList<>();
        public ArrayList<String> girlNames = new ArrayList<>();
        public ArrayList<Number> boyNameCounts = new ArrayList<>();
        public ArrayList<Number> girlNameCounts = new ArrayList<>();

        // parses baby name file with appropriate year from query
        public NameRankings(String fileName) {
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile("BabynamesFiles/" + fileName, "r");
                while (true) try {
                    String line = randomAccessFile.readLine();
                    if (line == null) break;
                    String[] info = line.split("\t");
                    ranks.add(NumberFormat.getNumberInstance(java.util.Locale.US).parse(info[0]));
                    boyNames.add(info[1].substring(0, info[1].indexOf(' ') == -1 ? info[1].length() : info[1].indexOf(' ')));
                    boyNameCounts.add(NumberFormat.getNumberInstance(java.util.Locale.US).parse(info[2]));
                    girlNames.add(info[3].substring(0, info[3].indexOf(' ') == -1 ? info[3].length() : info[3].indexOf(' ')));
                    girlNameCounts.add(NumberFormat.getNumberInstance(java.util.Locale.US).parse(info[4]));
                } catch (EOFException eofException) {
                    System.out.println("End of file reached.");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
