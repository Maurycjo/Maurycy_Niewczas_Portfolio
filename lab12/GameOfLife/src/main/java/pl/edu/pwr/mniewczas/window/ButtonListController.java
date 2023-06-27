package pl.edu.pwr.mniewczas.window;

import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.script.ScriptException;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class ButtonListController{

    private ArrayList<ArrayList<MyButton>> myButtonArrayList = new ArrayList<>();
    private GridPane gridPane;
    private int size;
    private MyScriptManager myScriptManager;
    private Stage stage;


    public ArrayList<ArrayList<MyButton>> getMyButtonArrayList() {
        return myButtonArrayList;
    }

    public MyScriptManager getMyScriptManager() {
        return myScriptManager;
    }

    public void setMyScriptManager(MyScriptManager myScriptManager) {
        this.myScriptManager = myScriptManager;
    }

    public int getSize() {
        return size;
    }

    public ButtonListController(GridPane gridPane, int size, Stage stage) {
        this.gridPane = gridPane;
        this.size = size;
        this.stage = stage;
        createArrayListOfMyButton();
        addButtonsToGridPane();
    }

    private void createArrayListOfMyButton(){

        for(int i=0; i<size; i++){
            ArrayList<MyButton> row = new ArrayList<>();

            for(int j=0; j<size; j++){
                row.add(new MyButton());
            }
            myButtonArrayList.add(row);
        }
    }

   public void changeArrayListSize(int newSize){

        if(newSize>size){

            for(int i=size; i<newSize; i++){

                ArrayList<MyButton> row = new ArrayList<>();
                for(int j=0;j<newSize;j++){
                    row.add(new MyButton());
                    gridPane.add(row.get(j), i, j);
                }
                myButtonArrayList.add(row);
            }

            for(int i=0;i<size;i++){
                for(int j=size; j<newSize; j++){
                    myButtonArrayList.get(i).add(new MyButton());
                    gridPane.add(myButtonArrayList.get(i).get(j), i, j);
                }
            }

        } else if (newSize<size) {

            for(int i=size-1; i>newSize-1; i--){
                myButtonArrayList.remove(i);

            }
            gridPane.getChildren().removeIf(node -> GridPane.getColumnIndex(node) > newSize-1);

            for(int i=0;i<newSize;i++){
                for(int j=size-1; j>newSize-1;j--){
                    myButtonArrayList.get(i).remove(j);
                }
            }

            gridPane.getChildren().removeIf(node -> GridPane.getRowIndex(node) > newSize-1);

        }

        this.size = newSize;
   }
    void changeButtonStateWithCoord(int row, int column){
        myButtonArrayList.get(row).get(column).changeState();
    }

    public void addButtonsToGridPane(){

        for(int i=0;i<size;i++){
            for(int j=0;j<size; j++){
                gridPane.add(myButtonArrayList.get(i).get(j), j, i);
            }
        }
    }

    public void clearBoard(){

        for(var list : myButtonArrayList){

            for(var but : list){

                but.changeStateIfDifferent(State.WHITE);

            }

        }

    }


    public void displayBoardInConsole(){

        for(var list: myButtonArrayList){

            for(var but : list){

                if(but.getCurrentState()== State.WHITE){
                    System.out.print("0 ");
                } else{
                    System.out.print("1 ");
                }
            }
            System.out.println();

        }

    }

    public String getButtonStatesWithNeighbour(int row, int column){

        StringBuilder states = new StringBuilder();

        for(int i=row-1;i<row+2;i++){

            for(int j=column-1; j<column+2; j++){

                if(i<0||i>size-1||j<0||j>size-1) {
                    states.append(0);
                } else {

                    //System.out.print(myButtonArrayList.get(i).get(j));
                    MyButton currentButton = myButtonArrayList.get(i).get(j);
                    if(currentButton.getCurrentState()==State.BLACK){
                        states.append(1);
                    } else {
                        states.append(0);
                    }
                }
            }
        }

        return states.toString();
    }

    public void calculateNextGeneration(){

        boolean isCellAlive;
        for(int i=0;i<size;i++){

            for(int j=0;j<size;j++){
                String array = getButtonStatesWithNeighbour(i, j);
                try {
                    isCellAlive = myScriptManager.checkCellStateInNextGeneration(array);

                } catch (ScriptException | NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }

                if(isCellAlive){
                    myButtonArrayList.get(i).get(j).setNextState(State.BLACK);
                } else{
                    myButtonArrayList.get(i).get(j).setNextState(State.WHITE);
                }
            }
        }

        for(var list : myButtonArrayList){

            for(var butt : list){

                butt.updateState();
            }

        }
    }

    public void loadModel(){

        FileChooser loadFileChooser = new FileChooser();
        loadFileChooser.setTitle("Wczytaj model");
        File userDesktopPath = new File(System.getProperty("user.home") + File.separator + "Desktop");
        loadFileChooser.setInitialDirectory(userDesktopPath);
        File selectedFile = loadFileChooser.showOpenDialog(stage);

        int newSize = size;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(selectedFile));

            int character;
            int columnIndex=0;
            int rowIndex = 0;


            while ((character = reader.read()) != -1) {
                char c = (char) character;

                if(c=='0'){
                    myButtonArrayList.get(rowIndex).get(columnIndex).changeStateIfDifferent(State.WHITE);
                    rowIndex++;
                } else if(c=='1'){
                    myButtonArrayList.get(rowIndex).get(columnIndex).changeStateIfDifferent(State.BLACK);
                    rowIndex++;
                } else if(c=='\n'){
                    rowIndex = 0;
                    columnIndex++;
                }

            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveModel(){

        FileChooser saveFileChooser = new FileChooser();
        saveFileChooser.setTitle("Zapisz model");
        File userDesktopPath = new File(System.getProperty("user.home") + File.separator + "Desktop");
        saveFileChooser.setInitialDirectory(userDesktopPath);
        File selectedFile = saveFileChooser.showSaveDialog(stage);

        try {
            selectedFile.createNewFile();
        } catch (IOException e) {
            return;
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));

            for(var list : myButtonArrayList){

                for(var but : list){

                    if(but.getCurrentState()== State.WHITE){
                        writer.append("0");
                    } else{
                        writer.append("1");
                    }
                }
                writer.append("\n");
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
