package pl.edu.pwr.lib;

// Java program to show Example of CardLayout.
// in java. Importing different Package.
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// class extends JFrame
public class MainWindow extends JFrame {


    // Declaration of objects
    // of CardLayout class.
    private CardLayout cl;
    FileHandler fHander = new FileHandler();
    public MainWindow()
    {
        fHander.fillFilesList();

        setTitle("Aplikacja MD5");
        setSize(320, 200);
        JPanel cardPanel = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        cl = new CardLayout();
        cardPanel.setLayout(cl);
        JPanel jp = new JPanel();
        cardPanel.add(new JScrollPane( new JList(fHander.getFiles().toArray())));

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

        getContentPane().add(cardPanel, BorderLayout.NORTH);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }
}