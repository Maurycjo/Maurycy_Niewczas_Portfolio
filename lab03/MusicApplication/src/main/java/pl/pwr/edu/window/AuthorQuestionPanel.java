package pl.pwr.edu.window;

import pl.pwr.edu.music.ArtistFromApi;
import pl.pwr.edu.music.ArtistLoader;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.ResourceBundle;


    public class AuthorQuestionPanel extends JPanel {


        JTextField questionTextField ;
        final int numOfAnswerRadioButtons = 4;

        int randomCorrectNumber;
        private String[] answers = new String[numOfAnswerRadioButtons];
        private String question;

        ArtistLoader artistLoader;
        ResourceBundle rb;
        JRadioButton[] radioButtons;

        public AuthorQuestionPanel(ResourceBundle rb, ArtistLoader artistLoader) {

            //question + album from api + ?
            this.artistLoader =artistLoader;
            this.rb=rb;

            setLayout(new BorderLayout());



            this.artistLoader.shuffleArtistArrayList();
            String artistName = artistLoader.getArtistArrayList().get(0).getArtistName();
            String url = this.artistLoader.getArtistArrayList().get(0).getUrl();
            ArtistFromApi currentArtist = new ArtistFromApi(artistName, url);
            loadQuestion(currentArtist);
            Random random = new Random();
            randomCorrectNumber = random.nextInt(numOfAnswerRadioButtons);

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
                radioButtonPanel.add(radioButtons[i]);
            }


            radioButtons[0].setSelected(true);

            add(questionTextField, BorderLayout.NORTH);
            add(radioButtonPanel, BorderLayout.CENTER);

        }


        public boolean checkIfCorrectSelect(){
            return radioButtons[randomCorrectNumber].isSelected();
        }

        public void loadQuestion(ArtistFromApi currentArtist){

            question = rb.getString("AuthorQuestion");
            question=question.replace("...", currentArtist.getRandomSong());

            answers[randomCorrectNumber]=currentArtist.getArtistName();
            question = this.rb.getString("AuthorQuestion");
            question=question.replace("...", currentArtist.getRandomSong());


            int j=1;
            for(int i=0;i<numOfAnswerRadioButtons;i++){

                if(i!=randomCorrectNumber){

                    answers[i]=artistLoader.getArtistArrayList().get(j).getArtistName();
                }
                j++;
            }

        }



    }
