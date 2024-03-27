package lt.mindaugas.rest_api.examples.reqres_model;

import lt.mindaugas.rest_api.examples.Resource;

import java.util.List;

public class UsersResponse {
    private String page;
    private Integer per_page;
    private Integer total;
    private Integer total_pages;
    private List<UserDetails> data;

    public UsersResponse(
            String page,
            Integer per_page,
            Integer total,
            Integer total_pages,
            List<UserDetails> data
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

    public void setPage(String page) {
        this.page = page;
    }

    public Integer getPer_page() {
        return per_page;
    }

    public void setPer_page(Integer per_page) {
        this.per_page = per_page;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }

    public List<UserDetails> getData() {
        return data;
    }

    public void setData(List<UserDetails> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "UsersResponse{" +
                "page='" + page + '\'' +
                ", per_page=" + per_page +
                ", total=" + total +
                ", total_pages=" + total_pages +
                ", data=" + data +
                '}';
    }
}
