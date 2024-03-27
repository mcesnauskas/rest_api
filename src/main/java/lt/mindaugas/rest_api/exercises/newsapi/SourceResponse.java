package lt.mindaugas.rest_api.exercises.newsapi;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class SourceResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("sources")
    private List<SourceDetails> sources;
}
