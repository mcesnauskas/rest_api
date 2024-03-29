package lt.mindaugas.rest_api.exercises.authorisation_exercise;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Register {
    @SerializedName("email")
    private String newEmail;
    @SerializedName("password")
    private String newPassword;
}
