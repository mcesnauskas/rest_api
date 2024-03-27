package lt.mindaugas.rest_api.exercises;

import lt.mindaugas.rest_api.common.Util;
import lt.mindaugas.rest_api.exercises.newsapi.SourceResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class NewsApiExercise {
    private static String baseUrl = "https://newsapi.org";
    private static String endpoint = "";
    private static String url;

    public static void getNewsSources() {
        endpoint = "/v2/top-headlines/sources";
        url = baseUrl + endpoint;

        try {
            Map<String, String> queryParam =
                    Map.of(
                            "page", "5",
                            "per_page", "4",
                            "category", "entertainment",
                            "language", "en",
                            "country", "us"
                    );

            Map<String, String> headers =
                    Map.of(
                            "Content-Type", "application/json",
                            "X-Api-Key", "b6c71e4783294e86a44b573fc7f73c1b"
                    );

            URI uri = Util.uriBuilder.apply(url, queryParam);
            HttpRequest.Builder builder = HttpRequest
                    .newBuilder(uri);

            headers.forEach(builder::header);
            HttpRequest httpRequest = builder.GET().build();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            System.out.println("\u001b[92mResponse code: \u001b[92;1m" + httpResponse.statusCode() +"\u001b[0m");
            System.out.println("Response body: " + httpResponse.body());

            SourceResponse sourceResponse = (SourceResponse) Util.jsonToObj.apply(httpResponse.body(), SourceResponse.class);
            Util.saveToJsonFile.accept(sourceResponse);

            sourceResponse = (SourceResponse) Util.readJsonFromFile.apply(SourceResponse.class);
            sourceResponse.getSources().forEach(System.out::println);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
