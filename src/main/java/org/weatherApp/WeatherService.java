package org.weatherApp;

public class WeatherService {
    private WeatherApiStrategy apiStrategy;

    public void setApiStrategy(WeatherApiStrategy apiStrategy) {
        this.apiStrategy = apiStrategy;
    }

    public WeatherData getWeatherData(Location location) throws Exception {
        if (apiStrategy == null) {
            throw new IllegalStateException("Weather API strategy is not set");
        }

        return apiStrategy.getWeatherData(location);
    }
}