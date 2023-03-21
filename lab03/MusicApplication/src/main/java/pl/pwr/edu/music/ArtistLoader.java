package pl.pwr.edu.music;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class ArtistLoader {




    private ArrayList<ArtistFromFile> artistArrayList = new ArrayList<>(); //key=name, val=url

    public ArrayList<ArtistFromFile> getArtistArrayList() {
        return artistArrayList;
    }

    public ArtistLoader()  {

        InputStream is = getClass().getClassLoader().getResourceAsStream("Authors.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;

        while(true){
            try {
                if (!((line=reader.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            String[] result = line.split(",");
            String name =result[0];
            String url =result[1].replace("/n", "");

            artistArrayList.add(new ArtistFromFile(name, url));
        }
    }


    public void shuffleArtistArrayList(){

        Collections.shuffle(artistArrayList);
    }

}
