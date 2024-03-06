package org.weatherApp;

public interface WeatherApiStrategy {
    WeatherData getWeatherData(Location location) throws Exception;
}
