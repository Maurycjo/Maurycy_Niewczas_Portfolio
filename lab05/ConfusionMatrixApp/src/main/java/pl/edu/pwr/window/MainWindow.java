package pl.edu.pwr.window;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class MainWindow extends JFrame {

    final int CSV_COLUMN_1_WIDTH = 80, CSV_COLUMN_2_WIDTH =80;

    JScrollPane csvScrollPane ;
    JScrollPane classScrollPane;
    String[] csvColumnNames = {"Klasa", "Grupa"};
    String[] classColumnNames ={"Klasy", "a", "b", "c", "d", "e", "f"};

    JTable csvTable;
    JTable classTable;

    String[] rowHeadersData = new String[classColumnNames.length + 1];

    DefaultTableModel csvTableModel = new DefaultTableModel(csvColumnNames, 0);
    DefaultTableModel classTableModel= new DefaultTableModel(classColumnNames, 0);



    private void updateFileTable(){
    }

    private void updateClassTable(){


    }

    public MainWindow()
    {

        // JPanel cardPanel = new JPanel();
        setTitle("Aplikacja Ładowacza Klas");
        setSize(800, 500);

        // csv JTable components initialization
        csvTable = new JTable(csvTableModel);
        csvScrollPane = new JScrollPane(csvTable);
        csvScrollPane.setPreferredSize(new Dimension(CSV_COLUMN_1_WIDTH + CSV_COLUMN_2_WIDTH, 100));


        // classes JTable components initialization
        classTable = new JTable(classTableModel);
        rowHeadersData[0] = "Klasy";
        System.arraycopy(classColumnNames, 0, rowHeadersData, 1, classColumnNames.length);
        classScrollPane = new JScrollPane(classTable);




        getContentPane().add(csvScrollPane, BorderLayout.WEST);
        getContentPane().add(classScrollPane, BorderLayout.CENTER);
    }
}