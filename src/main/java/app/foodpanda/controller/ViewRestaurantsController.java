package app.foodpanda.controller;


import app.foodpanda.model.RestaurantDTO;
import app.foodpanda.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ViewRestaurantsController {

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/restaurants/view")
    public List<RestaurantDTO> viewAll(){
        System.out.println("view all restaurants");
        return restaurantService.findAll();
    }


}
