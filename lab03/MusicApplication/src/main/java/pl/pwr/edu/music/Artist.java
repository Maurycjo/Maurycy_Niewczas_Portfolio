package pl.pwr.edu.music;

import java.util.ArrayList;

public class Artist {

    private String name;
    private ArrayList<String> pieceOfMusic;

    public Artist(String name, ArrayList<String> pieceOfMusic) {
        this.name = name;
        this.pieceOfMusic = pieceOfMusic;
    }

    public void displayInfo(){
        System.out.println(name);
        System.out.println(pieceOfMusic);
        System.out.println();
    }


}
