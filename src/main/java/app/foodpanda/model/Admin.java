package app.foodpanda.model;

import javax.persistence.*;

@Entity
@Table(name = "admin")
public class Admin extends User{

    @OneToOne(mappedBy = "admin",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Restaurant restaurant;

    public Admin(String username, String password) {
        super(username, password);
    }

    public Admin(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Admin() {

    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
