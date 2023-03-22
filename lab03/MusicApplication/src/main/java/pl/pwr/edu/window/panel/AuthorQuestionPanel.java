package pl.pwr.edu.window.panel;

import pl.pwr.edu.music.ArtistLoader;
import pl.pwr.edu.window.Bundle;


public class AuthorQuestionPanel extends QuestionPanel {

        private String question;
        private String[] answers = new String[numOfAnswerRadioButtons];
        private String randomSong;

        public AuthorQuestionPanel(Bundle bundle, ArtistLoader artistLoader) {

           super(bundle, artistLoader);
            loadQuestion();
        }

        @Override
        public void loadQuestion() {

            randomSong= currentArtist.getRandomSong();

            question = this.bundle.getRb().getString("AuthorQuestion");
            question=question.replace("...", randomSong);

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
    public String getAcceptedMessage(){

        String message = bundle.getRb().getString("AcceptedAuthorAnswer");
        message=message.replace("...", randomSong);
        message=message.replace(",,,", currentArtist.getArtistName());

        return message;
    }

    @Override
    public String getRejectedMessage(){

        String message= bundle.getRb().getString("RejectedAuthorAnswer");
        message=message.replace("...", randomSong);
        message=message.replace(",,,", currentArtist.getArtistName());
        return message;
    }

        @Override
        public void setLanguage(){

            question = this.bundle.getRb().getString("AuthorQuestion");
            question=question.replace("...", randomSong);
            questionTextField.setText(question);
        }

    }
