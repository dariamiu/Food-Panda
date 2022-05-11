package app.foodpanda.service;

import app.foodpanda.dto.FoodDTO;
import app.foodpanda.model.*;
import app.foodpanda.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 Service class containing the methods with the application logic that uses data from the food table
 @author Daria Miu
 */
@Service
public class FoodService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    FoodRepository foodRepository;


    Logger logger = LoggerFactory.getLogger(FoodService.class);

    /**
     * Method to save an admin to the database
     * @param foodDTO an object of type FoodDTO to be saved in the database in the table food
     * @return the response for the front in a hashmap structure with 2 entries withe the keys "message" that has as
     * value a string and "success" that has as value a bool depending on the success of the operation.
     */
    public HashMap<String,Object> addFood(FoodDTO foodDTO){

        HashMap<String, Object> response = new HashMap<String, Object>();

        logger.info("Creating food for the restaurant: {}", foodDTO.getRestaurant());

        Restaurant restaurant = restaurantRepository.findByName(foodDTO.getRestaurant());
        Category  category = categoryRepository.findByName(foodDTO.getCategory());
        List<Food> foods = foodRepository.findAllByRestaurant(restaurant);
        Food food = foods.stream()
                .filter(f -> foodDTO.getName().equals(f.getName()))
                .findAny()
                .orElse(null);

        try{
            if (food != null){
                response.put("message", "You have already created a food with that name "+ foodDTO.getName());
                response.put("success", false);
                logger.warn("The food was not created because there already exists a food with the name : {}," +
                        " at the restaurant : {}", foodDTO.getName(), foodDTO.getRestaurant());
            } else {
                Food newFood = new Food(foodDTO.getName(), category, foodDTO.getPrice(),
                        foodDTO.getDescription(),restaurant);

                foodRepository.save(newFood);

                if (!restaurant.getCategories().contains(category)){
                    restaurant.addCategory(category);
                    category.addRestaurant(restaurant);

                    restaurantRepository.save(restaurant);
                    categoryRepository.save(category);
                }

                response.put("message", "Food created!");
                response.put("success", true);

                logger.debug("A new food has been created with the name : {}, the price : {}, the description : {}," +
                        "the category: {},at the restaurant: {}",
                        newFood.getName(),
                        newFood.getPrice(),
                        newFood.getDescription(),
                        newFood.getCategory(),
                        newFood.getRestaurant());

            }
            return response;
        } catch (Exception e) {
            response.put("message", e.getMessage());
            response.put("success",false);
            logger.error(e.getMessage());
            return response;
        }

    }

    /**
     * Method to get the menu from a restaurant
     * @param restaurantName the name of the restaurant
     * @return list of MenuCategory, each MenuCategory has a name an contains a list with the foods
     */
    public List<MenuCategory> findAllByRestaurant(String restaurantName){

        HashMap<String , List<FoodDTO>> menu = new HashMap<>();

        Restaurant restaurant = restaurantRepository.findByName(restaurantName);

        ///get all foods from restaurant
        List<Food> foods = foodRepository.findAllByRestaurant(restaurant);

        if (foods.isEmpty()) {
            logger.warn("There are no foods yet at the restaurant : {}", restaurantName);
        }

        for (Food food : foods) {
            String category = food.getCategory().getName();

            //get the list of foods that are already in that category
            List<FoodDTO> foodList = menu.get(category);
            if (foodList == null){
                foodList = new ArrayList<>();
            }

            //add the food to the list
            foodList.add(new FoodDTO(food.getName(), food.getPrice(), food.getDescription(),
                    food.getCategory().getName(), food.getRestaurant().getName()));
            //put the new food list to the category
            menu.put(category,foodList);
        }

        List<MenuCategory> menuCategories = new ArrayList<>();

        //create a list of MenuCategory objects from the hashmap
        menu.forEach((key, value) -> menuCategories.add(new MenuCategory(key, value)));

        return menuCategories;
    }
}
