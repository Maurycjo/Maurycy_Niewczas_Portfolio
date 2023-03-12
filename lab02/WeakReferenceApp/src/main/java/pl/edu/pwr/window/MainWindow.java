package pl.edu.pwr.window;

import pl.edu.pwr.file.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MainWindow extends JFrame {

    private CardLayout cl;
    FileHandler fileHandler = new FileHandler();
    JList<Object> jFileList = new JList<>();
    JTextField pathField = new JTextField();
    private final JTextArea contentJtextArea;
    private final JTextArea infoJtextArea;
    JScrollPane sp ;
    JScrollPane contentSp;
    DefaultListModel listModel = new DefaultListModel();
    JTable measurementTable;
    String[] columnNames ={"Ciśnienie", "Temperatura", "Wilgotność"}; //column names in content display
    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);


    JTextField fileNameTextField;
    JTextField avgPressureTextField;
    JTextField avgTemperatureTextField;
    JTextField avgHumidityTextField;
    JTextField loadFromTextField;
    JTextField md5TextField ;



    CsvFileElement csvFileElement = null;



    private void updateJfileList(){
        //updating JFileList with item from current path and .md5 path
        fileHandler.fillFilesList();
        jFileList.removeAll();
        listModel.clear();
        for(ElementInFileSystem element:fileHandler.getFiles()){
            listModel.addElement(element.getFileNameWithInfo());
        }
        jFileList.setModel(listModel);

        jFileList.repaint();
        measurementTable.repaint();
        pathField.setText(fileHandler.getCurrentPath().toString());

    }

    private void loadRowsToMeasurementTable(int n, CsvFileElement csvFileElement){

        tableModel = (DefaultTableModel) measurementTable.getModel();
        tableModel.setRowCount(0);

        for(int i=0;i<n;i++){
            if(i==csvFileElement.getMeasurementArrayList().size()){
                break;
            }else {

                float pressure = csvFileElement.getMeasurementArrayList().get(i).getPressure();
                float temperature = csvFileElement.getMeasurementArrayList().get(i).getTemperature();
                int humidity = csvFileElement.getMeasurementArrayList().get(i).getHumidity();

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
        setSize(800, 400);

        pathField.setText(fileHandler.getCurrentPath().toString());
        pathField.setEditable(false);

        JPanel jp = new JPanel();

        measurementTable = new JTable(tableModel);

        updateJfileList();
        jFileList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt){
                JList jFileList = (JList)evt.getSource();
                if(evt.getClickCount()==2){
                    //after 2 clicking two times on item in jFile list change directory if possible
                    int index = jFileList.locationToIndex(evt.getPoint());

                    if(fileHandler.getFiles().get(index) instanceof DirElement){
                        fileHandler.setCurrentPath(fileHandler.getFiles().get(index).getFilePath());
                    } else{
                        fileHandler.getFiles().get(index).readFile();

                        if(fileHandler.getFiles().get(index) instanceof CsvFileElement){
                            loadRowsToMeasurementTable(100, (CsvFileElement) fileHandler.getFiles().get(index));
                            loadAdditionalInfo((CsvFileElement) fileHandler.getFiles().get(index));
                        }
                    }
                    updateJfileList();
                }
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

        fileNameTextField = new JTextField();
        avgPressureTextField = new JTextField();
        avgTemperatureTextField = new JTextField();
        avgHumidityTextField = new JTextField();
        loadFromTextField = new JTextField();
        md5TextField = new JTextField();

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
         avgPressureTextField.setText("Średnie ciśnienie: " + csvFileElement.getAvgPressure());
         avgTemperatureTextField.setText("Średnia temperatura: " + csvFileElement.getAvgTemperature());
         avgHumidityTextField.setText("Średnia wilgotność[%]:  "+ csvFileElement.getAvgHumidity());
         loadFromTextField.setText("Załadowano z " + "dysku");
         md5TextField.setText("Hash MD5: " + csvFileElement.getMd5CheckSum());

     }



}