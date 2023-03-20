package pl.pwr.edu.window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


    public class QuestionPanel extends JPanel {


        JTextField questionTextField ;
        final int numOfAnswerRadioButtons = 4;
        int correctAnswer;



        private String answerA="a", answerB="b", answerC="c", answerD="d";
        private String question="Question";


        JRadioButton[] radioButtons;

        public QuestionPanel() {

            //question + album from api + ?

            setLayout(new BorderLayout());

            questionTextField = new JTextField(question); //pytanie
            add(questionTextField);
            questionTextField.setEditable(false);
            questionTextField.setFont(new Font("Arial", 10, 20 ));


            JPanel radioButtonPanel = new JPanel(new GridLayout(numOfAnswerRadioButtons, 1));
            radioButtons = new JRadioButton[numOfAnswerRadioButtons];
            final ButtonGroup group = new ButtonGroup();
            JButton checkButton = null;

            radioButtons[0] = new JRadioButton(answerA);
            radioButtons[1] = new JRadioButton(answerB);
            radioButtons[2] = new JRadioButton(answerC);
            radioButtons[3] = new JRadioButton(answerD);

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


        public void setQuestionAndAnswer(){

        }


    }
