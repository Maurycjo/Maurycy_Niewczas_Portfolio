package pl.edu.pwr.file;

public class Measurement {

    private String hour;
    private float pressure;
    private float temperature;
    private int humidity;

    public Measurement(String hour, float pressure, float temperature, int humidity) {
     this.setHour(hour);
     this.setPressure(pressure);
     this.setTemperature(temperature);
     this.setHumidity(humidity);
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        if(pressure>=0 && pressure<=100){
            this.pressure = pressure;
        } else{
            throw new IllegalArgumentException("can't be smaller than 0 and greater than 100");
        }

    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }



}
