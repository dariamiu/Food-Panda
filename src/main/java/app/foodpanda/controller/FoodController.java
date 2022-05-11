package app.foodpanda.controller;

import app.foodpanda.dto.CategoryDTO;
import app.foodpanda.dto.FoodDTO;
import app.foodpanda.model.MenuCategory;
import app.foodpanda.service.CategoryService;
import app.foodpanda.service.FoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FoodController {

    @Autowired
    FoodService foodService;

    @Autowired
    CategoryService categoryService;

    Logger logger = LoggerFactory.getLogger(FoodController.class);

    @PostMapping("/food/add")
    public HashMap<String, Object> addFood(@RequestBody FoodDTO food){
        logger.info("Executing the post request for adding food");
        logger.debug("The food name is {}, the restaurant name is {}, the category is {}",
                food.getName(),
                food.getRestaurant(),
                food.getRestaurant());
        //System.out.println(food.getName());
        //System.out.println(food.getRestaurant());
        //System.out.println(food.getCategory());
        return foodService.addFood(food);
    }

    @GetMapping("/food/categories")
    public List<CategoryDTO> getAllCategories(){
        logger.info("Executing the get request for getting the categories names");
        return categoryService.findAll();
    }


    @GetMapping("/food/view/{name}")
    public List<MenuCategory> viewAll(@PathVariable String name){
        logger.info("Executing the get request for getting the foods from restaurant {}", name);
        return foodService.findAllByRestaurant(name);
    }
}
