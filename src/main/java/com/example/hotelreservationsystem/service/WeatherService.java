package com.example.hotelreservationsystem.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

/**
 * Servis za dohvat vremenskih informacija sa WeatherAPI.
 */
public class WeatherService {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String API_URL = "http://api.weatherapi.com/v1/current.json?key=9d11e9149a3640ec8c1144036241606&q=";

    /**
     * Dohvata vremenske informacije za zadatu lokaciju.
     *
     * @param location lokacija za koju se dohvataju vremenske informacije
     * @return vremenske informacije kao string
     * @throws Exception ako dođe do greške prilikom HTTP zahteva
     */
    public static String getWeather(String location) throws Exception {
        String urlString = API_URL + location;
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return parseWeatherResponse(response.toString());
        } else {
            // Dodajte više informacija o grešci
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            throw new Exception("GET request not worked: " + response.toString());
        }
    }

    /**
     * Parsira odgovor vremenskog API-ja.
     *
     * @param response odgovor kao string
     * @return parsirane vremenske informacije kao string
     */
    private static String parseWeatherResponse(String response) {
        JSONObject jsonObject = new JSONObject(response);
        JSONObject current = jsonObject.getJSONObject("current");
        double tempC = current.getDouble("temp_c");
        String condition = current.getJSONObject("condition").getString("text");
        return "Temperature: " + tempC + "°C, Condition: " + condition;
    }

    /**
     * Glavna metoda za testiranje dohvatanja vremenskih informacija.
     *
     * @param args argumenti komandne linije
     */
    public static void main(String[] args) {
        try {
            String weatherInfo = getWeather("London");
            System.out.println(weatherInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
