package lt.mindaugas.rest_api.examples.reqres_model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lt.mindaugas.rest_api.exercises.Support;

@AllArgsConstructor
@Data
public class UserResponse {
    @SerializedName("data")
    private UserDetails data;
    @SerializedName("support")
    private Support support;
}
