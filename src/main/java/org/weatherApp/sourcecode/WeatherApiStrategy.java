package org.weatherApp.sourcecode;

public interface WeatherApiStrategy {
    WeatherData getWeatherData(Location location) throws Exception;

}
