package app.foodpanda.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "food")
public class Food {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id_food;

    @Column
    private String name;

    @Column
    private Float price;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_category")
    private Category category;


    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "food_order", joinColumns = @JoinColumn(name = "id_food"),
    inverseJoinColumns = @JoinColumn(name = "id_order_table"))
    private List<Order> orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_restaurant")
    private Restaurant restaurant;

    public Food() {

    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Food(String name, Category category, Float price, String description, Restaurant restaurant) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.restaurant = restaurant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId_food() {
        return id_food;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setId_food(Integer id_food) {
        this.id_food = id_food;
    }

    public String getDescription() {
        return description;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    public void addOrder(Order order){
        orders.add(order);
    }

}
