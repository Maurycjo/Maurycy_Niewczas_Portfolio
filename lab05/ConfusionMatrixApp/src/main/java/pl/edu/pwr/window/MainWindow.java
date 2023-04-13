package pl.edu.pwr.window;

import pl.edu.pwr.file.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;




public class MainWindow extends JFrame {


    FileHandler fileHandler = new FileHandler();

    private final JTextArea contentJtextArea;
    private final JTextArea infoJtextArea;
    JScrollPane sp ;
    JScrollPane contentSp;
    final int FILE_COLUMN_1_WIDTH = 80, FILE_COLUMN_2_WIDTH=200;


    String[] fileColumnNames = {"kappa", "wspolczynni1"};
    String[] classColumnNames ={"1", "2", "3", "4", "5", "6"};

    JTable fileTable;
    public JTable classTable;

    DefaultTableModel fileTableModel = new DefaultTableModel(fileColumnNames, 0);
    DefaultTableModel classTableModel= new DefaultTableModel(classColumnNames, 0);



    private void updateFileTable(){
    }

    private void updateClassTable(){

        fileHandler.fillClassPathList();
        //classTableModel = (DefaultTableModel) classTable.getModel();
        //classTableModel.setRowCount(0);

        for(Path path:fileHandler.getFilesPath()){


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


        JPanel jp = new JPanel();
        updateFileTable();
        updateClassTable();



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

        //Buttons
        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Powrót");
        JButton refreshButton = new JButton("Odśwież");
        JButton loadClassButton = new JButton("Załaduj");
        JButton unloadClassButton = new JButton("Wyładuj");

        buttonPanel.add(backButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(loadClassButton);
        buttonPanel.add(unloadClassButton);


        // add ActionListeners
        backButton.addActionListener(new ActionListener()
        {
            //go 1 level up in directory
            public void actionPerformed(ActionEvent arg0)
            {

            }
        });

        refreshButton.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent arg0)
            {

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

        getContentPane().add(sp, BorderLayout.WEST);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        getContentPane().add(contentSp, BorderLayout.CENTER);
    }
}