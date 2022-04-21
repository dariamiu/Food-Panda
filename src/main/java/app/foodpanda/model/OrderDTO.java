package app.foodpanda.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class OrderDTO {

    private Integer id_order;

    private String restaurant;

    private String client;

    private List<FoodDTO> foods;

    private String status;

    private Float price;

    private LocalDate date;

    private LocalTime time;

    public OrderDTO(Integer id_order, String restaurant, String client, List<FoodDTO> foods, Float price, String status, LocalDate date, LocalTime time) {
        this.id_order = id_order;
        this.restaurant = restaurant;
        this.client = client;
        this.foods = foods;
        this.status = status;
        this.price = price;
        this.date = date;
        this.time = time;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public List<FoodDTO> getFoods() {
        return foods;
    }

    public void setFoods(List<FoodDTO> foods) {
        this.foods = foods;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId_order() {
        return id_order;
    }

    public void setId_order(Integer id_order) {
        this.id_order = id_order;
    }
}
