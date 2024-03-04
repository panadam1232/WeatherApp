package org.weatherApp;

public class Main {
    public static void main(String[] args) throws Exception {
        Location location = new Location();
        WeatherData weatherDataFromOpenweather = new WeatherData();
        WeatherData weatherDataFromAccuweather = new WeatherData();

        UserClient.setUserDataForLocation(location);

        ApiClient.getCoordinatesFromJSon
                (
                        ApiClient.makeApiRequest(ApiClient.buildGeocodeApiUrl(location)),
                        location
                );

        ApiClient.getDataFromOpenweatherJSon
                (
                        ApiClient.makeApiRequest(ApiClient.buildOpenWeathereApiUrl(location)),
                        weatherDataFromOpenweather
                );
        System.out.println(weatherDataFromOpenweather);


        String resultAccuweatherGeopositionApiUrl = ApiClient.buildAccuweatherGeopositioningApiUrl(location);
        String cityKey = ApiClient.getGeopositioningAccuweatherCityKeyFromJSon(resultAccuweatherGeopositionApiUrl);
        String resultAccuweatherConditionApiUrl = ApiClient.buildAccuweatherConditionApiUrl(cityKey);
        String resultOfAccuweatherApiUrlRequest = ApiClient.makeApiRequest(resultAccuweatherConditionApiUrl);
        ApiClient.getDataFromAccuweatherJSon(resultOfAccuweatherApiUrlRequest,weatherDataFromAccuweather);

        System.out.println(weatherDataFromAccuweather);


    }
}
