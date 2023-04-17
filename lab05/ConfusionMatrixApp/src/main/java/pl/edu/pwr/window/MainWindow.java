package pl.edu.pwr.window;

import pl.edu.pwr.file.CsvFile;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;


public class MainWindow extends JFrame {

    final int CSV_COLUMN_1_WIDTH = 80, CSV_COLUMN_2_WIDTH =80;
    final String CSV_DIR_PATH = "../csv/";


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

        ArrayList <String> csvFileNames = new ArrayList<>();

        try (Stream<Path> paths = Files.list(Paths.get(CSV_DIR_PATH))) {
            paths.peek(path -> {
                        if (path.toString().endsWith(".csv")) {
                            //selectCsvFileComboBox.add((Component) path.getFileName());
                            csvFileNames.add(String.valueOf(path.getFileName()));
                        }
                    })
                    .count(); // kończy strumień, ale nie wykonuje żadnych operacji
        } catch (Exception e) {
            e.printStackTrace();
        }

        selectCsvFileComboBox = new JComboBox<>(csvFileNames.toArray());

        loadCsvFileToTable();
    }

    private void loadCsvFileToTable(){

        csvTableModel = (DefaultTableModel) csvTable.getModel();
        csvTableModel.setRowCount(0);

        CsvFile csvFile = new CsvFile(Path.of(CSV_DIR_PATH + selectCsvFileComboBox.getSelectedItem().toString()));
        csvFile.readFile();

        for(var classWithAssigment: csvFile.getClassWithAssigmentArrayList()){

            String className = classWithAssigment.getClassName();
            int id = classWithAssigment.getGroupID();

            Object[] objs = {className, String.valueOf(id)};
            csvTableModel.addRow(objs);

        }

        csvTable.repaint();

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


        selectCsvFileComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadCsvFileToTable();
            }
        });









        // add components to main window
        getContentPane().add(selectionPanel, BorderLayout.NORTH);
        getContentPane().add(csvScrollPane, BorderLayout.WEST);
        getContentPane().add(classScrollPane, BorderLayout.CENTER);
    }
}