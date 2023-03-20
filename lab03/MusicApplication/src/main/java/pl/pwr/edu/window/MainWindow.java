package pl.pwr.edu.window;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.Locale;
import java.util.ResourceBundle;




public class MainWindow extends JFrame {

    CardLayout cardLayout = new CardLayout();
    JPanel cards = new JPanel(cardLayout); //panel that contains cards
    JPanel card1 = new QuestionPanel();
    JPanel card2 = new QuestionPanel();
    JPanel card3 = new QuestionPanel();

    JMenuBar menuBar;
    JMenuItem menuPl, menuEng;

    JButton newQuizButton = new JButton();
    JButton checkButton = new JButton();

    public enum Language{
        POLISH,
        ENGLISH
    }

    Language language = Language.POLISH;
    private ResourceBundle rb = ResourceBundle.getBundle("MessageBundle_PL", new Locale("pl", "PL"));

    public MainWindow()
    {

        setTextOnElements();
        setSize(600, 400);

        menuBar = new JMenuBar();
        menuPl = new JMenuItem(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(language==Language.ENGLISH){
                    rb = ResourceBundle.getBundle("MessageBundle_PL", new Locale("pl", "PL"));
                    language=Language.POLISH;
                    setTextOnElements();

                }

            }
        });

        menuPl.setText("PL");

        menuEng = new JMenuItem(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(language==Language.POLISH){
                    rb = ResourceBundle.getBundle("MessageBundle_EN", new Locale("en", "EN"));
                    language=Language.ENGLISH;
                    setTextOnElements();

                }
            }
        });
        menuEng.setText("ENG");


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

    private void setTextOnElements(){

        newQuizButton.setText(rb.getString("Reset"));
        checkButton.setText(rb.getString("Check"));
        setTitle(rb.getString("Title"));

        newQuizButton.repaint();
        checkButton.repaint();
    }


}