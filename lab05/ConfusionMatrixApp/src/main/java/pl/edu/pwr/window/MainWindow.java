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


    JComboBox selectCsvFileComboBox;
    JLabel selectCsvLabel = new JLabel("Wybierz plik: ");

    JComboBox selectAlgorithmComboBox;
    JLabel selectAlgorithmLabel = new JLabel("Wybierz współczynnik przetwarzania: ");
    private void loadCsvFileSelectionToComboBox(){

        String csvFileNames[] ={"csv1", "csv2", "csv3"};

        selectCsvFileComboBox = new JComboBox<>(csvFileNames);
    }

    private void loadAlgorithmsToComboBox(){

        String algorithmNames[] = {"kappa", "alg2", "alg3"};
        selectAlgorithmComboBox = new JComboBox(algorithmNames);

    }


    public MainWindow()
    {

        // JPanel cardPanel = new JPanel();
        setTitle("Aplikacja Macierzy Niezgodności");
        setSize(800, 500);

        // csv JTable components initialization
        csvTable = new JTable(csvTableModel);
        csvScrollPane = new JScrollPane(csvTable);
        csvScrollPane.setPreferredSize(new Dimension(CSV_COLUMN_1_WIDTH + CSV_COLUMN_2_WIDTH, 100));


        // classes JTable components initialization
        classTable = new JTable(classTableModel);
        classScrollPane = new JScrollPane(classTable);


        loadAlgorithmsToComboBox();
        loadCsvFileSelectionToComboBox();

        // selection csv and algorithm panels
        JPanel selectionPanel = new JPanel();
        JPanel selectCsvPanel = new JPanel();
        JPanel selectAlgorithmPanel = new JPanel();
        selectCsvPanel.add(selectCsvLabel);
        selectCsvPanel.add(selectCsvFileComboBox);
        selectAlgorithmPanel.add(selectAlgorithmLabel);
        selectAlgorithmPanel.add(selectAlgorithmComboBox);
        selectionPanel.add(selectCsvPanel, BorderLayout.WEST);
        selectionPanel.add(selectAlgorithmPanel, BorderLayout.EAST);

        // add components to main window
        getContentPane().add(selectionPanel, BorderLayout.NORTH);
        getContentPane().add(csvScrollPane, BorderLayout.WEST);
        getContentPane().add(classScrollPane, BorderLayout.CENTER);
    }
}