package app.foodpanda.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "zone")
public class Zone {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer zone_id;

    @Column
    private String name;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "zone_restaurant", joinColumns = @JoinColumn(name = "id_zone"),
    inverseJoinColumns = @JoinColumn(name = "id_restaurant"))
    private List<Restaurant> restaurants;

    public Zone(String name, List<Restaurant> restaurants) {
        this.name = name;
        this.restaurants = restaurants;
    }

    public Zone() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public void addRestaurant(Restaurant restaurant){
        restaurants.add(restaurant);
    }
}
