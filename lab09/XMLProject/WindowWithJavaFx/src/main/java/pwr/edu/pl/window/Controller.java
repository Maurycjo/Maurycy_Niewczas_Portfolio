package pwr.edu.pl.window;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pwr.edu.pl.parser.DomParser;
import pwr.edu.pl.parser.JAXPParser;
import pwr.edu.pl.parser.XSLTParser;
import pwr.edu.pl.parser.XmlParser;
import javafx.scene.control.Tab;
import javafx.scene.web.WebView;
import java.io.File;
import java.io.IOException;

public class Controller {

    private Stage stage;
    private XmlParser xmlParser = new DomParser();
    File loadedFile;

    private Node currentTextArea;

    public void setXmlParser(XmlParser xmlParser){
        this.xmlParser = xmlParser;
    }

    //buttons
    @FXML
    private Button deserializeButton;

    @FXML
    private Button loadDataButton;

    @FXML
    private Button serializeButton;

    //tabs
    @FXML
    private TabPane myTabPane;
    @FXML
    private Tab jaxbTab;
    @FXML
    private Tab domTab;
    @FXML
    private Tab saxTab;
    @FXML
    private Tab xsltTab;

    //text areas

    @FXML
    private TextArea jaxbTextArea;
    @FXML
    private TextArea domTextArea;
    @FXML
    private TextArea saxTextArea;
    @FXML
    private WebView xsltWebView;

    @FXML
    void deserialize(ActionEvent event) {
        xmlParser.load(loadedFile);
        xmlParser.deserialize();
        //outputTextArea.setText(xmlParser.getOutput());
    }

    @FXML
    void loadData(ActionEvent event) throws IOException {

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz plik");
        String userHomeDir = System.getProperty("user.home");
        fileChooser.setInitialDirectory(new File(userHomeDir+"/Desktop/xml"));
        loadedFile = fileChooser.showOpenDialog(stage);
        event.consume();


    }

    @FXML
    void serialize(ActionEvent event) {

        xmlParser.serialize();
        jaxbTextArea.setText(xmlParser.getOutput());
    }


    @FXML
    void changedToJaxb(ActionEvent event) {


    }
    @FXML
    void changedToDom(ActionEvent event) {

    }

    @FXML
    void changedToSax(ActionEvent event) {

    }

    @FXML
    void changedToXslt(ActionEvent event) {

    }



}
