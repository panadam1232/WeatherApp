package org.weatherApp;

public class Main {
    public static void main(String[] args) throws Exception {
        WeatherService weatherService = new WeatherService();
        System.out.println(weatherService.ShowWeatherData());
    }
}
