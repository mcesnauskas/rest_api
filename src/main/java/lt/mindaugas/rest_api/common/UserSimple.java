package lt.mindaugas.rest_api.common;

public class UserSimple {
    private int id;
    private String fullName;
    private String email;

    public UserSimple(int id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }
}
