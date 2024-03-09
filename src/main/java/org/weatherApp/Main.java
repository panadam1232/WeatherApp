package org.weatherApp;

public class Main {
    public static void main(String[] args) throws Exception {
        URLConnection urlConnection = new DefaultURLConnection();
        GeocodeApi geocodeApi = new GeocodeApi(urlConnection);
        UserClient userClient = new UserClient();
        Location location = new Location();
        userClient.getLocationDataFromUser(location);
        geocodeApi.getCoordinatesFromJSon(location);
        System.out.println(location);


        WeatherApiStrategy weatherApiStrategyForOpenweather = new OpenweatherApi(urlConnection);
        System.out.println(weatherApiStrategyForOpenweather.getWeatherData(location));

        WeatherApiStrategy weatherApiStrategyForAccuweather = new AccuweatherApi(urlConnection);
        System.out.println(weatherApiStrategyForAccuweather.getWeatherData(location));

        try {

//            WeatherData openweatherData = weatherServiceOpenweather.getApiStrategy().getWeatherData(location);
//            WeatherData accuweatherData = weatherServiceAccuweather.getApiStrategy().getWeatherData(location);



        } catch (Exception e) {
            System.out.println("Wystąpił błąd podczas pobierania danych pogodowych: " + e.getMessage());
        }
    }
}
