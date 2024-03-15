package org.weatherApp.sourcecode;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class GeocodeApiTest {


    @Test
    void shouldGenerateGeocodeApiUrlTest() {
        Location location = new Location();
        location.setCountry("Polska");
        location.setCity("Warszawa");
        location.setStreet("Chmielna");
        location.setZipCode("00-020");

        //when
        String resultApiUrl = "https://geocode.maps.co/search?q="
                + location.getCountry()
                + "," + location.getCity()
                + "," + location.getStreet()
                + "," + location.getZipCode()
                + "&api_key=65e2f07a7c763292633381fac5b8a44";

        //then
        String expectedResultApiUrl = "https://geocode.maps.co/search?q=Polska,Warszawa,Chmielna,00-020&api_key=65e2f07a7c763292633381fac5b8a44";
        assertEquals(expectedResultApiUrl, resultApiUrl);
    }

    @Test
    void shouldGetCoordinatesFromJsonTest() throws Exception {
        //given
        URLConnection urlConnection = Mockito.mock(URLConnection.class);
        Location location = new Location();
        GeocodeApi geocodeApi = new GeocodeApi(urlConnection);
        String jsonResponse = "[{\"place_id\":160585207,\"licence\":\"Data © OpenStreetMap contributors, ODbL 1.0. https://osm.org/copyright\",\"osm_type\":\"node\",\"osm_id\":4936916388,\"boundingbox\":[\"52.2332136\",\"52.2333136\",\"21.0173842\",\"21.0174842\"],\"lat\":\"52.2332636\",\"lon\":\"21.0174342\",\"display_name\":\"Polska Księgarnia Narodowa, 10, Chmielna, Centrum, Śródmieście Północne, Śródmieście, Warsaw, Masovian Voivodeship, 00-020, Poland\",\"class\":\"place\",\"type\":\"house\",\"importance\":0.41000999999999993}]";
        Mockito
                .when(urlConnection.makeApiRequest(Mockito.anyString()))
                .thenReturn(jsonResponse);
        //when
        geocodeApi.getCoordinatesFromJSon(location);

        //then
        assertEquals("52.2332636", location.getLat());
        assertEquals("21.0174342", location.getLon());

    }
}