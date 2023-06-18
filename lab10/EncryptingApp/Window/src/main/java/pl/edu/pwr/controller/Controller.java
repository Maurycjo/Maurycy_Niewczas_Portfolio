package pl.edu.pwr.controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import pl.edu.pwr.window.FileWalker;

public class Controller {


    FileWalker fileWalker = new FileWalker();
    ObservableList<String> fileList = FXCollections.observableArrayList();

    private void fillFileListView(){

        fileList.clear();
        fileWalker.fillPathList();
        currentPathLabel.setText(fileWalker.getCurrentPath().toString());

        for(var path : fileWalker.getCurrentPathsArrayList()){

            fileList.add(fileWalker.getOutputInList(path));
        }
        fileListView.setItems(fileList);


    }

    @FXML
    private Label currentPathLabel;
    @FXML
    private ListView<String> fileListView;

    @FXML
    private Button backButton;

    @FXML
    void parentPath(){

        fileWalker.parentPath();
        fillFileListView();

    }

    @FXML
    private Button deleteFileButton;

    @FXML
    void deleteFile(){

    }

    @FXML
    private void initialize(){

        fillFileListView();

        fileListView.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && mouseEvent.getClickCount() == 2) {

                int selectedIndex = fileListView.getSelectionModel().getSelectedIndex();
                if(selectedIndex != -1 && fileWalker.checkIfDirectory(selectedIndex)){
                    fileWalker.clickOnElement(selectedIndex);
                    fileList.clear();
                    fileWalker.fillPathList();
                    fillFileListView();
                }
            }
        });


    }

    //Key Tab
    //****************************8
    @FXML
    private TextField nameOfCreatedKeyTextField;

    @FXML
    private ComboBox<?> algorithmOfCreatedKeyComboBox;

    @FXML
    private ComboBox<?> sizeOfCreatedKeyComboBox;


    @FXML
    void generateKey(ActionEvent event) {

    }



    //algorithm Tab
    //**********************************
    @FXML
    private ComboBox<String> selectAlgorithmComboBox;

    @FXML
    private Button decryptButton;

    @FXML
    private Button encryptButton;


    @FXML
    private Button generateKeyButton;

    @FXML
    private Button inputFileButton;

    @FXML
    private Button inputKeyButton;



    @FXML
    private Button removeFileButton;

    @FXML
    private Button removeKeyButton;

    @FXML
    void decrypt(ActionEvent event) {

    }

    @FXML
    void encrypt(ActionEvent event) {

    }

    @FXML
    void inputFile(ActionEvent event) {

    }

    @FXML
    void inputKey(ActionEvent event) {

    }

    @FXML
    void removeFile(ActionEvent event) {

    }

    @FXML
    void removeKey(ActionEvent event) {

    }


}
