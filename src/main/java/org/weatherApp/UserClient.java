package org.weatherApp;

import java.util.Scanner;

public class UserClient {

    //https://geocode.maps.co/search?q=Polska,Warszawa,Chmielna,00-020&api_key=65e2f07a7c763292633381fac5b8a44

    public static void setUserDataForLocation(Location location) {
        Scanner scanner = new Scanner(System.in);
        String country;
        String city;
        String street;
        String zipCode;
        do {
            System.out.println("Podaj kraj: ");
            country = scanner.nextLine();
        } while (country.isEmpty());

        do {
            System.out.println("Podaj miasto: ");
            city = scanner.nextLine();
        } while (city.isEmpty());

        System.out.println("Podaj ulicę: ");
        street = scanner.nextLine();

        do {
            System.out.println("Podaj kod pocztowy: ");
            zipCode = scanner.nextLine();
        } while (zipCode.isEmpty());

        location.setCountry(country);
        location.setCity(city);
        location.setStreet(street);
        location.setZipCode(zipCode);
    }
}
