package app.foodpanda.controller;

import app.foodpanda.dto.RestaurantDTO;
import app.foodpanda.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(InitRestaurantController.class);

    @GetMapping("/restaurants/{admin}")
    RestaurantDTO getRestaurantByAdmin(@PathVariable String admin){
        logger.info("Executing get request for getting the restaurant name for admin {}", admin);
        return restaurantService.findByAdmin(admin);
    }
}
