package pl.pwr.edu.window;

import pl.pwr.edu.music.Artist;
import pl.pwr.edu.music.ArtistGenerator;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        MainWindow window = new MainWindow();
        window.setVisible(true);
        window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
        ArtistGenerator artistGenerator;

        try {
            artistGenerator = new ArtistGenerator();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        for(Artist artist:artistGenerator.getArtistArrayList()){
            artist.displayInfo();
        }

    }
}
