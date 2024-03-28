package lt.mindaugas.rest_api.examples;

import lt.mindaugas.rest_api.common.Util;
import lt.mindaugas.rest_api.common.connection.HttpConnection;
import lt.mindaugas.rest_api.common.connection.RequestMethod;
import lt.mindaugas.rest_api.examples.reqres_model.UserDetails;
import lt.mindaugas.rest_api.examples.reqres_model.UserResponse;
import lt.mindaugas.rest_api.examples.reqres_model.UsersResponse;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class RestApiExample {
    private static String baseUrl = "https://reqres.in";
    private static String endpoint = "";
    private static String url;

    public static void run() {
//        getListUsers();
//        checkLombokGeneratedMethods();
//        getListUsersWithParameters();
//        getUserDetails(5);
//        getListUsersIncludingHeaders();
//        postNewUser();
//        getListUsersByCustomisedConnection();
//        postNewUserByCustomisedConnection();
//        putUserByCustomisedConnection();
        deleteUserByCustomisedConnection();
    }

    private static void getListUsers() {
        endpoint = "/api/users";
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

    private static void checkLombokGeneratedMethods() {
        UserDetails user01 =
                new UserDetails(1, "Luke", "Skywalker", "demo@demo.com", "some avatar");
        UserDetails user02 =
                new UserDetails(1, "Luke", "Skywalker", "demo@demo.com", "some avatar");

        System.out.println(user01);
        System.out.println(user01.equals(user02));
    }

    private static void getListUsersWithParameters() {
        endpoint = "/api/users";
        // https://reqres.in/api/users
        // https://reqres.in/api/users?page=3&per_page=3
        url = baseUrl + endpoint;

        try {
            Map<String, String> queryParam =
                    Map.of(
                            "page", "4",
                            "per_page", "3"
                    );

            URI uri = Util.uriBuilder.apply(url, queryParam);
            HttpRequest httpRequest = HttpRequest
                    .newBuilder(uri)
                    .GET()
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            System.out.println("\u001B[31m");
            System.out.println("\n GET user list");
            System.out.println("\u001B[0m");
            System.out.println("Response code: " + httpResponse.statusCode());
            System.out.println("Response headers: " + httpResponse.headers());

            UsersResponse usersResponse =
                    (UsersResponse) Util.jsonToObj.apply(httpResponse.body(), UsersResponse.class);

            System.out.println("Page no: " + usersResponse.getSomePage());
            System.out.println("Page total: " + usersResponse.getTotalPages());
            System.out.println("Per page: " + usersResponse.getPerPage());

            Util.saveToJsonFile.accept(usersResponse);
            usersResponse = (UsersResponse) Util.readJsonFromFile.apply(UsersResponse.class);
            usersResponse.getData().forEach(System.out::println);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void getUserDetails(int userId) {
        System.out.println("\n*** Exercise ***\n");
        endpoint = "/api/users/" + userId;
        url = baseUrl + endpoint;

        try {
            URI uri1 = new URI(url);
            HttpRequest httpRequest = HttpRequest
                    .newBuilder()
                    .uri(uri1)
                    .GET()
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            System.out.println("Response code: " + httpResponse.statusCode());
            System.out.println("Response body: " + httpResponse.body());

            UserResponse userResponse = (UserResponse) Util.jsonToObj.apply(httpResponse.body(), UserResponse.class);
            Util.saveToJsonFile.accept(userResponse);
            userResponse = (UserResponse) Util.readJsonFromFile.apply(UserResponse.class);

            System.out.println(userResponse.getData());

//            System.out.println(userResponse.getData().getId());
//            System.out.println(userResponse.getData().getFirstName());
//            System.out.println(userResponse.getData().getLastName());
//            System.out.println(userResponse.getData().getEmail());
//            System.out.println(userResponse.getData().getAvatar());

        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void getListUsersIncludingHeaders() {
        endpoint = "/api/users";
        // https://reqres.in/api/users
        // https://reqres.in/api/users?page=3&per_page=3
        url = baseUrl + endpoint;

        try {
            Map<String, String> queryParam =
                    Map.of(
                            "page", "4",
                            "per_page", "3"
                    );

            Map<String, String> headers =
                    Map.of(
                            "content-type", "application/json; charset=utf-8",
                            "Authorisation", "Bearer your_ access_token",
                            "X-Api-Key", "your_api_key"
                    );

            URI uri = Util.uriBuilder.apply(url, queryParam);
            HttpRequest.Builder builder = HttpRequest
                    .newBuilder(uri);
//                    .header("content-type", "content-type")
//                    .header("Authorisation", "Authorisation")
//                    .header("X-Api-Key", "X-Api-Key");


//            headers.forEach((k, v) -> builder.header(k, v));
            headers.forEach(builder::header);

            HttpRequest httpRequest = builder.GET().build();


            HttpClient httpClient = HttpClient.newHttpClient();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            System.out.println("\u001B[31m");
            System.out.println("\n GET user list");
            System.out.println("\u001B[0m");

            System.out.println("Request headers: " + httpResponse.request().headers());
            System.out.println("Response code: " + httpResponse.statusCode());
            System.out.println("Response headers: " + httpResponse.headers());

            UsersResponse usersResponse =
                    (UsersResponse) Util.jsonToObj.apply(httpResponse.body(), UsersResponse.class);

            System.out.println("Page no: " + usersResponse.getSomePage());
            System.out.println("Page total: " + usersResponse.getTotalPages());
            System.out.println("Per page: " + usersResponse.getPerPage());

            Util.saveToJsonFile.accept(usersResponse);
            usersResponse = (UsersResponse) Util.readJsonFromFile.apply(UsersResponse.class);
            usersResponse.getData().forEach(System.out::println);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void postNewUser() {
        endpoint = "/api/users";
        url = baseUrl + endpoint;

        Map<String, String> headers =
                Map.of(
                        "content-type", "application/json; charset=utf-8",
                        "Authorisation", "Bearer your_ access_token",
                        "X-Api-Key", "your_api_key"
                );

        String jsonData = """
                {
                    "name" : "Yoda",
                    "email" : "demo@demo.com",
                    "job" : "Jedi master",
                    "age" : 255,
                    "Date of birth" : "3550-01-01"
                }
                """;

        UserDetails user = new UserDetails("Darth", "Vader", "demo@demo.com", "some avatar");
        jsonData = Util.objToJson.apply(user);

        URI uri = URI.create(url);
        HttpRequest.Builder builder = HttpRequest
                .newBuilder(uri);

        headers.forEach(builder::header);

        HttpRequest httpRequest =
                builder
//                        .POST(HttpRequest.BodyPublishers.ofString(jsonData))
                        .method("POST", HttpRequest.BodyPublishers.ofString(jsonData))
                        .build();

        try {
            HttpResponse<String> httpResponse =
                    HttpClient.newHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());

            System.out.println("Response code: " + httpResponse.statusCode());
            System.out.println("Response headers: " + httpResponse.headers());
            System.out.println("Response body: " + httpResponse.body());

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void getListUsersByCustomisedConnection() {
        endpoint = "/api/users";
        url = baseUrl + endpoint;

        Map<String, String> queryParam =
                Map.of(
                        "page", "4",
                        "per_page", "3"
                );

        Map<String, String> headers =
                Map.of(
                        "content-type", "application/json; charset=utf-8",
                        "Authorisation", "Bearer your_ access_token",
                        "X-Api-Key", "your_api_key"
                );

        try {
            HttpResponse<String> httpResponse = HttpConnection
                    .request
                    .openUrl(url)
                    .setParams(queryParam)
                    .setHeaders(headers)
                    .build(RequestMethod.GET);

            System.out.println("\u001B[31m");
            System.out.println("\n GET user list");
            System.out.println("\u001B[0m");
            System.out.println("Request: " + httpResponse.request());
            System.out.println("Response code: " + httpResponse.statusCode());
            System.out.println("Response headers: " + httpResponse.headers());
            System.out.println("Response body: " + httpResponse.body());

            UsersResponse usersResponse =
                    (UsersResponse) Util.jsonToObj.apply(httpResponse.body(), UsersResponse.class);

            usersResponse.getData().forEach(System.out::println);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    private static void postNewUserByCustomisedConnection() {
        endpoint = "/api/users";
        url = baseUrl + endpoint;

        Map<String, String> queryParam =
                Map.of(
                        "page", "4",
                        "per_page", "3"
                );

        Map<String, String> headers =
                Map.of(
                        "content-type", "application/json; charset=utf-8",
                        "Authorisation", "Bearer your_ access_token",
                        "X-Api-Key", "your_api_key"
                );

        UserDetails user = new UserDetails("Darth", "Vader", "demo@demo.com", "some avatar");

        try {
            HttpResponse<String> httpResponse = HttpConnection
                    .request
                    .openUrl(url)
                    .setHeaders(headers)
                    .setBodyPublisher(user)
                    .build(RequestMethod.POST);

            System.out.println("\u001B[31m");
            System.out.println("\n POST new user");
            System.out.println("\u001B[0m");
            System.out.println("Request: " + httpResponse.request());
            System.out.println("Response code: " + httpResponse.statusCode());
            System.out.println("Response headers: " + httpResponse.headers());
            System.out.println("Response body: " + httpResponse.body());

            UserDetails userDetails =
                    (UserDetails) Util.jsonToObj.apply(httpResponse.body(), UserDetails.class);
            System.out.println(userDetails);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void putUserByCustomisedConnection() {
        // PUT -> atnaujina visus objekto laukus (perraso)
        // PATCH -> galima atnaujinti viena ar keleta lauku

        endpoint = "/api/users";
        url = baseUrl + endpoint;

        Map<String, String> queryParam =
                Map.of(
                        "page", "4",
                        "per_page", "3"
                );

        Map<String, String> headers =
                Map.of(
                        "content-type", "application/json; charset=utf-8",
                        "Authorisation", "Bearer your_ access_token",
                        "X-Api-Key", "your_api_key"
                );

        UserDetails user = new UserDetails(
                45,"Darth", "Vader", "demo@demo.com", "some avatar"
        );
        url += "/" + user.getId();
        // butinai nurodom ID

        try {
            HttpResponse<String> httpResponse = HttpConnection
                    .request
                    .openUrl(url)
                    .setBodyPublisher(user)
                    .setHeaders(headers)
                    .build(RequestMethod.PUT);

            System.out.println("\u001B[31m");
            System.out.println("\n PUT new user");
            System.out.println("\u001B[0m");
            System.out.println("Request: " + httpResponse.request());
            System.out.println("Response code: " + httpResponse.statusCode());
            System.out.println("Response headers: " + httpResponse.headers());
            System.out.println("Response body: " + httpResponse.body());

            UserDetails userDetails =
                    (UserDetails) Util.jsonToObj.apply(httpResponse.body(), UserDetails.class);
            System.out.println(userDetails);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void deleteUserByCustomisedConnection() {
        endpoint = "/api/users";
        url = baseUrl + endpoint;

        Map<String, String> headers =
                Map.of(
                        "content-type", "application/json; charset=utf-8",
                        "Authorisation", "Bearer your_ access_token",
                        "X-Api-Key", "your_api_key"
                );

        UserDetails user = new UserDetails(
                45,"Darth", "Vader", "demo@demo.com", "some avatar"
        );
        url += "/" + user.getId();

        try {
            HttpResponse<String> httpResponse = HttpConnection
                    .request
                    .openUrl(url)
                    .setHeaders(headers)
                    .build(RequestMethod.DELETE);

            System.out.println("\u001B[31m");
            System.out.println("\n DELETE user");
            System.out.println("\u001B[0m");
            System.out.println("Request: " + httpResponse.request());
            System.out.println("Response code: " + httpResponse.statusCode());
            System.out.println("Response headers: " + httpResponse.headers());
            System.out.println("Response body: " + httpResponse.body());

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

