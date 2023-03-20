package pl.pwr.edu.window;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MainWindow extends JFrame {

    CardLayout cardLayout = new CardLayout();
    JPanel cards = new JPanel(cardLayout); //panel that contains cards
    JPanel card1 = new QuestionPanel();
    JPanel card2 = new QuestionPanel();
    JPanel card3 = new QuestionPanel();

    JMenuBar menuBar;
    JMenu menuPl, menuEng;
    JMenuItem menuItem1, menuItem2;
    public MainWindow()
    {
        //JPanel cardPanel = new JPanel();
        setTitle("Aplikacja Muzyczna");
        setSize(600, 400);

        menuBar = new JMenuBar();
        menuPl = new JMenu("PL");
        menuEng = new JMenu("ENG");
        menuBar.add(menuPl);
        menuBar.add(menuEng);
        setJMenuBar(menuBar);

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        JTextField testTextField1 = new JTextField("1");
        JTextField testTextField2 = new JTextField("2");
        JTextField testTextField3 = new JTextField("3");


        //Buttons
        JPanel buttonPanel = new JPanel();

        JButton newQuizButton = new JButton("Od nowa");
        JButton checkButton = new JButton("Sprawdz");

        buttonPanel.add(newQuizButton);
        buttonPanel.add(checkButton);


        // add ActionListeners

        checkButton.addActionListener(new ActionListener()
        {
            //refresh, checking checksums, deletions, additions
            public void actionPerformed(ActionEvent arg0)
            {
                cardLayout.next(cards);
            }
        });

        newQuizButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {

            }
        });

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        getContentPane().add(cards, BorderLayout.CENTER);

    }

}