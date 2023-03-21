package pl.pwr.edu.window;

import pl.pwr.edu.music.ArtistFromApi;
import pl.pwr.edu.music.ArtistLoader;

import javax.swing.*;
import java.util.ResourceBundle;

public class AlbumQuestionPanel extends AuthorQuestionPanel {

    public AlbumQuestionPanel(ResourceBundle rb, ArtistLoader artistLoader) {
        super(rb, artistLoader);
    }


    @Override
    public void loadQuestion(ArtistFromApi currentArtist){

        System.out.println("pytanie autora");
    }


}
