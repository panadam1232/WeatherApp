package org.weatherApp;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiClient {
    public static String buildGeocodeApiUrl(Location location) {

        String resultApiUrl = "https://geocode.maps.co/search?q="
                + location.getCountry()
                + "," + location.getCity()
                + "," + location.getStreet()
                + "," + location.getZipCode()
                + "&api_key=65e2f07a7c763292633381fac5b8a44";

        return resultApiUrl;
    }

    public static String buildOpenWeathereApiUrl(Location location) {

        //https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid=

        String resultOpenWeatherApiUrl = "https://api.openweathermap.org/data/2.5/weather?"
                + "lat=" + location.getLat()
                + "&lon=" + location.getLon()
                + "&appid=29a9b093e8a39ce93b7acc545b128a04&units=metric";

        return resultOpenWeatherApiUrl;
    }


    public static String buildAccuweatherGeopositioningApiUrl(Location location) {

        //http://dataservice.accuweather.com/locations/v1/cities/geoposition/search?q=52.229676,21.012229&apikey=EjIs6w02WNNFJeJ4P72Bv4TkEVepCFcD

        String resultAccuweatherGeopositionApiUrl = "https://dataservice.accuweather.com/locations/v1/cities/geoposition/search?q="
                + location.getLat()
                + "," + location.getLon()
                + "&apikey=EdFTHcoLB4yDixzdoV6XkXnEBNPM1lYh";

        return resultAccuweatherGeopositionApiUrl;
    }

    /**
     *
     * @param cityKey
     * @return
     */
    public static String buildAccuweatherConditionApiUrl (String cityKey){

        //http://dataservice.accuweather.com/currentconditions/v1/{Key}?apikey=EjIs6w02WNNFJeJ4P72Bv4TkEVepCFcD

        String resultAccuweatherConditionApiUrl = "https://dataservice.accuweather.com/currentconditions/v1/"
                + cityKey
                + "?apikey=EdFTHcoLB4yDixzdoV6XkXnEBNPM1lYh&details=true";

        return resultAccuweatherConditionApiUrl;
    }


    public static String makeApiRequest(String apiUrl) throws Exception {

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Read the response
        StringBuilder response = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        }
        return response.toString();
    }

    public static void getCoordinatesFromJSon(String geocodeApiResult, Location location) {

        JSONArray jsonArray = new JSONArray(geocodeApiResult);
        JSONObject jsonObject = jsonArray.getJSONObject(0);

        String lat = jsonObject.getString("lat");
        String lon = jsonObject.getString("lon");

        location.setLat(lat);
        location.setLon(lon);
    }

    public static void getDataFromOpenweatherJSon(String openWeathereApiResult, WeatherData weatherData) {
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
    public static String getGeopositioningAccuweatherCityKeyFromJSon (String resultAccuweatherGeopositionApiUrl) throws Exception {
        JSONObject jsonObject = new JSONObject(makeApiRequest(resultAccuweatherGeopositionApiUrl));
        String cityKey = jsonObject.getString("Key");
        return cityKey;
    }
    public static void getDataFromAccuweatherJSon(String accuweatherConditionApiUrlResult, WeatherData weatherData) {
        JSONArray jsonArray = new JSONArray(accuweatherConditionApiUrlResult);
        JSONObject jsonObject = jsonArray.getJSONObject(0);

        double temp = jsonObject.getJSONObject("Temperature").getJSONObject("Metric").getDouble("Value");
        double pressure = jsonObject.getJSONObject("Pressure").getJSONObject("Metric").getDouble("Value");
        double humidity = jsonObject.getDouble("RelativeHumidity");
        double speed = jsonObject.getJSONObject("Wind").getJSONObject("Speed").getJSONObject("Metric").getDouble("Value");
        double deg = jsonObject.getJSONObject("Wind").getJSONObject("Direction").getDouble("Degrees");

        weatherData.setTemp(temp);
        weatherData.setPressure(pressure);
        weatherData.setHumidity(humidity);
        weatherData.setSpeed(speed);
        weatherData.setDeg(deg);

    }


}
