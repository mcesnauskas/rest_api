package lt.mindaugas.rest_api.exercises;

import java.util.List;

public class DataClass {
    private DetailsClass data;

    public DataClass(DetailsClass data) {
        this.data = data;
    }

    public DetailsClass getData() {
        return data;
    }

    @Override
    public String toString() {
        return "DataClass{" +
                "data=" + data +
                '}';
    }
}
