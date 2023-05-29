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
import pwr.edu.pl.parser.JAXBParser;
import pwr.edu.pl.parser.JAXPParser;
import pwr.edu.pl.parser.XSLTParser;
import pwr.edu.pl.parser.XmlParser;
import javafx.scene.control.Tab;

import java.io.File;
import java.io.IOException;

public class Controller {

    private Stage stage;
    private XmlParser xmlParser = new JAXBParser();


    public void setXmlParser(XmlParser xmlParser){
        this.xmlParser = xmlParser;
    }

    @FXML
    private TextArea outputTextArea;
    @FXML
    private Button deserializeButton;

    @FXML
    private Button loadDataButton;

    @FXML
    private Button serializeButton;

    @FXML
    void deserialize(ActionEvent event) {

        xmlParser.deserialize();
        outputTextArea.setText(xmlParser.getOutput());
    }

    @FXML
    void loadData(ActionEvent event) throws IOException {

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz plik");
        String userHomeDir = System.getProperty("user.home");
        fileChooser.setInitialDirectory(new File(userHomeDir+"/Desktop/xml"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        event.consume();
        xmlParser.load(selectedFile);

    }

    @FXML
    void serialize(ActionEvent event) {
        xmlParser.serialize();
        outputTextArea.setText(xmlParser.getOutput());
    }

    @FXML
    private TabPane myTabPane;

    @FXML
    private Tab jaxbTab;

    @FXML
    private Tab jaxpTab;

    @FXML
    private Tab xsltTab;


    @FXML
    void changedToJaxb(Event event) {

        if(xmlParser instanceof JAXBParser){
            return;
        }
        xmlParser = new JAXBParser();

    }

    @FXML
    void changedToJaxp(Event event) {

        if(xmlParser instanceof JAXPParser){
            return;
        }
        xmlParser = new JAXPParser();

    }

    @FXML
    void changedToXslt(Event event) {

        if(xmlParser instanceof XSLTParser){
            return;
        }
        xmlParser = new XSLTParser();

    }



}
