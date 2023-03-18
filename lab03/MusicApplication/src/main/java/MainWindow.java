
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
    JPanel card1 = new JPanel();
    JPanel card2 = new JPanel();
    JPanel card3 = new JPanel();

    public MainWindow()
    {
        //JPanel cardPanel = new JPanel();
        setTitle("Aplikacja Muzyczna");
        setSize(600, 400);


        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        JTextField testTextField1 = new JTextField("1");
        JTextField testTextField2 = new JTextField("2");
        JTextField testTextField3 = new JTextField("3");

        card1.add(testTextField1);
        card2.add(testTextField2);
        card3.add(testTextField3);



        //Buttons
        JPanel buttonPanel = new JPanel();
        JButton previousButton = new JButton("Poprzednie");
        JButton nextButton = new JButton("Następne");
        JButton acceptButton = new JButton("Sprawdz");

        buttonPanel.add(previousButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(acceptButton);

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

        acceptButton.addActionListener(new ActionListener()
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