package org.weatherApp.sourcecode;

import org.json.JSONObject;

public class OpenweatherApi implements WeatherApiStrategy {
    private URLConnection urlConnection;

    public OpenweatherApi(URLConnection urlConnection) {
        this.urlConnection = urlConnection;
    }

    @Override
    public WeatherData getWeatherData(Location location) throws Exception {
        String conditionDataFromApiResponse = urlConnection.makeApiRequest(openweatherApiUrl(location));
        WeatherData weatherData = new WeatherData();
        getDataFromOpenweatherJSon(conditionDataFromApiResponse, weatherData);
        return weatherData;
    }

    private String openweatherApiUrl(Location location) {

        //https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid=

        String resultOpenWeatherApiUrl = "https://api.openweathermap.org/data/2.5/weather?"
                + "lat=" + location.getLat()
                + "&lon=" + location.getLon()
                + "&appid=29a9b093e8a39ce93b7acc545b128a04&units=metric";

        return resultOpenWeatherApiUrl;
    }

    private void getDataFromOpenweatherJSon(String openWeathereApiResult, WeatherData weatherData) {
        JSONObject jsonObject = new JSONObject(openWeathereApiResult);
        JSONObject jsonObjectMain = jsonObject.getJSONObject("main");
        JSONObject jsonObjectWind = jsonObject.getJSONObject("wind");


        double temp = jsonObjectMain.getDouble("temp");
        double pressure = jsonObjectMain.getDouble("pressure");
        double humidity = jsonObjectMain.getDouble("humidity");
        double speed = jsonObjectWind.getDouble("speed");
        double deg = jsonObjectWind.getDouble("deg");

        weatherData.setTemp(temp);
        weatherData.setPressure(pressure);
        weatherData.setHumidity(humidity);
        weatherData.setSpeed(speed);
        weatherData.setDeg(deg);

    }

}
