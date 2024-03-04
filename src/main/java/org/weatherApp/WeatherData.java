package org.weatherApp;

public class WeatherData {
    //— Subtask: Przygotowanie modelu danych dla rezultatu (temperatura, ciśnienie, wilgotność, kierunek i prędkość wiatru).
    private double temp;
    private double pressure;
    private double humidity;
    private double speed;
    private double deg;

    public double getTemp() {
        return temp;
    }

    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDeg() {
        return deg;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setDeg(double deg) {
        this.deg = deg;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "temp=" + temp +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", speed=" + speed +
                ", deg=" + deg +
                '}';
    }
}
