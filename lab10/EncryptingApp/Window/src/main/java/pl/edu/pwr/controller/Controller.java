package pl.edu.pwr.controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import pl.edu.pwr.encryptor.EncryptorController;
import pl.edu.pwr.encryptor.EncryptorTypeEnum;
import pl.edu.pwr.key.KeyGeneratorController;
import pl.edu.pwr.key.KeyTypeEnum;
import pl.edu.pwr.window.FileWalker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class Controller {


    FileWalker fileWalker = new FileWalker();
    ObservableList<String> fileList = FXCollections.observableArrayList();
    EncryptorController encryptorController = new EncryptorController();
    KeyGeneratorController keyGeneratorController = new KeyGeneratorController();
    Path keyPath = null;
    Path filePath = null;

    String algorithmsNames[] = {"RSA", "AES"};
    String rsaAvailableSizes[] = {"512", "1024", "2048"};
    String aesAvailableSizes[] = {"128", "192", "256"};

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

        int selectedIndex = fileListView.getSelectionModel().getSelectedIndex();
        if(selectedIndex != -1 && !fileWalker.checkIfDirectory(selectedIndex)){
            filePath = fileWalker.getCurrentPathsArrayList().get(selectedIndex);

            try {
                Files.delete(filePath);
            } catch (IOException e){
                e.printStackTrace();
            }

        }
    fillFileListView();

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
                } else if(selectedIndex != -1){

                    fileDisplayTextArea.clear();
                    fileDisplayTextArea.setText(fileWalker.getFileDisplay(selectedIndex));
                }
            }
        });

        algorithmOfCreatedKeyComboBox.setItems(FXCollections.observableArrayList(algorithmsNames));
        selectAlgorithmComboBox.setItems(FXCollections.observableArrayList(algorithmsNames));
        algorithmOfCreatedKeyComboBox.getSelectionModel().selectFirst();
        sizeOfCreatedKeyComboBox.setItems(FXCollections.observableArrayList(rsaAvailableSizes));
        sizeOfCreatedKeyComboBox.getSelectionModel().selectFirst();
        selectAlgorithmComboBox.getSelectionModel().selectFirst();


    }


    //Key Tab
    //****************************8
    @FXML
    private TextField nameOfCreatedKeyTextField;

    @FXML
    private ComboBox<String> algorithmOfCreatedKeyComboBox;

    @FXML
    private ComboBox<String> sizeOfCreatedKeyComboBox;

    @FXML
    void algorithmSelected(){
        if(algorithmOfCreatedKeyComboBox.getValue().equals("RSA")){
            sizeOfCreatedKeyComboBox.setItems(FXCollections.observableArrayList(rsaAvailableSizes));
            sizeOfCreatedKeyComboBox.getSelectionModel().selectFirst();

        } else{
            sizeOfCreatedKeyComboBox.setItems(FXCollections.observableArrayList(aesAvailableSizes));
            sizeOfCreatedKeyComboBox.getSelectionModel().selectFirst();
        }


    }

    @FXML
    void generateKey(ActionEvent event) {

        keyGeneratorController.setKeyPath(fileWalker.getCurrentPath().toString());


        int size = Integer.valueOf(sizeOfCreatedKeyComboBox.getValue());
        String fileName = nameOfCreatedKeyTextField.getText();
        if(fileName.equals("")) {
                fileName="key";
                nameOfCreatedKeyTextField.setText("key");
            }

        if(algorithmOfCreatedKeyComboBox.getValue().equals("RSA")){
            keyGeneratorController.generateKey(KeyTypeEnum.RSA, size, fileName);
        } else if(algorithmOfCreatedKeyComboBox.getValue().equals("AES")){
            keyGeneratorController.generateKey(KeyTypeEnum.AES, size, fileName);
        }
        fillFileListView();
        //info window
        //przypadek kiedy nie wybierze się algorytmu

    }



    //algorithm Tab
    //**********************************
    @FXML
    private ComboBox<String> selectAlgorithmComboBox;

    @FXML
    private TextArea fileDisplayTextArea;
    @FXML
    private Button decryptButton;

    @FXML
    private Button encryptButton;


    @FXML
    private Button generateKeyButton;

    @FXML
    private Button inputFileButton;

    @FXML
    private Label selectedFileLabel;

    @FXML
    private Label selectedKeyLabel;

    @FXML
    private Button inputKeyButton;



    @FXML
    private Button removeFileButton;

    @FXML
    private Button removeKeyButton;

    @FXML
    void decrypt(ActionEvent event) {

        if(filePath == null || keyPath == null){
            return;
        }

        encryptorController.loadFile(filePath.getFileName().toString());
        encryptorController.loadKeyFromFile(keyPath.getFileName().toString());

        if(selectAlgorithmComboBox.getValue().equals("RSA")){
            encryptorController.decryptFile(EncryptorTypeEnum.RSA, filePath.getFileName().toString());
        } else if (selectAlgorithmComboBox.getValue().equals("AES")) {
            encryptorController.decryptFile(EncryptorTypeEnum.AES, filePath.getFileName().toString());
        }

        fillFileListView();
    }

    @FXML
    void encrypt(ActionEvent event) {

        if(filePath == null || keyPath == null){
            return;
        }

        encryptorController.setDirName(fileWalker.getCurrentPath().toString());
        encryptorController.loadFile(filePath.getFileName().toString());
        encryptorController.loadKeyFromFile(keyPath.getFileName().toString());

        if(selectAlgorithmComboBox.getValue().equals("RSA")){
            encryptorController.encryptFile(EncryptorTypeEnum.RSA, filePath.getFileName().toString());
        } else if (selectAlgorithmComboBox.getValue().equals("AES")) {
            encryptorController.encryptFile(EncryptorTypeEnum.AES, filePath.getFileName().toString());
        }

        fillFileListView();
    }

    @FXML
    void inputFile(ActionEvent event) {

        int selectedIndex = fileListView.getSelectionModel().getSelectedIndex();
        if(selectedIndex != -1 && !fileWalker.checkIfDirectory(selectedIndex)){
            filePath = fileWalker.getCurrentPathsArrayList().get(selectedIndex);
            selectedFileLabel.setText(filePath.getFileName().toString());

        }

    }

    @FXML
    void inputKey(ActionEvent event) {

        int selectedIndex = fileListView.getSelectionModel().getSelectedIndex();
        if(selectedIndex != -1 && !fileWalker.checkIfDirectory(selectedIndex)){
            keyPath = fileWalker.getCurrentPathsArrayList().get(selectedIndex);
            selectedKeyLabel.setText(keyPath.getFileName().toString());
        }

    }

    @FXML
    void removeFile(ActionEvent event) {

        filePath = null;
        selectedFileLabel.setText("brak");

    }

    @FXML
    void removeKey(ActionEvent event) {

        keyPath = null;
        selectedKeyLabel.setText("brak");

    }


}
