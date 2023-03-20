package pl.pwr.edu.music;

import java.util.ArrayList;

public class Artist {

    private String name;
    private ArrayList<String> pieceOfMusic;
    private String artistId;

    private ArrayList<String> albumList = new ArrayList<>();

    public String getArtistId() {
        return artistId;
    }

    public ArrayList<String> getAlbumList() {
        return albumList;
    }

    public void addAlbum(String album){
        albumList.add(album);
    }


    public Artist(String artistId, String name, ArrayList<String> pieceOfMusic) {
        this.artistId=artistId;
        this.name = name;
        this.pieceOfMusic = pieceOfMusic;
    }

    public void displayInfo(){
        System.out.println(artistId);
        System.out.println(name);
        System.out.println(pieceOfMusic);
        System.out.println();
    }


}
