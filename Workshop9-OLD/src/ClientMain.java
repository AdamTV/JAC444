import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class ClientMain extends Application {

    public static FlowPane flowPane = new FlowPane();
    public static ScrollPane scrollPane = new ScrollPane();
    public static TextField textField = new TextField();
    public static Button button = new Button("Send");
    public static Stage primaryStage;
    public static Scene scene;
    public static Label label = new Label();

    static int i = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStageArg) {
        primaryStage = primaryStageArg;
        setStage("");
        primaryStage.show();
        new Thread(()->EchoMultiThreadClient.main(null)).start();
    }

    public static void setStage(String s) {
        flowPane = new FlowPane();
        primaryStage.setTitle("Client");
        label = new Label(label.getText() == null ? s : label.getText() + "\n" + s);
        scrollPane = new ScrollPane(label);
        scrollPane.setMinWidth(500);
        textField.setMinWidth(400);
        flowPane.setAlignment(Pos.CENTER);
        flowPane.setPadding(new Insets(50, 50, 50, 50));
        flowPane.setHgap(10);
        flowPane.setVgap(20);
        flowPane.getChildren().add(new Label("Messages"));
        flowPane.getChildren().add(scrollPane);
        flowPane.getChildren().add(textField);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> buttonClicked());
        flowPane.getChildren().add(button);
        scene = new Scene(flowPane);
        primaryStage.setOnCloseRequest(windowEvent -> Platform.exit());
        primaryStage.setScene(scene);
    }

    private static void buttonClicked() {
        appendScrollPane("You said: " + textField.getText());
    if (i == 0){
        EchoMultiThreadClient.name = textField.getText();
        i++;
    }else{
        EchoMultiThreadClient.echoString = textField.getText();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        EchoMultiThreadClient.echoString = null;
    }
    textField.clear();
    }

    public static void appendScrollPane(String text){
        Platform.runLater(() -> setStage(text));
    }
}
