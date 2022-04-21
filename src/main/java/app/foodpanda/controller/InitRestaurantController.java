package app.foodpanda.controller;

import app.foodpanda.model.RestaurantDTO;
import app.foodpanda.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class InitRestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/restaurants/{admin}")
    RestaurantDTO getRestaurantByAdmin(@PathVariable String admin){
        System.out.println(admin);
        return restaurantService.findByAdmin(admin);
    }
}
