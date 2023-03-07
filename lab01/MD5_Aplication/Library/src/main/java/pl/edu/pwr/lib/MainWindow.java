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
import javax.swing.UIManager;

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

        updateJfileList();
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

        buttonPanel.add(backButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(deleteButton);

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
                UIManager.put("OptionPane.noButtonText", "Nie");
                UIManager.put("OptionPane.yesButtonText", "Tak");

            if(JOptionPane.showConfirmDialog(null, "Czy napewno chcesz usunąć wszystkie informacje o statusie plików?","WARNING",
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