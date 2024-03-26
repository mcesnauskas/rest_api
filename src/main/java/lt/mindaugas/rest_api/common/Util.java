package lt.mindaugas.rest_api.common;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Consumer;

public class Util {

    public static Consumer<Object> saveToJsonFile =
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
}
