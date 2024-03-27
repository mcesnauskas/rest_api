package lt.mindaugas.rest_api.exercises.newsapi;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SourceDetails {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("url")
    private String url;
    @SerializedName("category")
    private String category;
    @SerializedName("language")
    private String language;
    @SerializedName("country")
    private String country;
}
