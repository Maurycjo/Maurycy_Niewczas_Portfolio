package java;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.Files;
import java.nio.file.Path;

public class MainWindow extends JFrame {

    java.FileHandler fileHandler = new FileHandler();
    JList<Object> jFileList = new JList<>();
    JTextField pathField = new JTextField();
    private final JTextArea contentJtextArea;
    private final JTextArea infoJtextArea;
    JScrollPane sp ;
    JScrollPane contentSp;
    DefaultListModel listModel = new DefaultListModel();
    JTable measurementTable;
    String[] columnNames ={"Ciśnienie [hPa]", "Temperatura [℃]", "Wilgotność [%]"}; //column names in content display
    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);


    JTextField fileNameTextField;
    JTextField avgPressureTextField;
    JTextField avgTemperatureTextField;
    JTextField avgHumidityTextField;
    JTextField loadFromTextField;
    JTextField md5TextField ;

    java.CsvFileElement csvFileElement = null;

    private void updateJfileList(){
        //updating JFileList with item from current path and .md5 path
        fileHandler.fillFilesPathList();
        jFileList.removeAll();
        listModel.clear();
        for(Path path:fileHandler.getFilesPath()){

            String text;
            if(Files.isDirectory(path)){
                text="Dir  |";
            } else if(path.toString().endsWith(".csv")){
                text="Csv |";
            } else{
                text="File |";
            }

            listModel.addElement(text+path.getFileName());
        }
        jFileList.setModel(listModel);

        jFileList.repaint();
        measurementTable.repaint();
        pathField.setText(fileHandler.getCurrentPath().toString());
    }

    private void loadRowsToMeasurementTable(int n, java.CsvFileElement csvFileElement){

        tableModel = (DefaultTableModel) measurementTable.getModel();
        tableModel.setRowCount(0);

        for(int i=0;i<n;i++){
            if(i==csvFileElement.getMeasurementArrayList().size()){
                break;
            }else {

                float pressure = csvFileElement.getMeasurementArrayList().get(i).getPressure();
                float temperature = csvFileElement.getMeasurementArrayList().get(i).getTemperature();
                float humidity = csvFileElement.getMeasurementArrayList().get(i).getHumidity();

                Object[] objs = {String.valueOf(pressure), String.valueOf(temperature), String.valueOf(humidity)};
                tableModel.addRow(objs);
            }
        }
        jFileList.repaint();
        measurementTable.repaint();
    }

    public MainWindow()
    {
        //JPanel cardPanel = new JPanel();
        setTitle("Aplikacja WeakReferences");
        setSize(1000, 400);

        pathField.setText(fileHandler.getCurrentPath().toString());
        pathField.setEditable(false);

        JPanel jp = new JPanel();

        measurementTable = new JTable(tableModel);

        updateJfileList();
        jFileList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt){
                JList jFileList = (JList)evt.getSource();
                if(evt.getClickCount()==2){

                    fileHandler.clearFiles();
                    fileHandler.fillFilesList();

                    //after 2 clicking two times on item in jFile list change directory if possible
                    int index = jFileList.locationToIndex(evt.getPoint());


                    ElementInFileSystem clickedElement;
                    //dir
                    if(Files.isDirectory(fileHandler.getFilesPath().get(index))){

                        clickedElement = new DirElement(fileHandler.getFilesPath().get(index));
                        fileHandler.setCurrentPath(clickedElement.getFilePath());

                    } else if(fileHandler.getFilesPath().get(index).toString().endsWith(".csv")){

                        //csv file
                        clickedElement = new java.CsvFileElement(fileHandler.getFilesPath().get(index));

                        //check if csv is in memory
                        if(fileHandler.getFilesWeakHashMap().get(clickedElement.getFilePath())==null){

                            clickedElement.readFile();
                            fileHandler.getFilesWeakHashMap().put(fileHandler.getFiles().get(index).getFilePath(), (java.CsvFileElement) clickedElement);
                            loadFromTextField.setText("Załadowano z dysku");
                        }
                        else{
                            clickedElement =fileHandler.getFilesWeakHashMap().get(clickedElement.getFilePath());
                            loadFromTextField.setText("Załadowano z pamięci");
                        }

                        loadRowsToMeasurementTable(100, (java.CsvFileElement) clickedElement);
                        loadAdditionalInfo((java.CsvFileElement) clickedElement);

                    } else {
                        clickedElement = new java.FileElement(fileHandler.getFilesPath().get(index));
                        loadAdditionalInfo((java.FileElement) clickedElement);
                    }
                }
                updateJfileList();
                }
        });

        sp = new JScrollPane(jFileList);
        contentSp = new JScrollPane(measurementTable);

        //jtextArea for file content
        contentJtextArea = new JTextArea();
        contentJtextArea.setVisible(true);

        contentJtextArea.setPreferredSize(new Dimension(100, 100));

        //jtextArea for hash, from memory or disc
        infoJtextArea = new JTextArea();
        infoJtextArea.setVisible(true);


        //additional info
        JPanel additionalInfoPanel = new JPanel();
        additionalInfoPanel.setLayout(new BoxLayout(additionalInfoPanel, BoxLayout.Y_AXIS));


        fileNameTextField = new JTextField("Nazwa pliku:                                   ");
        avgPressureTextField = new JTextField("Średnie ciśnienie:                                  ");
        avgTemperatureTextField = new JTextField("Średnia temperatura:                                  ");
        avgHumidityTextField = new JTextField("Średnia wilgotność:                                   ");
        loadFromTextField = new JTextField("Załadowano z                                  ");
        md5TextField = new JTextField("Hash MD5:                                  ");


        additionalInfoPanel.add(fileNameTextField);
        additionalInfoPanel.add(avgPressureTextField);
        additionalInfoPanel.add(avgTemperatureTextField);
        additionalInfoPanel.add(avgHumidityTextField);
        additionalInfoPanel.add(loadFromTextField);
        additionalInfoPanel.add(md5TextField);

        fileNameTextField.setEditable(false);
        avgPressureTextField.setEditable(false);
        avgTemperatureTextField.setEditable(false);
        avgHumidityTextField.setEditable(false);
        loadFromTextField.setEditable(false);
        md5TextField.setEditable(false);

        //Buttons
        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Powrót");
        JButton refreshButton = new JButton("Odśwież");

        buttonPanel.add(backButton);
        buttonPanel.add(refreshButton);

        // add ActionListeners
        backButton.addActionListener(new ActionListener()
        {
            //go 1 level up in directory
            public void actionPerformed(ActionEvent arg0)
            {
              fileHandler.parentPath();
              updateJfileList();
            }
        });

        refreshButton.addActionListener(new ActionListener()
        {
            //refresh, checking checksums, deletions, additions
            public void actionPerformed(ActionEvent arg0)
            {
                updateJfileList();
            }
        });

        getContentPane().add(pathField, BorderLayout.NORTH);
        getContentPane().add(sp, BorderLayout.WEST);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        getContentPane().add(contentSp, BorderLayout.CENTER);
        getContentPane().add(additionalInfoPanel, BorderLayout.EAST);
    }

     private void loadAdditionalInfo(CsvFileElement csvFileElement){

         fileNameTextField.setText("Nazwa pliku: " + csvFileElement.getFileName());
         avgPressureTextField.setText("Średnie ciśnienie: " + csvFileElement.getAvgPressure()+ "[hPa]");
         avgTemperatureTextField.setText("Średnia temperatura: " + csvFileElement.getAvgTemperature()+"[℃]");
         avgHumidityTextField.setText("Średnia wilgotność:  "+ csvFileElement.getAvgHumidity() + "[%]");
         md5TextField.setText("Hash MD5: " + csvFileElement.getMd5CheckSum());

     }

    private void loadAdditionalInfo(FileElement fileElement){


        fileNameTextField.setText("Nazwa pliku: " + fileElement.getFileName());
        avgPressureTextField.setText("Średnie ciśnienie: Brak danych");
        avgTemperatureTextField.setText("Średnia temperatura: Brak danych");
        avgHumidityTextField.setText("Średnia wilgotność: Brak danych");
        loadFromTextField.setText("Załadowano z " + "dysku");
        md5TextField.setText("Hash MD5: " + fileElement.getMd5CheckSum());

    }

}