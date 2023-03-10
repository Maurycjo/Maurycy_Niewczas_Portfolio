package pl.edu.pwr.window;

import pl.edu.pwr.file.DirElement;
import pl.edu.pwr.file.ElementInFileSystem;
import pl.edu.pwr.file.FileHandler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class MainWindow extends JFrame {

    private CardLayout cl;
    FileHandler fileHandler = new FileHandler();
    JList<Object> jFileList = new JList<>();
    JTextField pathField = new JTextField();
    JScrollPane sp ;
    DefaultListModel listModel = new DefaultListModel();

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
        pathField.setText(FileHandler.getCurrentPath());

    }

    public MainWindow()
    {
        //JPanel cardPanel = new JPanel();
        setTitle("Aplikacja WeakReferences");
        setSize(800, 400);

        pathField.setText(FileHandler.getCurrentPath());
        pathField.setEditable(false);

        JPanel jp = new JPanel();

        updateJfileList();
        jFileList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt){
                JList jFileList = (JList)evt.getSource();
                if(evt.getClickCount()==2){
                    //after 2 clicking two times on item in jFile list change directory if possible
                    int index = jFileList.locationToIndex(evt.getPoint());
                    fileHandler.getFiles().get(index).contentAfterClicked();
                    if(fileHandler.getFiles().get(index) instanceof DirElement){
                        fileHandler.childPath(fileHandler.getFiles().get(index).getFileName());
                    }
                    updateJfileList();
                }
            }
        });
        sp = new JScrollPane(jFileList);

        //jtextArea for file content
        JTextArea contentJtextArea = new JTextArea();
        contentJtextArea.setVisible(true);
        contentJtextArea.setText("hello content will be there");
        contentJtextArea.setPreferredSize(new Dimension(100, 100));

        //jtextArea for hash, from memory or disc
        JTextArea infoJtextArea = new JTextArea();
        infoJtextArea.setVisible(true);
        infoJtextArea.setText("From disc\nHash: 1234HashMd5");

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
        getContentPane().add(contentJtextArea, BorderLayout.CENTER);
        getContentPane().add(infoJtextArea, BorderLayout.EAST);

    }
}