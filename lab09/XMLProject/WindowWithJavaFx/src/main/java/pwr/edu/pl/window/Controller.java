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
import pwr.edu.pl.parser.*;
import javafx.scene.control.Tab;
import javafx.scene.web.WebView;

import java.io.File;
import java.io.IOException;

public class Controller {

    private Stage stage;
    File loadedFile;
    File xslFile;


    private JAXBParser jaxbParser = new JAXBParser();
    private XmlParser currentParser;
    private DomParser domParser = new DomParser();
    private XmlSAXParser xmlSAXParser = new XmlSAXParser();
    private XSLTParser xsltParser = new XSLTParser();

    public void setXmlParser(XmlParser xmlParser){
        currentParser = xmlParser;
    }

    //buttons
    @FXML
    private Button deserializeButton;
    @FXML
    private Button loadDataButton;
    @FXML
    private Button serializeButton;

    @FXML
    private Button loadXslButton;

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

    private TextArea currentTextArea;


    @FXML
    void deserialize(ActionEvent event) {
        currentParser.load(loadedFile);
        currentParser.deserialize();

        if(currentTextArea!=null){
            currentTextArea.setText(currentParser.getOutput());
        } else{
            xsltWebView.getEngine().loadContent(currentParser.getOutput());
        }
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

        jaxbParser.serialize();
        currentTextArea.setText(currentParser.getOutput());
    }

    @FXML
    void loadXsl(ActionEvent event){

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz plik");
        String userHomeDir = System.getProperty("user.home");
        fileChooser.setInitialDirectory(new File(userHomeDir+"/Desktop/xml"));
        xslFile = fileChooser.showOpenDialog(stage);
        xsltParser.loadXsltFile(xslFile);
        event.consume();
    }


    @FXML
    void changedToJaxb(Event event) {


        serializeButton.setVisible(false);
        if(currentParser instanceof JAXBParser){
            return;
        }
        serializeButton.setVisible(true);
        currentParser = jaxbParser;
        currentTextArea = jaxbTextArea;

    }
    @FXML
    void changedToDom(Event event) {

        if(currentParser instanceof DomParser){
            return;
        }
        currentParser = domParser;
        currentTextArea = domTextArea;
    }

    @FXML
    void changedToSax(Event event) {

        if(currentParser instanceof XmlSAXParser){
            return;
        }

        currentParser = xmlSAXParser;
        currentTextArea = saxTextArea;
    }
    @FXML
    void changedToXslt(Event event) {

        if(currentParser instanceof XSLTParser){
            return;
        }
        currentParser = xsltParser;
        currentTextArea = null;
    }

}
