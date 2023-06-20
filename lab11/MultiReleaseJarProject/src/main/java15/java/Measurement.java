package java;

public class Measurement {

    private String hour;
    private float pressure;
    private float temperature;
    private float humidity;

    public Measurement(String hour, float pressure, float temperature, float humidity) {
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

        this.pressure=pressure;

    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }



}
