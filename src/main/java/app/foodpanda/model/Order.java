package app.foodpanda.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "order_table")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id_order_table;

    @Column
    private String status;

    @Column
    private LocalDate date;

    @Column
    private LocalTime time;

    @Column
    private Float price;

    @ManyToMany(mappedBy = "orders")
    private List<Food> foods;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_restaurant")
    private Restaurant restaurant;

    public Order() {

    }

    public Order(String status, LocalDate date, LocalTime time, List<Food> foods, Customer customer,Restaurant restaurant,
                 Float price) {
        this.status = status;
        this.date = date;
        this.time = time;
        this.foods = foods;
        this.customer = customer;
        this.restaurant = restaurant;
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getId_order_table() {
        return id_order_table;
    }

    public void setId_order_table(Integer id_order_table) {
        this.id_order_table = id_order_table;
    }
}
