package org.weatherApp.sourcecode;

import org.json.JSONArray;
import org.json.JSONObject;

public class GeocodeApi {
    private URLConnection urlConnection;
    public GeocodeApi(URLConnection urlConnection) {
        this.urlConnection = urlConnection;
    }
    private String geocodeApiUrl(Location location) {

        String resultApiUrl = "https://geocode.maps.co/search?q="
                + location.getCountry()
                + "," + location.getCity()
                + "," + location.getStreet()
                + "," + location.getZipCode()
                + "&api_key=65e2f07a7c763292633381fac5b8a44";

        return resultApiUrl;
    }

    public void getCoordinatesFromJSon(Location location) throws Exception {
        JSONArray jsonArray = new JSONArray(urlConnection.makeApiRequest(geocodeApiUrl(location)));
        JSONObject jsonObject = jsonArray.getJSONObject(0);

        String lat = jsonObject.getString("lat");
        String lon = jsonObject.getString("lon");

        location.setLat(lat);
        location.setLon(lon);
    }
}
