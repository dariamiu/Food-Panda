package app.foodpanda.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id_restaurant;

    @Column
    private String name;

    @Column
    private String location;

    @ManyToMany(mappedBy = "restaurants")
    private List<Zone> zones;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Food> foods;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Order> orders;

    @ManyToMany(mappedBy = "restaurants")
    private List<Category> categories;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private Admin admin;

    public Restaurant(String name, String location, List<Zone> zones, Admin admin) {
        this.name = name;
        this.location = location;
        this.zones = zones;
        this.admin = admin;
    }

    public Restaurant() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Zone> getZones() {
        return zones;
    }

    public void setZones(List<Zone> zones) {
        this.zones = zones;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Admin getAdmin() {
        return admin;
    }


    public void addCategory(Category category){
        categories.add(category);
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Integer getId_restaurant() {
        return id_restaurant;
    }

    public void addOrder(Order order){
        orders.add(order);
    }
}
