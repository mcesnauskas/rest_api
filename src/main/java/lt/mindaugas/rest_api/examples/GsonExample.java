package lt.mindaugas.rest_api.examples;

import com.google.gson.Gson;
import lt.mindaugas.rest_api.common.UserSimple;

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

        Consumer<Object> saveToJsonFile =
                (obj) -> {
                    FileWriter writer = null;
                    try {
                        writer = new FileWriter("./demo/%s.json".formatted(obj.getClass().getSimpleName()));
                        Gson nGson = new Gson();
                        nGson.toJson(obj,writer);

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        assert writer != null;
                        try {
                            writer.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };

        saveToJsonFile.accept(user);
        saveToJsonFile.accept("demo");
        saveToJsonFile.accept(1);
        saveToJsonFile.accept(new String[]{"demo01", "demo02"});
    }
}
