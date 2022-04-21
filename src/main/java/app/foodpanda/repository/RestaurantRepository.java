package app.foodpanda.repository;

import app.foodpanda.model.Admin;
import app.foodpanda.model.Restaurant;

public interface RestaurantRepository extends AbstractRepository<Restaurant> {

    Restaurant findByName(String name);

    Restaurant findByAdmin(Admin admin);
}
