package lt.mindaugas.rest_api.examples;

import com.google.gson.Gson;
import lt.mindaugas.rest_api.common.UserSimple;
import lt.mindaugas.rest_api.common.Util;

public class GsonExample {
    public static void run() {
//        doExampleJsonSerialization();
        doExampleJsonDeserialization();

    }

    private static void doExampleJsonSerialization() {
        Gson gson = new Gson();
        String json01 = gson.toJson(5);
        String json02 = gson.toJson("Hello World!");
        String json03 = gson.toJson(new String[]{"Pirmadienis", "Antradienis", "Treciadienis"});

        System.out.println(json01);
        System.out.println(json02);
        System.out.println(json03);

        UserSimple user = new UserSimple(1, "Harry Potter", "harry@demo.com");
        String json04 = gson.toJson(user);
        System.out.println(json04);

        Util.saveToJsonFile.accept(user);
        Util.saveToJsonFile.accept("demo");
        Util.saveToJsonFile.accept(1);
        Util.saveToJsonFile.accept(new String[]{"demo01", "demo02"});
    }

    private static void doExampleJsonDeserialization() {
        String jsonText = """
                {
                    "id": 111,
                    "fullName": "Darth Vader",
                    "email": "darkempire@demo.com"
                }
                """;

        Gson gson = new Gson();
        UserSimple user = gson.fromJson(jsonText, UserSimple.class);
        System.out.println(user);

        user = (UserSimple) Util.readJsonFromFile.apply(UserSimple.class);
        System.out.println(user);
    }
}
