package lt.mindaugas.rest_api.exercises;

public class UserResponse {
    private UserDetails data;
    private Support support;

    public UserResponse(UserDetails data, Support support) {
        this.data = data;
        this.support = support;
    }

    public UserDetails getData() {
        return data;
    }

    public Support getSupport() {
        return support;
    }

    @Override
    public String toString() {
        return "JsonResponse{" +
                "data=" + data +
                ", support=" + support +
                '}';
    }
}
