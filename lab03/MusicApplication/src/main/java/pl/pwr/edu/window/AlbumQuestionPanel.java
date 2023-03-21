package pl.pwr.edu.window;

import pl.pwr.edu.music.ArtistFromApi;
import pl.pwr.edu.music.ArtistLoader;

import javax.swing.*;
import java.util.ResourceBundle;

public class AlbumQuestionPanel extends QuestionPanel {

    public AlbumQuestionPanel(Bundle bundle, ArtistLoader artistLoader) {
        super(bundle, artistLoader);
    }

    @Override
    public void loadQuestion() {
        System.out.println("pytanie autora");
    }

    @Override
    public void setLanguage() {

    }


}
