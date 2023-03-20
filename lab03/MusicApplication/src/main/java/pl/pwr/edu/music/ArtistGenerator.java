package pl.pwr.edu.music;

import com.google.gson.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ArtistGenerator {

    private ArrayList<Artist> artistArrayList= new ArrayList<>();
    private ArrayList<String> uriList = new ArrayList<>();
    private String rapidApiKey = "332cc35608msh79f3d6bc5480ec1p1b7f2ejsncfd8b8ad9598";
    private String rapidApiHost ="shazam.p.rapidapi.com";

    public ArrayList<Artist> getArtistArrayList() {
        return artistArrayList;
    }

    Gson gson;


    public ArtistGenerator() throws IOException, InterruptedException {


       uriList.add("https://shazam.p.rapidapi.com/search?term=Nocny%20Kochanek&locale=en-US&offset=0&limit=5");


       for(int i=0;i<uriList.size();i++){
           generateArtist(uriList.get(i));
       }



    }

    private void generateArtist(String uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("X-RapidAPI-Key", rapidApiKey)
                .header("X-RapidAPI-Host", rapidApiHost)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);

        JsonArray hits = jsonObject.getAsJsonObject("tracks").getAsJsonArray("hits");
        ArrayList<String> titles = new ArrayList<>();
        for (JsonElement hit : hits) {
            String title = hit.getAsJsonObject().getAsJsonObject("track").get("title").getAsString();
            titles.add(title);
        }

        String subtitle = jsonObject.getAsJsonObject("tracks")
                .getAsJsonArray("hits")
                .get(0)
                .getAsJsonObject()
                .getAsJsonObject("track")
                .get("subtitle")
                .getAsString();

        artistArrayList.add(new Artist(subtitle, titles));
    }

}
