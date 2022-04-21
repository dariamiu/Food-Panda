package app.foodpanda.model;

public class StatusDTO {
    private Integer id_order;

    private String status;

    public StatusDTO() {
    }

    public StatusDTO(Integer id_order, String status) {
        this.id_order = id_order;
        this.status = status;
    }

    public void setId_order(Integer id_order) {
        this.id_order = id_order;
    }

    public Integer getId_order() {
        return id_order;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
