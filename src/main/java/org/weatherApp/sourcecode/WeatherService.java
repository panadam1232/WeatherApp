package org.weatherApp.sourcecode;

public class WeatherService {
    private WeatherApiStrategy apiStrategyOne;
    private WeatherApiStrategy apiStrategyTwo;


    public WeatherData ShowWeatherData() throws Exception {
        URLConnection urlConnection = new DefaultURLConnection();
        GeocodeApi geocodeApi = new GeocodeApi(urlConnection);
        UserClient userClient = new UserClient();
        Location location = new Location();
        userClient.getLocationDataFromUser(location);
        geocodeApi.getCoordinatesFromJSon(location);
        apiStrategyOne = new OpenweatherApi(urlConnection);
        apiStrategyTwo = new AccuweatherApi(urlConnection);

        WeatherData weatherData1 = apiStrategyOne.getWeatherData(location);
        WeatherData weatherData2 = apiStrategyTwo.getWeatherData(location);
        WeatherData weatherDataFinal = getAverageValueForWeatherData(weatherData1, weatherData2);

        return weatherDataFinal;
    }

    private static WeatherData getAverageValueForWeatherData(WeatherData weatherData1, WeatherData weatherData2) {
        WeatherData weatherDataFinal = new WeatherData();

        weatherDataFinal.setTemp((weatherData1.getTemp() + weatherData2.getTemp() / 2));
        weatherDataFinal.setPressure((weatherData1.getPressure() + weatherData2.getPressure()) / 2);
        weatherDataFinal.setHumidity((weatherData1.getHumidity() + weatherData2.getHumidity()) / 2);
        weatherDataFinal.setSpeed((weatherData1.getSpeed() + weatherData2.getSpeed()) / 2);
        weatherDataFinal.setDeg((weatherData1.getDeg() + weatherData2.getSpeed()) / 2);
        return weatherDataFinal;
    }
}