package org.weatherApp.sourcecode;

import org.json.JSONArray;
import org.json.JSONObject;

public class AccuweatherApi implements WeatherApiStrategy {
    private URLConnection urlConnection;

    protected AccuweatherApi(URLConnection urlConnection) {
        this.urlConnection = urlConnection;
    }

    @Override
    public WeatherData getWeatherData(Location location) throws Exception {
        String citykeyFromApiResponse = urlConnection.makeApiRequest(accuweatherGeopositioningApiUrl(location));
        String citykey = getGeopositioningAccuweatherCityKeyFromJSon(citykeyFromApiResponse);

        String conditionDataFromApiResponse = urlConnection.makeApiRequest(accuweatherConditionApiUrl(citykey));
        WeatherData weatherData = new WeatherData();
        getDataFromAccuweatherJSon(conditionDataFromApiResponse, weatherData);
        return weatherData;
    }

    private String accuweatherGeopositioningApiUrl(Location location) {

        //http://dataservice.accuweather.com/locations/v1/cities/geoposition/search?q=52.229676,21.012229&apikey=EjIs6w02WNNFJeJ4P72Bv4TkEVepCFcD

        String accuweatherGeopositionApiUrlresult = "https://dataservice.accuweather.com/locations/v1/cities/geoposition/search?q="
                + location.getLat()
                + "," + location.getLon()
                + "&apikey=EdFTHcoLB4yDixzdoV6XkXnEBNPM1lYh";

        return accuweatherGeopositionApiUrlresult;
    }

    private String accuweatherConditionApiUrl(String cityKey) {

        //http://dataservice.accuweather.com/currentconditions/v1/{Key}?apikey=EjIs6w02WNNFJeJ4P72Bv4TkEVepCFcD

        String resultAccuweatherConditionApiUrl = "https://dataservice.accuweather.com/currentconditions/v1/"
                + cityKey
                + "?apikey=EdFTHcoLB4yDixzdoV6XkXnEBNPM1lYh&details=true";

        return resultAccuweatherConditionApiUrl;
    }

    private String getGeopositioningAccuweatherCityKeyFromJSon(String accuweatherGeopositionApiUrlresult) throws Exception {
        JSONObject jsonObject = new JSONObject(accuweatherGeopositionApiUrlresult);
        String cityKey = jsonObject.getString("Key");
        return cityKey;
    }

    private void getDataFromAccuweatherJSon(String accuweatherConditionApiUrlResult, WeatherData weatherData) {
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
