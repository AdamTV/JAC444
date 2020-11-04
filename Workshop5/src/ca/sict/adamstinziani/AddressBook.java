package ca.sict.adamstinziani;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;

// address book - extension of JavaFX.Application class for managing addresses
public class AddressBook extends Application {

    static int fixedLengthForString = 10;
    static RandomAccessFile addressBookFile;
    TextField firstName = new TextField();
    TextField lastName = new TextField();
    TextField city = new TextField();
    ObservableList<String> options =
            FXCollections.observableArrayList(
                    "Select Province",
                    "NL",
                    "PE",
                    "NS",
                    "NB",
                    "QC",
                    "ON",
                    "MB",
                    "SK",
                    "AB",
                    "BC",
                    "YT",
                    "NT",
                    "NU"
            );
    ComboBox<String> province = new ComboBox<>(options);
    TextField postal = new TextField();

    // initialize this class and add default records if none exist
    public AddressBook() throws IOException {
        addressBookFile = new RandomAccessFile("addressBook.txt", "rw");
        if (addressBookFile.length() > 0) return;
        addAddress("Adam", "Stinziani", "Scarboro", "ON", "M1M2E2", false);
        addAddress("Mahboob", "Ali", "Toronto", "NL", "A1A1A1", false);
        addAddress("John", "Smith", "North York", "PE", "B1B1B1", false);
        addAddress("Jane", "Smith", "Barrie", "QC", "Z1Z1Z1", false);
        addAddress("Tony", "Stark", "Ajax", "NS", "C9C9C9", false);
    }

