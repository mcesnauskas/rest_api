package lt.mindaugas.rest_api.examples.reqres_model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class UsersResponse {
    @SerializedName("page")
    private String somePage;
    @SerializedName("per_page")
    private Integer perPage;
    @SerializedName("total")
    private Integer total;
    @SerializedName("total_pages")
    private Integer totalPages;
    @SerializedName("data")
    private List<UserDetails> data;
}
