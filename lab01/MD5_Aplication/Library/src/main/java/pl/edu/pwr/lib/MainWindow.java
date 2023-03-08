package pl.edu.pwr.lib;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.UIManager;

public class MainWindow extends JFrame {

    private CardLayout cl;
    FileHandler fileHandler = new FileHandler();
    JList<Object> jFileList = new JList<>();
    JTextField pathField = new JTextField();

    private void updateJfileList(){
        //updating JFileList with item from current path and .md5 path
        fileHandler.fillFilesList();
        jFileList.removeAll();
        jFileList.setListData(fileHandler.getFiles().toArray());
        fileHandler.createNewFileInfoFile();
        jFileList.repaint();
        pathField.setText(FileHandler.getCurrentPath());

    }

    public MainWindow()
    {
        JPanel cardPanel = new JPanel();
        setTitle("Aplikacja MD5");
        setSize(320, 400);

        pathField.setText(FileHandler.getCurrentPath());
        pathField.setEditable(false);

        cl = new CardLayout();
        cardPanel.setLayout(cl);
        JPanel jp = new JPanel();

        updateJfileList();
        jFileList.setListData(fileHandler.getFiles().toArray());
        jFileList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt){
                JList jFileList = (JList)evt.getSource();
                if(evt.getClickCount()==2){
                    //after 2 clicking two times on item in jFile list change directory if possible
                    int index = jFileList.locationToIndex(evt.getPoint());
                    fileHandler.childPath(fileHandler.getFiles().get(index).getFileName());
                    updateJfileList();
                }
            }
        });
        JScrollPane sp = new JScrollPane(jFileList);
        cardPanel.add(sp);

        //Buttons
        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Powrót");
        JButton refreshButton = new JButton("Odśwież");
        JButton deleteButton = new JButton("Usuń dane");

        buttonPanel.add(backButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(deleteButton);

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

        deleteButton.addActionListener(new ActionListener()
        {
            //delete all files from .md5
            public void actionPerformed(ActionEvent arg0)
            {
                UIManager.put("OptionPane.noButtonText", "Nie");
                UIManager.put("OptionPane.yesButtonText", "Tak");
            //confirm window for deletion
            if(JOptionPane.showConfirmDialog(null, "Czy napewno chcesz usunąć wszystkie informacje o statusie plików?","Ostrzeżenie",
                    JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                fileHandler.deleteMD5Directory();
                updateJfileList();
            }
            }
        });

        getContentPane().add(pathField, BorderLayout.NORTH);
        getContentPane().add(cardPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }
}