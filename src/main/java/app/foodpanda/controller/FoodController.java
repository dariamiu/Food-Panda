package app.foodpanda.controller;

import app.foodpanda.model.CategoryDTO;
import app.foodpanda.model.FoodDTO;
import app.foodpanda.model.MenuCategory;
import app.foodpanda.service.CategoryService;
import app.foodpanda.service.FoodService;
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

    @PostMapping("/food/add")
    public HashMap<String, Object> addFood(@RequestBody FoodDTO food){
        System.out.println("add food");
        System.out.println(food.getName());
        System.out.println(food.getRestaurant());
        System.out.println(food.getCategory());
        return foodService.addFood(food);
    }

    @GetMapping("/food/categories")
    public List<CategoryDTO> getAllCategories(){
        System.out.println("get categories");
        return categoryService.findAll();
    }


    @GetMapping("/food/view/{name}")
    public List<MenuCategory> viewAll(@PathVariable String name){
        System.out.println("view all");
        return foodService.findAllByRestaurant(name);
    }
}
