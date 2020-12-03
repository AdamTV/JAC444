import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerMain extends Application {

    public static Console console = new Console();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Server");
        primaryStage.setScene(new Scene(console, 300, 275));
        //console.log("");
        primaryStage.setOnCloseRequest(windowEvent -> Platform.exit());
        primaryStage.show();
        // let GUI load before showing first msg
        Thread.sleep(500);
        new Thread(()->EchoMultiThreadServer.main(null)).start();
    }
}
