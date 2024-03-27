package lt.mindaugas.rest_api.examples;

import lt.mindaugas.rest_api.common.Util;
import lt.mindaugas.rest_api.examples.reqres_model.UsersResponse;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RestApiExample {
    private static String baseUrl = "https://reqres.in";
    private static String endpoint = "";
    private static String url;

    public static void run(){
        getListUsers();
    }

    private static void getListUsers() {
        endpoint ="/api/users";
        // https://reqres.in/api/users
        // https://reqres.in/api/users?page=3&per_page=3
        url = baseUrl + endpoint;

        try {
            URI uri = new URI(url);
            HttpRequest httpRequest = HttpRequest
                    .newBuilder(uri)
                    .GET()
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            System.out.println("\u001B[31m");
            System.out.println("\n GET user list");
            System.out.println("\u001B[0m");
            System.out.println("Request: " + httpResponse.request());
            System.out.println("SSL session: " + httpResponse.sslSession());
            System.out.println("Response code: " + httpResponse.statusCode());
            System.out.println("Response headers: " + httpResponse.headers());
            System.out.println("Response body: " + httpResponse.body());

            UsersResponse usersResponse =
                    (UsersResponse) Util.jsonToObj.apply(httpResponse.body(), UsersResponse.class);

            usersResponse.getData().forEach(System.out::println);
            Util.saveToJsonFile.accept(usersResponse);
            Util.readJsonFromFile.apply(UsersResponse.class);

        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
