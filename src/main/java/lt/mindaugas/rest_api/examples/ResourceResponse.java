package lt.mindaugas.rest_api.examples;

import java.util.List;

public class ResourceResponse {

    private String page;
    private Integer per_page;
    private Integer total;
    private Integer total_pages;
    private List<Resource> data;

    public ResourceResponse(
            String page,
            Integer per_page,
            Integer total,
            Integer total_pages,
            List<Resource> data
    ) {
        this.page = page;
        this.per_page = per_page;
        this.total = total;
        this.total_pages = total_pages;
        this.data = data;
    }

    public String getPage() {
        return page;
    }

    public Integer getPer_page() {
        return per_page;
    }

    public Integer getTotal() {
        return total;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public List<Resource> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "ResourceResponse{" +
                "page='" + page + '\'' +
                ", per_page=" + per_page +
                ", total=" + total +
                ", total_pages=" + total_pages +
                ", data=" + data +
                '}';
    }
}
