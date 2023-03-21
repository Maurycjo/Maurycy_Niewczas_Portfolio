package pl.pwr.edu.window;

import pl.pwr.edu.music.Artist;
import pl.pwr.edu.music.ArtistLoaderFromFile;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;


    public class QuestionPanel extends JPanel {


        JTextField questionTextField ;
        final int numOfAnswerRadioButtons = 4;
        int correctAnswer=2;


        private String[] answers = new String[numOfAnswerRadioButtons];
        private String question;


        JRadioButton[] radioButtons;

        public QuestionPanel(ResourceBundle rb, ArtistLoaderFromFile artistLoaderFromFile) {

            //question + album from api + ?

            setLayout(new BorderLayout());


            artistLoaderFromFile.shuffleArtistArrayList();


            Artist currentArtist = artistLoaderFromFile.getArtistArrayList().get(0);
            currentArtist.getArtistInfoFromApi();


            question = rb.getString("AuthorQuestion");
            question=question.replace("...", currentArtist.getRandomSong());

            Random random = new Random();
            int randomCorrectNumber = random.nextInt(numOfAnswerRadioButtons);

            answers[randomCorrectNumber]=currentArtist.getArtistName();

            int j=1;
            for(int i=0;i<numOfAnswerRadioButtons;i++){

                if(i!=randomCorrectNumber){

                    answers[i]=artistLoaderFromFile.getArtistArrayList().get(j).getArtistName();
                }
                j++;
            }

            questionTextField = new JTextField(question); //pytanie
            add(questionTextField);
            questionTextField.setEditable(false);
            questionTextField.setFont(new Font("Arial", 10, 20 ));


            JPanel radioButtonPanel = new JPanel(new GridLayout(numOfAnswerRadioButtons, 1));
            radioButtons = new JRadioButton[numOfAnswerRadioButtons];
            final ButtonGroup group = new ButtonGroup();
            JButton checkButton = null;


            for(int i=0;i<numOfAnswerRadioButtons;i++){

                radioButtons[i] = new JRadioButton(answers[i]);
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
