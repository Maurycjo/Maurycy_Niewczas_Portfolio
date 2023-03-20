package pl.pwr.edu.window;

import pl.pwr.edu.music.Artist;
import pl.pwr.edu.music.ArtistGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ResourceBundle;


    public class QuestionPanel extends JPanel {


        JTextField questionTextField ;
        final int numOfAnswerRadioButtons = 4;
        int correctAnswer=2;



        private String answerA="a", answerB="b", answerC="c", answerD="d";
        private String question;


        JRadioButton[] radioButtons;

        public QuestionPanel(ResourceBundle rb) {

            //question + album from api + ?

            setLayout(new BorderLayout());

            ArtistGenerator artistGenerator;
            try {
                artistGenerator=new ArtistGenerator();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


            Artist currentArtist = artistGenerator.getArtistArrayList().get(0);
            question = rb.getString("AuthorQuestion");
            question=question.replace("...", currentArtist.getPieceOfMusic().get(0));


            answerA = artistGenerator.getArtistArrayList().get(1).getName();
            answerB = artistGenerator.getArtistArrayList().get(2).getName();
            answerC = currentArtist.getName();
            answerD = artistGenerator.getArtistArrayList().get(3).getName();


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

            add(questionTextField, BorderLayout.NORTH); //dodajemy przycisk do panelu
            add(radioButtonPanel, BorderLayout.CENTER);

        }


        public boolean checkIfCorrectSelect(){
            return radioButtons[correctAnswer].isSelected();

        }


        public void setQuestionAndAnswer(){

        }


    }
