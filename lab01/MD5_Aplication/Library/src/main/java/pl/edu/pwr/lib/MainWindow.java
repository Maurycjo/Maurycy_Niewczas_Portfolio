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
    FileHandler fHander = new FileHandler();
    public MainWindow()
    {
        fHander.fillFilesList();
        JPanel cardPanel = new JPanel();

        setTitle("Aplikacja MD5");
        setSize(320, 400);

        JTextField pathField = new JTextField(fHander.pathName());

        cl = new CardLayout();
        cardPanel.setLayout(cl);
        JPanel jp = new JPanel();

        JScrollPane sp = new JScrollPane(new JList(fHander.getFiles().toArray()));

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

            }
        });

        getContentPane().add(pathField, BorderLayout.NORTH);
        getContentPane().add(cardPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }
}