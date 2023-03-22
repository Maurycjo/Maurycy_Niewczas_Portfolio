package pl.pwr.edu.window;

import pl.pwr.edu.music.ArtistFromApi;
import pl.pwr.edu.music.ArtistLoader;

import javax.swing.*;
import java.util.*;

public class AlbumQuestionPanel extends QuestionPanel {

    private String question;


    public AlbumQuestionPanel(Bundle bundle, ArtistLoader artistLoader) {
        super(bundle, artistLoader);
        loadQuestion();
    }

    @Override
    public void loadQuestion() {

        question=this.bundle.getRb().getString("AlbumQuestion");
        question=question.replace("...", currentArtist.getArtistName());

        questionTextField.setText(question);

        HashSet<Integer> numberSet = new HashSet<>();

        numberSet.add(currentArtist.getHowManyAlbumsAndSinglies());

        Random random = new Random();


        while(numberSet.size()!=numOfAnswerRadioButtons){
            numberSet.add(random.nextInt((currentArtist.getHowManyAlbumsAndSinglies())*2));

        }

        numberSet.remove(currentArtist.getHowManyAlbumsAndSinglies());

        Object[] array =numberSet.toArray();


        radioButtons[randomCorrectNumber].setText(String.valueOf(currentArtist.getHowManyAlbumsAndSinglies()));

        int j=0;
        for(int i=0;i<numOfAnswerRadioButtons;i++){

            if(i!=randomCorrectNumber){
                radioButtons[i].setText(array[j].toString());
                j++;
            }
        }
    }

    public String getAcceptedMessage(){

        String message = bundle.getRb().getString("AcceptedAlbumAnswer");
        message=message.replace("...", currentArtist.getArtistName());
        message=message.replace(",,,", Integer.toString(currentArtist.getHowManyAlbumsAndSinglies()));

        return message;
    }

    public String getRejectedMessage(){
        return bundle.getRb().getString("RejectedAlbumAnswer");
    }

    @Override
    public void setLanguage() {
        question=this.bundle.getRb().getString("AlbumQuestion");
        question=question.replace("...", currentArtist.getArtistName());
        questionTextField.setText(question);

    }


}
