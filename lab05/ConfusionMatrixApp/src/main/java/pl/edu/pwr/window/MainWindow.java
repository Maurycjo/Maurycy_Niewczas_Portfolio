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


    final String CSV_DIR_PATH = "../csv/";


    JScrollPane csvScrollPane ;


    //String[] classColumnNames ={"Klasy", "a", "b", "c", "d", "e", "f"};

    DefaultTableModel csvTableModel= new DefaultTableModel();
    JTable csvTable = new JTable(csvTableModel);




    //DefaultTableModel csvTableModel = new DefaultTableModel(csvColumnNames, 0);



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



        String header[] = csvFile.getDataSet().getHeader();

        String[] columnNames =  new String[header.length + 1];




        csvTableModel.setDataVector(csvFile.getDataSet().getData(), csvFile.getDataSet().getHeader());
        csvTableModel.addColumn("Klasy", header);
        csvTable.moveColumn(csvTable.getColumnCount()-1, 0);





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
        //csvTable = new JTable(csvTableModel);


        // classes JTable components initialization



        loadAlgorithmsToComboBox();
        loadCsvFileSelectionToComboBox();

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
        selectionPanel.add(selectAlgorithmPanel, BorderLayout.EAST);


        selectCsvFileComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadCsvFileToTable();
            }
        });




        // add components to main window
        getContentPane().add(selectionPanel, BorderLayout.NORTH);
        getContentPane().add(csvScrollPane, BorderLayout.CENTER);

    }
}