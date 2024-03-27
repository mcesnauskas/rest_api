package lt.mindaugas.rest_api.common;

import com.google.gson.Gson;

import java.io.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class Util {

    public static BiFunction<String, Class<?>, Object> jsonToObj =
            (json, objClass) -> {
                Gson gson = new Gson();
                return gson.fromJson(json, objClass);
            };

    public static Function<Object, String> objToJson =
            (obj) -> {
                Gson gson = new Gson();
                return gson.toJson(obj);
            };

    public static Consumer<Object> saveToJsonFile =
            (obj) -> {
                FileWriter writer = null;
                try {
                    writer = new FileWriter("./demo/%s.json".formatted(obj.getClass().getSimpleName()));
                    Gson nGson = new Gson();
                    nGson.toJson(obj, writer);

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (writer != null) {
                        try {
                            writer.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };

    public static Function<Class<?>, Object> readJsonFromFile =
            (objClass) -> {
                Reader reader = null;
                Object object = null;

                try {
                    Gson gson = new Gson();
                    reader = new FileReader("./demo/%s.json".formatted(objClass.getSimpleName()));
                    object = gson.fromJson(reader, objClass);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return object;
            };
}
