package pl.edu.pwr.window;


import ex.api.AnalysisException;
import ex.api.AnalysisService;
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
import java.util.ServiceLoader;
import java.util.stream.Stream;


public class MainWindow extends JFrame {


    final String CSV_DIR_PATH = "../csv/";

    JScrollPane csvScrollPane ;

    DefaultTableModel csvTableModel= new DefaultTableModel();
    JTable csvTable = new JTable(csvTableModel);

    JComboBox selectCsvFileComboBox;
    JLabel selectCsvLabel = new JLabel("Wybierz plik: ");

    JComboBox selectAlgorithmComboBox;
    JLabel selectAlgorithmLabel = new JLabel("Wybierz współczynnik przetwarzania: ");

    JLabel resultInfoLabel = new JLabel("Wynik");
    JLabel resultLabel = new JLabel();

    JPanel resultPanel = new JPanel();
    CsvFile csvFile;
    ServiceLoader<AnalysisService> loader = ServiceLoader.load(AnalysisService.class);

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

        csvFile = new CsvFile(Path.of(CSV_DIR_PATH + selectCsvFileComboBox.getSelectedItem().toString()));
        csvFile.readFile();



        String header[] = csvFile.getDataSet().getHeader();

        String[] columnNames =  new String[header.length + 1];


        csvTableModel.setDataVector(csvFile.getDataSet().getData(), csvFile.getDataSet().getHeader());
        csvTableModel.addColumn("Klasy", header);
        csvTable.moveColumn(csvTable.getColumnCount()-1, 0);

        csvTable.setRowHeight(csvTable.getColumnCount()*8);
        csvTable.repaint();

    }

    private void loadResult(){


        for(AnalysisService service : loader){

            if(service.getName()==selectAlgorithmComboBox.getSelectedItem()){
                try {
                    service.submit(csvFile.getDataSet());
                } catch (AnalysisException e) {
                    throw new RuntimeException(e);
                }
                resultLabel.setText(String.valueOf(service.getResult()));
            }
        }
    }


    private void loadAlgorithmsToComboBox(){

        //ServiceLoader<AnalysisService> loader = ServiceLoader.load(AnalysisService.class);

        ArrayList<String> algorithmNamesArrayList = new ArrayList<>();

        for(var service:loader){
            algorithmNamesArrayList.add(service.getName());
        }

        selectAlgorithmComboBox = new JComboBox(algorithmNamesArrayList.toArray());

        loadResult();

    }


    private void saveData(){
        int rowCount = csvTable.getRowCount();
        int columnCount = csvTable.getColumnCount();

        String[][] data = new String[rowCount][columnCount-1];

        for (int row = 0; row < rowCount; row++) {
            for (int column = 1; column < columnCount; column++) {
                data[row][column-1] = (String) csvTable.getValueAt(row, column);
            }
        }

        csvFile.saveFile(data);
    }

    public MainWindow()
    {

        setTitle("Aplikacja Macierzy Niezgodności");
        setSize(1000, 400);

        resultPanel.add(resultInfoLabel, BorderLayout.WEST);
        resultPanel.add(resultLabel, BorderLayout.EAST);
        loadCsvFileSelectionToComboBox();
        loadAlgorithmsToComboBox();

        csvScrollPane = new JScrollPane(csvTable);

        // selection csv and algorithm panels
        JPanel selectionPanel = new JPanel();
        JPanel selectCsvPanel = new JPanel();
        JPanel selectAlgorithmPanel = new JPanel();
        selectCsvPanel.add(selectCsvLabel);
        selectCsvPanel.add(selectCsvFileComboBox);
        selectAlgorithmPanel.add(selectAlgorithmLabel);
        selectAlgorithmPanel.add(selectAlgorithmComboBox);
        selectionPanel.add(selectCsvPanel, BorderLayout.WEST);
        selectionPanel.add(selectAlgorithmPanel, BorderLayout.CENTER);
        selectionPanel.add(resultPanel, BorderLayout.EAST);

        JButton calculateButton = new JButton("Oblicz");
        JButton saveButton = new JButton("Zapisz");
        JButton restoreButton = new JButton("Anuluj");
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(calculateButton);
        buttonsPanel.add(saveButton);
        buttonsPanel.add(restoreButton);


        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadResult();
            }
        });

        restoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadCsvFileToTable();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveData();
            }
        });

        selectCsvFileComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadCsvFileToTable();
                loadResult();
            }
        });

        selectAlgorithmComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadResult();
            }
        });

        // add components to main window
        getContentPane().add(selectionPanel, BorderLayout.NORTH);
        getContentPane().add(csvScrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonsPanel, BorderLayout.SOUTH);

    }
}