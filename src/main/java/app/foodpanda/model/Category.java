package app.foodpanda.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id_category;

    @Column
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Food> foods;

    @ManyToMany(cascade =  CascadeType.MERGE)
    @JoinTable(name = "category_restaurant", joinColumns = @JoinColumn(name = "id_category"),
    inverseJoinColumns = @JoinColumn(name = "id_restaurant"))
    private List<Restaurant> restaurants;

    public Category(String name, List<Food> foods) {
        this.name = name;
        this.foods = foods;
    }

    public Category() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
    }
}
