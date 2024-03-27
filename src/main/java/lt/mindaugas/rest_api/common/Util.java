package lt.mindaugas.rest_api.common;

import com.google.gson.Gson;

import java.io.*;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
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

    public static BiFunction<String, Map<String, String>, URI> uriBuilder =
            (url, queryParam) -> {
                StringBuilder queryBuilder = new StringBuilder();



                for (Map.Entry<String, String> entry : queryParam.entrySet()) {
                    if (!queryBuilder.isEmpty()) {
                        queryBuilder.append("&");
                    }
                    queryBuilder
                            .append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8))
                            .append("=")
                            .append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
                }

                return URI.create(url + "?" + queryBuilder);
            };

//    private static Function<String, String> stringEncoder =
//            (value) -> URLEncoder.encode(value, StandardCharsets.UTF_8);
}
