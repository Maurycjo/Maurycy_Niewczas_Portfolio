package pl.edu.pwr.window;

import pl.edu.pwr.file.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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
    String[] classColumnNames ={"Nazwa klasy", "Status klasy", "Nazwa metody(Podaj)", "Wynik", "Status Metody"};

    JTable fileTable;
    JTable classTable;

    DefaultTableModel fileTableModel = new DefaultTableModel(fileColumnNames, 0){

        @Override
        public boolean isCellEditable(int row, int column){

            return column == 3;
        }
    };


    DefaultTableModel classTableModel = new DefaultTableModel(classColumnNames, 0);


    ArrayList<JavaClassFile> javaClassFileArrayList = new ArrayList<>();


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

        for(Path path:fileHandler.getFilesPath()){


            if(path.toString().endsWith(".class")){

                JavaClassFile javaClassFile = new JavaClassFile(path);


                String className = String.valueOf(path.getFileName());
                String classState;

                if (javaClassFile.isLoaded()){
                    classState = "Załadowana";
                } else{
                    classState="Niezaładowana";
                }

                Object[] objs ={className, classState, "", "", "status"};
                classTableModel.addRow(objs);
            }

        }
        classTable.repaint();



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


        JPanel leftButtonPanel = new JPanel();
        JPanel rightButtonPanel = new JPanel();
        JPanel centerButtonPanel = new JPanel();

        //Buttons
        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Powrót");
        JButton refreshButton = new JButton("Odśwież");
        JButton loadClassButton = new JButton("Załaduj");
        JButton unloadClassButton = new JButton("Wyładuj");
        JButton executeOperationButton = new JButton("Wykonaj metode");

//        buttonPanel.add(backButton);
//        buttonPanel.add(refreshButton);
//        buttonPanel.add(loadClassButton);
//        buttonPanel.add(unloadClassButton);
//        buttonPanel.add(executeOperationButton);

        leftButtonPanel.add(backButton);
        leftButtonPanel.add(refreshButton);
        rightButtonPanel.add(loadClassButton);
        rightButtonPanel.add(unloadClassButton);
        rightButtonPanel.add(executeOperationButton);

        buttonPanel.add(leftButtonPanel, FlowLayout.LEFT);
        buttonPanel.add(rightButtonPanel, FlowLayout.CENTER);




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

        loadClassButton.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent arg0)
            {

            }
        });

        unloadClassButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {

            }
        });

        executeOperationButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {

            }
        });

        getContentPane().add(pathField, BorderLayout.NORTH);
        getContentPane().add(sp, BorderLayout.WEST);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        getContentPane().add(contentSp, BorderLayout.CENTER);

    }





}