
package com.java_weather_app.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.imageio.ImageReadParam;

import org.json.JSONArray;
import org.json.JSONObject;

public class weatherApp {

    private static String apiKey = // Enter API KEY
    private static String apiUrl = "https://api.openweathermap.org/data/2.5/weather";

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        while (true) {;

            System.out.println("Enter a city or type 'quit' to exit the program");

            String city = scanner.nextLine();

            if (city.equals("quit")) {
                System.out.println("Exiting Program!");
                break;
            }

            if (city.isEmpty()) {
                System.out.println("City cannot be empty!");
                
            }

            try {
                
                URL url = new URL(buildApiUrl(city));

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    String line;

                    StringBuffer response = new StringBuffer();

                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                        
                    }

                    parseWeatherData(response.toString());

                    reader.close();

                    
                    
                } else {
                    System.out.println("ERROR: Cannot connect to API URL or city is typed incorrectly: " + connection.getResponseCode());
                }

                connection.disconnect();

            } catch (IOException e) {
                e.printStackTrace();
            }

            
            
        }

    }

    private static String buildApiUrl(String city) {
        return String.format("%s?q=%s&appid=%s&units=metric", apiUrl, city, apiKey);

    }

    private static void parseWeatherData(String returnData) {

        JSONObject json = new JSONObject(returnData);
        
        // MAIN:
        JSONObject main = json.getJSONObject("main");
        int temp = main.getInt("temp");
        int humidity = main.getInt("humidity");

        //Weather Arr:
        JSONArray weatherArray = json.getJSONArray("weather");
        JSONObject weatherObject = weatherArray.getJSONObject(0);
        String description = weatherObject.getString("description");


        System.out.println(temp);
        System.out.println(description);

    }

}
