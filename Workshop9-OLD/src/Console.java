import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.IndexRange;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * Console which provides a mechanism to lock scrolling. Selecting text and copying it works while scrolling is locked.
 */
public class Console extends BorderPane {

    TextArea textArea = new TextArea();
    ToggleButton scrollLockButton;
    IndexRange range;

    public Console() {

        initComponents();

    }

    private void initComponents() {

        // toolbar
        HBox toolbar = new HBox();
        toolbar.setAlignment(Pos.CENTER_RIGHT);

        // clear
        Button clearButton = new Button("Clear");
        clearButton.setOnAction(e -> {
            textArea.clear();
        });

        // scroll lock
        scrollLockButton = new ToggleButton("Scroll Lock");

        // button positions & layout
        Insets insets = new Insets(5, 5, 5, 5);
        HBox.setMargin(clearButton, insets);
        HBox.setMargin(scrollLockButton, insets);

        toolbar.getChildren().addAll(clearButton,scrollLockButton);

        // component layout
        setCenter(textArea);
        setTop(toolbar);

    }


    public void log(String text) {

        if (scrollLockButton.isSelected()) {
            range = textArea.getSelection();
        }

        textArea.appendText(text + "\n");

        if (scrollLockButton.isSelected()) {
            textArea.selectRange(range.getStart(), range.getEnd());
        }
    }
}