package lt.mindaugas.rest_api.exercises.authorisation_exercise;


import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Login {
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
}