    // adds address to end of address book file
    private static boolean addAddress(String fName, String lName, String city, String province, String postal, boolean isUpdate) {
        try {
            // validate input
            if (province.length() > 2) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Province must be selected from list.");
                alert.showAndWait();
                addressBookFile.seek(addressBookFile.getFilePointer() + 60);
                return false;
            }
            if (postal.length() != 6) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Postal code must be 6 characters in format A1A1A1.");
                alert.showAndWait();
                addressBookFile.seek(addressBookFile.getFilePointer() + 60);
                return false;
            }
            if (Character.isDigit(postal.charAt(0)) || Character.isDigit(postal.charAt(2)) || Character.isDigit(postal.charAt(4)) || Character.isAlphabetic(postal.charAt(3)) || Character.isAlphabetic(postal.charAt(5))) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Invalid format for postal code. Postal code must be in format A1A1A1.");
                alert.showAndWait();
                addressBookFile.seek(addressBookFile.getFilePointer() + 60);
                return false;
            }
            if (!isUpdate) {
                addressBookFile.seek(addressBookFile.length());
            }
            // 1 ADDRESS RECORD OFFSETS 60 BYTES
            String fixed;
            fixed = String.format("%1$" + fixedLengthForString + "s", fName);
            fixed = fixed.substring(0, Math.min(fixed.length(), 10));
            addressBookFile.writeUTF(fixed);
            fixed = String.format("%1$" + fixedLengthForString + "s", lName);
            fixed = fixed.substring(0, Math.min(fixed.length(), 10));
            addressBookFile.writeUTF(fixed);
            fixed = String.format("%1$" + fixedLengthForString + "s", city);
            fixed = fixed.substring(0, Math.min(fixed.length(), 10));
            addressBookFile.writeUTF(fixed);
            fixed = String.format("%1$" + fixedLengthForString + "s", province);
            fixed = fixed.substring(0, Math.min(fixed.length(), 10));
            addressBookFile.writeUTF(fixed);
            fixed = String.format("%1$" + fixedLengthForString + "s", postal);
            fixed = fixed.substring(0, Math.min(fixed.length(), 10));
            addressBookFile.writeUTF(fixed);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    // remove leading whitespace from string
    static String removeLeadingSpaces(String s) {
        StringBuilder sb = new StringBuilder(s);
        while (sb.length() > 0 && sb.charAt(0) == ' ') {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }

    // initiate the GUI
    public static void initiate(String[] args) {
        launch(args);
    }

    // overwrites currently displayed address with any overwritten values
    boolean updateAddress() {
        boolean result = false;
        try {
            addressBookFile.seek(addressBookFile.getFilePointer() - 60);
            result = addAddress(firstName.getText(), lastName.getText(), city.getText(), province.getValue(), postal.getText(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    // display address from any position in file
    private void showAddress(long position) {
        try {
            addressBookFile.seek(position);
            String fixed = addressBookFile.readUTF();
            fixed = removeLeadingSpaces(fixed);
            firstName.setText(fixed);
            fixed = addressBookFile.readUTF();
            fixed = removeLeadingSpaces(fixed);
            lastName.setText(fixed);
            fixed = addressBookFile.readUTF();
            fixed = removeLeadingSpaces(fixed);
            city.setText(fixed);
            fixed = addressBookFile.readUTF();
            fixed = removeLeadingSpaces(fixed);
            province.setValue(fixed);
            fixed = addressBookFile.readUTF();
            fixed = removeLeadingSpaces(fixed);
            postal.setText(fixed);
        } catch (EOFException eof) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("End of Address Book reached.");
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Beginning of Address Book reached.");
            alert.showAndWait();
        }

    }

    // custom labels for formatting
    private Label CustomLabel(String name) {
        Label label = new Label(name);
        label.setMaxWidth(100);
        label.setAlignment(Pos.BASELINE_RIGHT);
        return label;
    }

    // custom button for formatting
    private Button CustomButton(String name) {
        Button button = new Button(name);
        button.setMaxWidth(75);
        return button;
    }

    // override of JavaFX.Application start method
    @Override
    public void start(Stage primaryStage) {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(5, 5, 5, 5));
        pane.setHgap(5);
        pane.setVgap(5);

        Button addButton = CustomButton("Add");
        Button firstButton = CustomButton("First");
        Button nextButton = CustomButton("Next");
        Button previousButton = CustomButton("Previous");
        Button lastButton = CustomButton("Last");
        Button updateButton = CustomButton("Update");

        pane.add(CustomLabel("First Name:"), 0, 1);
        pane.add(firstName, 1, 1);

        pane.add(CustomLabel("Last Name:"), 0, 3);
        pane.add(lastName, 1, 3);

        pane.add(CustomLabel("City:"), 0, 5);
        pane.add(city, 1, 5);

        province.getSelectionModel().selectFirst();
        pane.add(CustomLabel("Province:"), 2, 5);
        pane.add(province, 3, 5);

        pane.add(CustomLabel("Postal Code:"), 4, 5);
        pane.add(postal, 5, 5);

        pane.add(addButton, 0, 10);
        pane.add(firstButton, 1, 10);
        pane.add(nextButton, 2, 10);
        pane.add(previousButton, 3, 10);
        pane.add(lastButton, 4, 10);
        pane.add(updateButton, 5, 10);

        for (int i = 0; i < pane.getColumnCount(); i++) {
            pane.getColumnConstraints().add(new ColumnConstraints(100)); // all columns are 100 pixels wide
        }

        // EVENT HANDLERS
        addButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> {
                    if (addAddress(firstName.getText(), lastName.getText(), city.getText(), province.getValue(), postal.getText(), false)) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Address added to Address Book.");
                        alert.showAndWait();
                    }

                });
        firstButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> showAddress(0));
        nextButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> {
                    try {
                        showAddress(addressBookFile.getFilePointer());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        previousButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> {
                    try {
                        showAddress(addressBookFile.getFilePointer() - 120);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        lastButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> {
                    try {
                        showAddress(addressBookFile.length() - 60);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        updateButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> {
                    if (updateAddress()) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Address Book updated.");
                        alert.showAndWait();
                    }
                });

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Address Book");
        primaryStage.setScene(scene);
        primaryStage.show();
        showAddress(0);
    }
}
