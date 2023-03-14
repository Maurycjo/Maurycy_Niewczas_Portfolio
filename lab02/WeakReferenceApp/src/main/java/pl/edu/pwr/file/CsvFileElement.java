package pl.edu.pwr.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Stream;

public class CsvFileElement extends FileElement{

    private float avgPressure;
    private float avgTemperature;
    private float avgHumidity;

    private ArrayList<Measurement> measurementArrayList = new ArrayList<>();


    public float getAvgPressure() {
        return avgPressure;
    }

    public float getAvgTemperature() {
        return avgTemperature;
    }

    public float getAvgHumidity() {
        return avgHumidity;
    }

    public ArrayList<Measurement> getMeasurementArrayList() {
        return measurementArrayList;
    }

    public CsvFileElement(Path filePath){
        super(filePath);
    }

    public String getFileNameWithInfo(){
        return "Csv  | " + getFileName();
    }

    public void readFile(){

        try(Stream<String> lines = Files.lines(getFilePath())){

            lines.forEach(line-> {

                String[] result = line.split(",");

                    String hour = result[0];
                    float pressure = Float.parseFloat(result[1]);
                    float temperature = Float.parseFloat(result[2]);
                    float humidity = Float.parseFloat(result[3]);

                    measurementArrayList.add(new Measurement(hour, pressure, temperature, humidity));
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.calculateAverageValues();
    }

    private void calculateAverageValues(){
        float sumPressure=0;
        float sumTemperature=0;
        float sumHumidity=0;

        for(Measurement measurement:measurementArrayList){
            sumPressure+=measurement.getPressure();
            sumTemperature+=measurement.getTemperature();
            sumHumidity+=measurement.getHumidity();
        }

        int listSize = measurementArrayList.size();
        this.avgPressure = sumPressure/listSize;
        this.avgTemperature = sumTemperature/listSize;
        this.avgHumidity = sumHumidity/listSize;
    }
}
