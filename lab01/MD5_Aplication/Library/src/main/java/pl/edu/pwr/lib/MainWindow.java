package pl.edu.pwr.lib;

// Java program to show Example of CardLayout.
// in java. Importing different Package.
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.*;

// class extends JFrame
public class MainWindow extends JFrame {


    private CardLayout cl;
    FileHandler fileHandler = new FileHandler();
    JList jFileList = new JList();
    JTextField pathField = new JTextField();

    private void updateJfileList(){

        fileHandler.fillFilesList();
        jFileList.removeAll();
        jFileList.setListData(fileHandler.getFiles().toArray());
        fileHandler.createNewFileInfoFile();
        jFileList.repaint();
        pathField.setText(FileHandler.getCurrentPath());

    }

    public MainWindow()
    {
        //fileHandler.fillFilesList();
        JPanel cardPanel = new JPanel();
        setTitle("Aplikacja MD5");
        setSize(320, 400);

        pathField.setText(FileHandler.getCurrentPath());
        pathField.setEditable(false);

        cl = new CardLayout();
        cardPanel.setLayout(cl);
        JPanel jp = new JPanel();

        fileHandler.fillFilesList();
        jFileList.setListData(fileHandler.getFiles().toArray());
        jFileList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt){
                JList jFileList = (JList)evt.getSource();
                if(evt.getClickCount()==2){
                    int index = jFileList.locationToIndex(evt.getPoint());
                    fileHandler.childPath(fileHandler.getFiles().get(index).getFileName());
                    updateJfileList();
                }
            }
        });
        JScrollPane sp = new JScrollPane(jFileList);
        //JScrollPane sp = new JScrollPane(new JList(fileHandler.getFiles().toArray()));

        cardPanel.add(sp);


        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Powrót");
        JButton refreshButton = new JButton("Odśwież");
        JButton deleteButton = new JButton("Usuń dane");
        //JButton lastBtn = new JButton("Last");

        buttonPanel.add(backButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(deleteButton);
        //buttonPanel.add(lastBtn);

        // add ActionListeners
        backButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
            fileHandler.parentPath();
            updateJfileList();


            }

        });

        refreshButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                updateJfileList();
            }
        });

        deleteButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {

                System.out.println("prev");
                FileHandler.setCurrentPath("/home/mniewczas/Desktop/md5/");
                updateJfileList();

            }
        });

        getContentPane().add(pathField, BorderLayout.NORTH);
        getContentPane().add(cardPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }
}