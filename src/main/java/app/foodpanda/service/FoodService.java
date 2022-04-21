package app.foodpanda.service;

import app.foodpanda.model.*;
import app.foodpanda.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    FoodRepository foodRepository;


    public HashMap<String,Object> addFood(FoodDTO foodDTO){
        HashMap<String, Object> response = new HashMap<String, Object>();

        System.out.println(foodDTO.getRestaurant());

        Restaurant restaurant = restaurantRepository.findByName(foodDTO.getRestaurant());

        Category  category = categoryRepository.findByName(foodDTO.getCategory());



        List<Food> foods = foodRepository.findAllByRestaurant(restaurant);

        Food food = foods.stream()
                .filter(f -> foodDTO.getName().equals(f.getName()))
                .findAny()
                .orElse(null);

        try{
            if(food != null){
                response.put("message", "You have already created a food with that name "+ foodDTO.getName());
                response.put("success", false);
                return response;
            } else {
                Food newFood = new Food(foodDTO.getName(), category, foodDTO.getPrice(),foodDTO.getDescription(),restaurant);
                foodRepository.save(newFood);

                if(!restaurant.getCategories().contains(category)){
                    restaurant.addCategory(category);
                    category.addRestaurant(restaurant);

                    restaurantRepository.save(restaurant);
                    categoryRepository.save(category);
                }

                response.put("message", "Food created!");
                response.put("success", true);
                return response;
            }
        } catch (Exception e) {
            // TODO: handle exception
            response.put("message", e.getMessage());
            response.put("success",false);
            return response;
        }

    }

    public List<MenuCategory> findAllByRestaurant(String restaurantName){

        HashMap<String , List<FoodDTO>> menu = new HashMap<>();

        Restaurant restaurant = restaurantRepository.findByName(restaurantName);

        ///get all foods from restaurant
        List<Food> foods = foodRepository.findAllByRestaurant(restaurant);

        for (Food food : foods) {
            String category = food.getCategory().getName();
            List<FoodDTO> foodList = menu.get(category);
            if(foodList == null){
                foodList = new ArrayList<>();
            }
            foodList.add(new FoodDTO(food.getName(), food.getPrice(), food.getDescription(), food.getCategory().getName(),
                     food.getRestaurant().getName()));
            menu.put(category,foodList);
        }

        List<MenuCategory> menuCategories = new ArrayList<>();
        menu.entrySet().forEach(menuItem -> {
            menuCategories.add(new MenuCategory(menuItem.getKey(), menuItem.getValue()));
        } );

        return menuCategories;
    }
}
