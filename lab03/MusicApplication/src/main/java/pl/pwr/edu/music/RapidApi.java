package pl.pwr.edu.music;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RapidApi {

    private String rapidApiKey;
    private String rapidApiHost ="shazam.p.rapidapi.com";

    public RapidApi() {
        readApiKey();
    }

    public String getRapidApiKey() {
        return rapidApiKey;
    }

    public String getRapidApiHost() {
        return rapidApiHost;
    }

    private void readApiKey(){

        InputStream is = getClass().getClassLoader().getResourceAsStream("rapidApiKey.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        try {
            line=reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        line=line.replace("\n", "");
        rapidApiKey=line;
    }

}
