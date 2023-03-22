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

public class ArtistFromApi extends ArtistFromFile{

    private String artistId;

    RapidApi rapidApi=new RapidApi();
    private int howManyAlbumsAndSinglies;

    private ArrayList<String> pieceOfMusic=new ArrayList<>();
    private ArrayList<String> pieceOfMusicKeys=new ArrayList<>();


    public ArrayList<String> getPieceOfMusic() {
        return pieceOfMusic;
    }


    public int getHowManyAlbumsAndSinglies() {
        return howManyAlbumsAndSinglies;
    }

    public ArtistFromApi(String artistName, String url) {

        super(artistName, url);
        getArtistInfoFromApi();
        loadNumbersOfAlbums();

    }


    public void getArtistInfoFromApi(){

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(getUrl()))
                .header("X-RapidAPI-Key", rapidApi.getRapidApiKey())
                .header("X-RapidAPI-Host", rapidApi.getRapidApiHost())
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
            String key = hit.getAsJsonObject().getAsJsonObject("track").get("key").getAsString();
            pieceOfMusic.add(title);
            pieceOfMusicKeys.add(key);
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


    public void loadNumbersOfAlbums(){



        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://shazam.p.rapidapi.com/artists/get-details?id="+artistId+"&1=en-US"))
                .header("X-RapidAPI-Key", rapidApi.getRapidApiKey())
                .header("X-RapidAPI-Host", rapidApi.getRapidApiHost())
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


        JsonArray jsonArray = jsonObject.getAsJsonArray("data")
                .get(0)
                .getAsJsonObject()
                .getAsJsonObject("relationships")
                .getAsJsonObject("albums")
                .getAsJsonArray("data");

        howManyAlbumsAndSinglies=jsonArray.size();


    }

    public String getRandomSong(){

        Random rand = new Random();
        int randNumber = rand.nextInt(pieceOfMusic.size());
        return pieceOfMusic.get(randNumber);
    }


    public void displayInfo(){
        System.out.println(artistId);
        System.out.println(getArtistName());
        System.out.println(pieceOfMusic);
        System.out.println();
    }


}
