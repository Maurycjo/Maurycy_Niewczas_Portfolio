package pl.pwr.edu.window;

import pl.pwr.edu.music.ArtistLoader;


public class AuthorQuestionPanel extends QuestionPanel {

        private String question;
        private String[] answers = new String[numOfAnswerRadioButtons];


        public AuthorQuestionPanel(Bundle bundle, ArtistLoader artistLoader) {

           super(bundle, artistLoader);
            loadQuestion();

        }

        @Override
        public void loadQuestion() {

            question = bundle.getRb().getString("AuthorQuestion");
            question=question.replace("...", currentArtist.getRandomSong());
            question = this.bundle.getRb().getString("AuthorQuestion");
            question=question.replace("...", currentArtist.getRandomSong());

            answers[randomCorrectNumber]=currentArtist.getArtistName();

            questionTextField.setText(question);
            int j=1;
            radioButtons[randomCorrectNumber].setText(currentArtist.getArtistName());
            for(int i=0;i<numOfAnswerRadioButtons;i++){

                if(i!=randomCorrectNumber){
                    radioButtons[i].setText(artistLoader.getArtistArrayList().get(j).getArtistName());
                }
                j++;
            }
        }

        @Override
        public void setLanguage(){
            question = bundle.getRb().getString("AuthorQuestion");
            question=question.replace("...", currentArtist.getRandomSong());
            question = this.bundle.getRb().getString("AuthorQuestion");
            question=question.replace("...", currentArtist.getRandomSong());
            questionTextField.setText(question);
        }

    }
