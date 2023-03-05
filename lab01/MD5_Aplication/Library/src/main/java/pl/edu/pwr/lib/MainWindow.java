package pl.edu.pwr.lib;

// Java program to show Example of CardLayout.
// in java. Importing different Package.
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// class extends JFrame
public class MainWindow extends JFrame {


    private CardLayout cl;
    FileHandler fileHandler = new FileHandler();
    JList jFileList = new JList();

    private void updateJfileList(){

        fileHandler.fillFilesList();
        jFileList.removeAll();
        jFileList.setListData(fileHandler.getFiles().toArray());
        jFileList.repaint();
    }

    public MainWindow()
    {
        //fileHandler.fillFilesList();
        JPanel cardPanel = new JPanel();
        setTitle("Aplikacja MD5");
        setSize(320, 400);

        JTextField pathField = new JTextField(fileHandler.getCurrentPath());

        cl = new CardLayout();
        cardPanel.setLayout(cl);
        JPanel jp = new JPanel();

        fileHandler.fillFilesList();
        jFileList.setListData(fileHandler.getFiles().toArray());
        JScrollPane sp = new JScrollPane(jFileList);
        //JScrollPane sp = new JScrollPane(new JList(fileHandler.getFiles().toArray()));

        cardPanel.add(sp);


        JPanel buttonPanel = new JPanel();
        JButton firstBtn = new JButton("First");
        JButton nextBtn = new JButton("Next");
        JButton previousBtn = new JButton("Previous");
        JButton lastBtn = new JButton("Last");

        buttonPanel.add(firstBtn);
        buttonPanel.add(nextBtn);
        buttonPanel.add(previousBtn);
        buttonPanel.add(lastBtn);

        // add ActionListeners
        firstBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                updateJfileList();

                System.out.println("first");
            }

        });

        lastBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                System.out.println("last");
            }
        });

        nextBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                System.out.println("next");
            }
        });

        previousBtn.addActionListener(new ActionListener()
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