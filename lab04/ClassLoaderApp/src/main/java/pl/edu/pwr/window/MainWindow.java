package pl.edu.pwr.window;

import pl.edu.pwr.file.*;
import pl.edu.pwr.processing.MyStatusListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;



public class MainWindow extends JFrame {


    FileHandler fileHandler = new FileHandler();

    JTextField pathField = new JTextField();
    private final JTextArea contentJtextArea;
    private final JTextArea infoJtextArea;
    JScrollPane sp ;
    JScrollPane contentSp;
    MyStatusListener mst;

    final int CLASS_NAME_IDX = 0, CLASS_STATE_IDX = 1, CLASS_ARG_IDX = 2, CLASS_RESULT_IDX=3, PROGRESS_IDX=4,  METHOD_INFO_IDX=5;
    final int FILE_COLUMN_1_WIDTH = 80, FILE_COLUMN_2_WIDTH=200;


    HashMap<Path, JavaClassFile> loadedClassHashMap = new HashMap<>();

    String[] fileColumnNames = {"Typ", "Nazwa pliku"};
    String[] classColumnNames ={"Nazwa klasy", "Status klasy", "Argumenty metody", "Wynik", "Progress", "o Metodzie"};

    JTable fileTable;
    public JTable classTable;

    DefaultTableModel fileTableModel = new DefaultTableModel(fileColumnNames, 0){

        @Override
        public boolean isCellEditable(int row, int column){

            return column == 3;
        }
    };


    DefaultTableModel classTableModel = new DefaultTableModel(classColumnNames, 0){

        @Override
        public  boolean isCellEditable(int row, int column){
            return column == CLASS_ARG_IDX;
        }
    };

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

        fileHandler.fillClassPathList();
        classTableModel = (DefaultTableModel) classTable.getModel();
        classTableModel.setRowCount(0);

        for(Path path:fileHandler.getFilesPath()){

            if(path.toString().endsWith(".class")){

                if(loadedClassHashMap.get(path)==null){

                    String className = String.valueOf(path.getFileName());

                    Object[] objs = {className, "Niezaładowana", "", "", "Brak"};
                    classTableModel.addRow(objs);
                } else{

                    JavaClassFile currentClassFile = loadedClassHashMap.get(path);

                    Object[] objs ={currentClassFile.getFileName(), currentClassFile.getClassState(),
                                    currentClassFile.getLastTask(), currentClassFile.getLastResult(),
                                    currentClassFile.getMethodState(), currentClassFile.getInfoMethod()};
                    classTableModel.addRow(objs);
                }
            }
        }
        classTable.repaint();
    }

    public MainWindow()
    {
        //JPanel cardPanel = new JPanel();
        setTitle("Aplikacja Ładowacza Klas");
        setSize(1300, 500);

        fileTable = new JTable(fileTableModel);
        classTable = new JTable(classTableModel);

        fileTable.setFont(new Font("Arial", Font.PLAIN, 14));
        classTable.setFont(new Font("Arial", Font.PLAIN, 14));

        fileTable.getColumnModel().getColumn(0).setMaxWidth(FILE_COLUMN_1_WIDTH);
        fileTable.getColumnModel().getColumn(1).setMaxWidth(FILE_COLUMN_2_WIDTH);
        classTable.getColumnModel().getColumn(5).setMinWidth(170);

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
                   // fileHandler.fillFilesList();

                    //after 2 clicking two times on item in jFile list change directory if possible
                    int index = jtable.rowAtPoint(evt.getPoint());

                    ElementInFileSystem clickedElement;
                    //dir
                    if(Files.isDirectory(fileHandler.getFilesPath().get(index))){

                        clickedElement = new DirElement(fileHandler.getFilesPath().get(index));
                        fileHandler.setCurrentPath(clickedElement.getFilePath());
                    }
                }
                updateFileTable();
                updateClassTable();
                }
        });

        sp = new JScrollPane(fileTable);
        sp.setPreferredSize(new Dimension(FILE_COLUMN_1_WIDTH+FILE_COLUMN_2_WIDTH, 100));
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
        JButton showResultButton = new JButton("Pokaz wynik");

        leftButtonPanel.add(backButton);
        leftButtonPanel.add(refreshButton);
        rightButtonPanel.add(loadClassButton);
        rightButtonPanel.add(unloadClassButton);
        rightButtonPanel.add(executeOperationButton);
        rightButtonPanel.add(showResultButton);

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
              updateClassTable();
            }
        });

        refreshButton.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent arg0)
            {

                updateFileTable();
                //updateClassTable();

                System.out.println(loadedClassHashMap.size());

            }
        });

        loadClassButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                int selectedRow = classTable.getSelectedRow();

                Path selectedPath = fileHandler.getClassPath().get(selectedRow).toAbsolutePath();


                if(loadedClassHashMap.get(selectedPath)==null){
                    JavaClassFile javaClassFile = new JavaClassFile(selectedPath);
                    javaClassFile.loadClass();
                    loadedClassHashMap.put(selectedPath, javaClassFile);
                    classTable.setValueAt(javaClassFile.getClassState(), selectedRow, CLASS_STATE_IDX);
                    classTable.setValueAt(javaClassFile.getInfoMethod(), selectedRow, METHOD_INFO_IDX);
                }

            }
        });

        unloadClassButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                int selectedRow = classTable.getSelectedRow();
                Path selectedPath = fileHandler.getClassPath().get(selectedRow).toAbsolutePath();

                if(loadedClassHashMap.get(selectedPath)==null){
                    return;
                }
                else{
                    loadedClassHashMap.remove(selectedPath);
                    System.gc();
                }
                classTable.setValueAt("Niezaładowana", selectedRow, CLASS_STATE_IDX);
            }
        });

        showResultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedRow = classTable.getSelectedRow();
                Path selectedPath = fileHandler.getClassPath().get(selectedRow).toAbsolutePath();

                if(loadedClassHashMap.get(selectedPath)==null){
                    return;
                }
                else{
                    JavaClassFile javaClassFile = loadedClassHashMap.get(selectedPath);
                    classTable.setValueAt(javaClassFile.getResultMethod(), selectedRow, CLASS_RESULT_IDX);
                }
            }
        });

        executeOperationButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                int selectedRow = classTable.getSelectedRow();
                Path selectedPath = fileHandler.getClassPath().get(selectedRow).toAbsolutePath();

                if(loadedClassHashMap.get(selectedPath)==null){
                    classTable.setValueAt("Załaduj klasę",selectedRow, METHOD_INFO_IDX);
                    classTable.setValueAt("", selectedRow, CLASS_RESULT_IDX);

                }else{

                    mst = new MyStatusListener(classTable, selectedRow, PROGRESS_IDX);
                    JavaClassFile javaClassFile = loadedClassHashMap.get(selectedPath);
                    javaClassFile.setMst(mst);
                    javaClassFile.submitTask((String) classTable.getValueAt(selectedRow, CLASS_ARG_IDX));

                }
            }
        });

        getContentPane().add(pathField, BorderLayout.NORTH);
        getContentPane().add(sp, BorderLayout.WEST);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        getContentPane().add(contentSp, BorderLayout.CENTER);
    }
}