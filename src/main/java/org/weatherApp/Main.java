package org.weatherApp;


import org.weatherApp.sourcecode.WeatherService;

public class Main {
    public static void main(String[] args) throws Exception {
        WeatherService weatherService = new WeatherService();
        System.out.println(weatherService.ShowWeatherData());
    }
}
