package pl.pwr.edu.window;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;


    public class QuestionPanel extends JPanel {


        JTextField questionTextField = new JTextField( "Kto"); //pytanie
        final int numOfAnswerRadioButtons = 4;

        JRadioButton[] radioButtons;

        public QuestionPanel() {

            setLayout(new BorderLayout());
            add(questionTextField);
            questionTextField.setEditable(false);

            JPanel radioButtonPanel = new JPanel(new GridLayout(numOfAnswerRadioButtons, 1));
            radioButtons = new JRadioButton[numOfAnswerRadioButtons];
            final ButtonGroup group = new ButtonGroup();
            JButton checkButton = null;

            radioButtons[0] = new JRadioButton("odp a");
            radioButtons[1] = new JRadioButton("odp b");
            radioButtons[2] = new JRadioButton("odp c");
            radioButtons[3] = new JRadioButton("odp d");

            for (int i = 0; i < numOfAnswerRadioButtons; i++) {
                group.add(radioButtons[i]);
                radioButtonPanel.add(radioButtons[i]); //dodajemy radiobutton do panelu
            }
            radioButtons[0].setSelected(true);

            checkButton = new JButton("Sprawdź");

            checkButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                }
            });
            add(questionTextField, BorderLayout.NORTH); //dodajemy przycisk do panelu
            add(radioButtonPanel, BorderLayout.CENTER);
            add(checkButton, BorderLayout.SOUTH);

        }


    }
