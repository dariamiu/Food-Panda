package app.foodpanda.repository;

import app.foodpanda.model.Food;
import app.foodpanda.model.Restaurant;

import java.util.List;

public interface FoodRepository extends AbstractRepository<Food>{

    List<Food> findAllByRestaurant(Restaurant restaurant);

    Food findByNameAndRestaurant(String name, Restaurant restaurant);

 }
