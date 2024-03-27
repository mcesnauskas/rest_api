package lt.mindaugas.rest_api;

import lt.mindaugas.rest_api.examples.GsonExample;
import lt.mindaugas.rest_api.examples.RestApiExample;
import lt.mindaugas.rest_api.exercises.GsonExercises;
import lt.mindaugas.rest_api.exercises.NewsApiExercise;

public class Main {
    public static void main(String[] args) {
//        GsonExample.run();
//        GsonExercises.doUserDetails();
//        RestApiExample.run();
        NewsApiExercise.getNewsSources();
    }
}
