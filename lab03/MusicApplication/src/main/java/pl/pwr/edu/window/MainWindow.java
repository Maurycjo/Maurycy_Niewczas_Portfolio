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
    JMenu menu, submenu;
    JMenuItem menuItem1, menuItem2;
    public MainWindow()
    {
        //JPanel cardPanel = new JPanel();
        setTitle("Aplikacja Muzyczna");
        setSize(600, 400);

        menuBar = new JMenuBar();
        menu = new JMenu("Język/Language");
        menuBar.add(menu);

        menuItem1 = new JMenuItem("Polski|Polish");
        menuItem2 = new JMenuItem("Angielski|English");

        menu.add(menuItem1);
        menu.add(menuItem2);

        setJMenuBar(menuBar);

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        JTextField testTextField1 = new JTextField("1");
        JTextField testTextField2 = new JTextField("2");
        JTextField testTextField3 = new JTextField("3");


        //Buttons
        JPanel buttonPanel = new JPanel();
        JButton previousButton = new JButton("Poprzednie");
        JButton nextButton = new JButton("Następne");
        JButton newQuestionButton = new JButton("Nowe Pytania");


        buttonPanel.add(previousButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(newQuestionButton);




        // add ActionListeners
        previousButton.addActionListener(new ActionListener()
        {
            //go 1 level up in directory
            public void actionPerformed(ActionEvent arg0)
            {
                cardLayout.previous(cards);
            }
        });

        nextButton.addActionListener(new ActionListener()
        {
            //refresh, checking checksums, deletions, additions
            public void actionPerformed(ActionEvent arg0)
            {
                cardLayout.next(cards);
            }
        });

        newQuestionButton.addActionListener(new ActionListener()
        {
            //refresh, checking checksums, deletions, additions
            public void actionPerformed(ActionEvent arg0)
            {

            }
        });

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        getContentPane().add(cards, BorderLayout.CENTER);

    }

}