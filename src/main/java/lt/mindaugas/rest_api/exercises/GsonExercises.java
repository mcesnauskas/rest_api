package lt.mindaugas.rest_api.exercises;

import com.google.gson.Gson;
import lt.mindaugas.rest_api.common.Util;

public class GsonExercises {
    public static void doUserDetails(){
        String userDetails = """
        {
            "data": {
                "id": 2,
                "email": "janet.weaver@reqres.in",
                "first_name": "Janet",
                "last_name": "Weaver",
                "avatar": "https://reqres.in/img/faces/2-image.jpg"
            },
            "support": {
                "url": "https://reqres.in/#support-heading",
                "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
            }
        }
        """;

        Gson gson = new Gson();
        UserResponse response = gson.fromJson(userDetails, UserResponse.class);
        System.out.println(response);

        //*
        Util.saveToJsonFile.accept(response);
        response = (UserResponse) Util.readJsonFromFile.apply(UserResponse.class);
        //*

        System.out.println(response.getData());
        System.out.println(response.getSupport());
    }
}
