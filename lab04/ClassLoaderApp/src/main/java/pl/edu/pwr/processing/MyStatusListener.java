package pl.edu.pwr.processing;

import pl.edu.pwr.window.MainWindow;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MyStatusListener implements StatusListener{


    JTable classTable;
    int selectedRow, column;

    public MyStatusListener(JTable classTable, int selectedRow, int column){

        this.classTable = classTable;
        this.selectedRow=selectedRow;
        this.column=column;
    }



    @Override
    public void statusChanged(Status s) {

        int value = s.getProgress();

        if(value>=100){
         classTable.setValueAt("Ukończone", selectedRow, column);
        } else {
            classTable.setValueAt(value, selectedRow, column);
        }

    }
}
