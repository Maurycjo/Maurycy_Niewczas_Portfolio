package pwr.edu.pl.window;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;

public class ButtonController {

    private Stage stage;

    @FXML
    private Button deserializeButton;

    @FXML
    private Button loadDataButton;

    @FXML
    private Button serializeButton;

    @FXML
    void deserialize(ActionEvent event) {

        System.out.println("1");
    }

    @FXML
    void loadData(ActionEvent event) throws IOException {

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz plik");
        String userHomeDir = System.getProperty("user.home");
        fileChooser.setInitialDirectory(new File(userHomeDir, "Desktop"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        event.consume();

    }

    @FXML
    void serialize(ActionEvent event) {
        System.out.println("3");
    }

}
