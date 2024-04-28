import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Scanner;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Practicum {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        WeatherApplication weatherApp = new WeatherApplication();
//
//        System.out.println("Приложение для получения информации о погоде.");
//        System.out.println("Доступные города: Москва (MOW), Санкт-Петербург (LED), Казань (KZN)");
//        while (true) {
//            System.out.println("Введите код города (или 'exit' для выхода).");
//            String command = scanner.nextLine();
//
//            if (command.equalsIgnoreCase("exit")) {
//                break;
//            }
//
//            weatherApp.displayWeather(command);
//        }
        System.out.println("to test reset");
    }
}

class WeatherApplication {
    private HashMap<String, String> weatherData;
    private WeatherClient weatherClient;

    public WeatherApplication() {
        initializedData();
    }

    // инициализация статических данных о погоде
    private void initializedData() {
        weatherData = new HashMap<>();
        weatherData.put("MOW", "Город: Москва. Облачно, +5°C");
        weatherData.put("LED", "Санкт-Петербург. Дождливо, +3°C");
        weatherData.put("KZN", "Город: Казань. Солнечно, +12°C");
    }

    // метод для отображения погоды
    public void displayWeather(String city) {
        HttpClient client = HttpClient.newHttpClient();
        // замените данные на динамические, полученные через weatherClient
        // String weatherData = this.weatherData.getOrDefault(city, "Данные о погоде не найдены");
        weatherClient = new WeatherClient(client);

        String response = weatherClient.getWeatherData(city);
        System.out.println(response);
    }
}

class WeatherClient {
    private HttpClient client;

    public WeatherClient(HttpClient client) {
        this.client = client;
    }

    public String getWeatherData(String city) {
        URI uri = URI.create("https://functions.yandexcloud.net/d4eo3a1nvqedpic89160/?scale=C&city=" + city);

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                return "Что-то пошло не так. Сервер вернул код состояния: " + response.statusCode();
            }

            String body = response.body();
            JsonObject cities = JsonParser.parseString(body).getAsJsonObject().get("cities").getAsJsonObject();
            JsonObject cityObject = cities.get(city).getAsJsonObject();

            String cityField = cityObject.get("city").getAsString();
            String conditionField = cityObject.get("conditions").getAsString();
            String temperatureField = cityObject.get("temperature").getAsString();

            return "Город: " + cityField + ". " + conditionField + ", " + temperatureField;
        } catch(Exception e) {
            return "Во время выполнения запроса возникла ошибка. Проверьте, пожалуйста, параметры запроса и повторите попытку.";
        }
    }
}