package pl.pwr.edu.music;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Random;

public class Artist {

    private String rapidApiKey = "332cc35608msh79f3d6bc5480ec1p1b7f2ejsncfd8b8ad9598";
    private String rapidApiHost ="shazam.p.rapidapi.com";
    private String artistId;
    private String artistName;

    private ArrayList<String> pieceOfMusic=new ArrayList<>();
    private String url;

    public ArrayList<String> getPieceOfMusic() {
        return pieceOfMusic;
    }

    public String getArtistName() {
        return artistName;
    }


    public Artist(String artistName, String url) {

        this.artistName=artistName;
        this.url=url;


    }
    public void getArtistInfoFromApi(){


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("X-RapidAPI-Key", rapidApiKey)
                .header("X-RapidAPI-Host", rapidApiHost)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);

        JsonArray hits = jsonObject.getAsJsonObject("tracks").getAsJsonArray("hits");

        for (JsonElement hit : hits) {
            String title = hit.getAsJsonObject().getAsJsonObject("track").get("title").getAsString();
            pieceOfMusic.add(title);
        }


        String artistId = jsonObject.getAsJsonObject("artists")
                .getAsJsonArray("hits")
                .get(0)
                .getAsJsonObject()
                .getAsJsonObject("artist")
                .get("adamid")
                .getAsString();

        this.artistId=artistId;

    }

    public String getRandomSong(){

        Random rand = new Random();
        int randNumber = rand.nextInt(pieceOfMusic.size());
        return pieceOfMusic.get(randNumber);
    }


    public void displayInfo(){
        System.out.println(artistId);
        System.out.println(artistName);
        System.out.println(pieceOfMusic);
        System.out.println();
    }


}
