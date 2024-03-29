package lt.mindaugas.rest_api.exercises;

import com.google.gson.Gson;
import lt.mindaugas.rest_api.common.Util;
import lt.mindaugas.rest_api.common.connection.HttpConnection;
import lt.mindaugas.rest_api.common.connection.RequestMethod;
import lt.mindaugas.rest_api.examples.reqres_model.UserDetails;
import lt.mindaugas.rest_api.exercises.authorisation_exercise.Login;
import lt.mindaugas.rest_api.exercises.authorisation_exercise.Register;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Map;

public class AuthorisationExercise {

    private static String baseUrl = "https://reqres.in";
    private static String endpoint = "";
    private static String url;

    public static void doExerciseReqresLogin() {
        endpoint = "/api/login";
        url = baseUrl + endpoint;

//        Map<String, String> loginData =
//                Map.of(
//                        "email", "eve.holt@reqres.in",
//                        "password", "cityslicka"
//                );

        Login loginData = new Login("eve.holt@reqres.in", "cityslicka");

        Map<String, String> headers =
                Map.of(
                        "content-type", "application/json; charset=utf-8"
                );

        try {
            HttpResponse<String> httpResponse = HttpConnection
                    .request
                    .openUrl(url)
                    .setHeaders(headers)
                    .setBodyPublisher(loginData)
                    .build(RequestMethod.POST);

            System.out.println("Request: " + httpResponse.request());
            System.out.println("Response code: " + httpResponse.statusCode());
            System.out.println("Response headers: " + httpResponse.headers());
            System.out.println("Response body: " + httpResponse.body());

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void doExerciseReqresRegistration() {
        endpoint = "/api/register";
        url = baseUrl + endpoint;

        Register register = new Register("eve.holt@reqres.in", "pistol");

        Map<String, String> headers =
                Map.of(
                        "content-type", "application/json; charset=utf-8"
                );

        try {
            HttpResponse<String> httpResponse = HttpConnection
                    .request
                    .openUrl(url)
                    .setHeaders(headers)
                    .setBodyPublisher(register)
                    .build(RequestMethod.POST);

            System.out.println("Request: " + httpResponse.request());
            System.out.println("Response code: " + httpResponse.statusCode());
            System.out.println("Response headers: " + httpResponse.headers());
            System.out.println("Response body: " + httpResponse.body());

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
