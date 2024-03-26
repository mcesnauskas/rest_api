package lt.mindaugas.rest_api.examples;

import com.google.gson.Gson;
import lt.mindaugas.rest_api.common.UserSimple;
import lt.mindaugas.rest_api.common.Util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.function.Consumer;

public class GsonExample {
    public static void run() {
        doExampleJsonSerialization();
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
}
