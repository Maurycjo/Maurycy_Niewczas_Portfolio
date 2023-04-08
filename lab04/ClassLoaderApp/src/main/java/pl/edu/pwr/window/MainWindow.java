package pl.edu.pwr.window;

import pl.edu.pwr.file.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class MainWindow extends JFrame {

    FileHandler fileHandler = new FileHandler();

    JTextField pathField = new JTextField();
    private final JTextArea contentJtextArea;
    private final JTextArea infoJtextArea;
    JScrollPane sp ;
    JScrollPane contentSp;


    String[] fileColumnNames = {"Typ", "Nazwa pliku"};
    String[] classColumnNames ={"Nazwa klasy", "Status klasy", "Opcja", "Nazwa metody(Podaj)", "Przycisk Wykonaj", "Status Metody"};

    JTable fileTable;
    JTable classTable;

    DefaultTableModel fileTableModel = new DefaultTableModel(fileColumnNames, 0){

        @Override
        public boolean isCellEditable(int row, int column){

            return column == 3;
        }
    };


    DefaultTableModel classTableModel = new DefaultTableModel(classColumnNames, 0);





    private void updateFileTable(){
        //updating JFileList with item from current path and .md5 path
        fileHandler.fillFilesPathList();
        fileTableModel = (DefaultTableModel) fileTable.getModel();
        fileTableModel.setRowCount(0);


        for(Path path:fileHandler.getFilesPath()){

            String text;
            if(Files.isDirectory(path)){
                text="Dir";
            } else if(path.toString().endsWith(".class")){
                text="Class";
            } else{
                text="File";
            }

            Object[] objs ={String.valueOf(text), String.valueOf(path.getFileName())};
            fileTableModel.addRow(objs);

        }
        fileTable.repaint();

        pathField.setText(fileHandler.getCurrentPath().toString());
    }

    private void updateClassTable(){


    }



    public MainWindow()
    {
        //JPanel cardPanel = new JPanel();
        setTitle("Aplikacja Ładowacza Klas");
        setSize(1000, 400);





        fileTable = new JTable(fileTableModel);
        classTable = new JTable(classTableModel);

        fileTable.getColumnModel().getColumn(0).setMaxWidth(40);



        pathField.setText(fileHandler.getCurrentPath().toString());
        pathField.setEditable(false);

        JPanel jp = new JPanel();
        updateFileTable();
        updateClassTable();

        fileTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt){
                JTable jtable = (JTable) evt.getSource();
                if(evt.getClickCount()==2){

                    fileHandler.clearFiles();
                    fileHandler.fillFilesList();

                    //after 2 clicking two times on item in jFile list change directory if possible
                    int index = jtable.rowAtPoint(evt.getPoint());


                    ElementInFileSystem clickedElement;
                    //dir
                    if(Files.isDirectory(fileHandler.getFilesPath().get(index))){

                        clickedElement = new DirElement(fileHandler.getFilesPath().get(index));
                        fileHandler.setCurrentPath(clickedElement.getFilePath());

                    } else if(fileHandler.getFilesPath().get(index).toString().endsWith(".class")){


                        //csv file
                        clickedElement = new JavaClassFile(fileHandler.getFilesPath().get(index));
                        JavaClassFile javaClassFile = (JavaClassFile)clickedElement;


                    } else {
                        clickedElement = new FileElement(fileHandler.getFilesPath().get(index));

                    }
                }
                updateFileTable();
                }
        });

        sp = new JScrollPane(fileTable);
        contentSp = new JScrollPane(classTable);

        //jtextArea for file content
        contentJtextArea = new JTextArea();
        contentJtextArea.setVisible(true);

        contentJtextArea.setPreferredSize(new Dimension(100, 100));

        //jtextArea for hash, from memory or disc
        infoJtextArea = new JTextArea();
        infoJtextArea.setVisible(true);


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
              updateFileTable();
            }
        });

        refreshButton.addActionListener(new ActionListener()
        {
            //refresh, checking checksums, deletions, additions
            public void actionPerformed(ActionEvent arg0)
            {
                updateFileTable();
            }
        });

        getContentPane().add(pathField, BorderLayout.NORTH);
        getContentPane().add(sp, BorderLayout.WEST);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        getContentPane().add(contentSp, BorderLayout.CENTER);

    }





}