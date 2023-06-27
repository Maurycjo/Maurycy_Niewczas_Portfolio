package pl.edu.pwr.mniewczas.window;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainWindow extends Application{

    public static void main(String[] args) {
        launch(args);
    }


    ButtonListController buttonListController;
    GridPane gridPane;
    boolean isCalculated = false;
    Thread calculationThread;
    Long currentTimeBetweenCycle = 700L;
    Path scriptPath = null;
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Gra w życie");

        MyScriptManager myScriptManager = new MyScriptManager();
        FileChooser fileChooser = new FileChooser();

        //Panes
        BorderPane root = new BorderPane();
        gridPane = new GridPane();
        FlowPane flowPane = new FlowPane();

        root.setCenter(gridPane);
        root.setTop(flowPane);
        gridPane.setAlignment(Pos.CENTER);

        //components on top
        Button clearButton = new Button("Wyczyść");
        Button saveModelButton = new Button("Zapisz");
        Button loadModelButton = new Button("Wczytaj Model");
        Button loadScriptButton = new Button("Załaduj Automat");
        Label sizeLabel = new Label("Rozmiar planszy: ");
        ComboBox<String> boardSizeComboBox = new ComboBox<>();
        Button startPauseButton = new Button("Start");
        Label timeLabel = new Label("Czas [ms]");
        Slider timeSlider = new Slider(300, 2000, currentTimeBetweenCycle);
        Label currentTimeLabel = new Label(String.valueOf(timeSlider.getValue()));
        currentTimeLabel.setMinWidth(100);
        flowPane.setAlignment(Pos.CENTER);
        flowPane.setHgap(10);

        flowPane.getChildren().addAll(clearButton, saveModelButton, loadModelButton,
                                        loadScriptButton,sizeLabel, boardSizeComboBox,
                                        startPauseButton, timeLabel, timeSlider, currentTimeLabel);


        timeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            currentTimeLabel.setText(String.valueOf(newValue.intValue()));
            currentTimeBetweenCycle = newValue.longValue();
        });


        Map<String, Integer> boardSizeMap = new LinkedHashMap<>(){{
           put("10x10", 10);
            put("20x20", 20);
            put("30x30", 30);
        }};
        boardSizeComboBox.setItems(FXCollections.observableArrayList(boardSizeMap.keySet()));
        boardSizeComboBox.getSelectionModel().select(2);

        //file chooser
        fileChooser.setTitle("Wybierz skrypt do załadowania");
        File userDesktopPath = new File(System.getProperty("user.home") + File.separator + "Desktop");
        fileChooser.setInitialDirectory(userDesktopPath);

        loadScriptButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scriptPath = fileChooser.showOpenDialog(stage).toPath();
                myScriptManager.loadScript(scriptPath);
                buttonListController.setMyScriptManager(myScriptManager);
            }
        });

        saveModelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                buttonListController.saveModel();
            }
        });

        loadModelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                buttonListController.loadModel();
            }
        });

        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                buttonListController.clearBoard();
            }
        });

        // test path *******
        buttonListController = new ButtonListController(gridPane, boardSizeMap.get(boardSizeComboBox.getValue()), stage);


        startPauseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(scriptPath==null) return;

                if(!isCalculated){
                    startPauseButton.setText("Stop");
                    isCalculated = true;
                    calculationThreadFun();
                    calculationThread.start();

                } else {
                    startPauseButton.setText("Start");
                    isCalculated = false;
                    calculationThread.interrupt();
                    calculationThread = null;
                }

            }
        });

        boardSizeComboBox.setOnAction(event -> {

            String selectedSize = boardSizeComboBox.getValue();
            int newSize = boardSizeMap.get(selectedSize);
            buttonListController.changeArrayListSize(newSize);

        });

        setTestStates();

        Scene scene = new Scene(root, 1000, 1000);
        stage.setScene(scene);
        stage.show();
    }


    private void setTestStates(){

        buttonListController.changeButtonStateWithCoord(1, 1);
        buttonListController.changeButtonStateWithCoord(1, 2);
    }
    
    
    private void calculationThreadFun(){
        calculationThread = new Thread(()->{
            
            while (isCalculated){
                buttonListController.calculateNextGeneration();

                try {
                    Thread.sleep(currentTimeBetweenCycle);
                } catch (InterruptedException e) {
                    return;
                }

            }
        });
    }

}
