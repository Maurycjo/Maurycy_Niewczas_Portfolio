package pwr.edu.pl.window;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        BorderPane mainPane = FXMLLoader.load(getClass().getResource("/MainView.fxml"));
        Scene scene = new Scene(mainPane);
        stage.setScene(scene);
        stage.setTitle("Aplikacja pozwalająca na przetwarzanie dokumentów XML");
        stage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
