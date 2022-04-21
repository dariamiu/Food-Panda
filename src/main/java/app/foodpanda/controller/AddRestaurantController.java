package app.foodpanda.controller;


import app.foodpanda.model.*;
import app.foodpanda.service.RestaurantService;
import app.foodpanda.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AddRestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    ZoneService zoneService;

   @PostMapping("/restaurants/add")
    HashMap<String, Object> addRestaurant(@RequestBody RestaurantDTO restaurant){
        System.out.println("restaurant added");
        return restaurantService.addRestaurant(restaurant);
    }

    @GetMapping("/zones")
    List<ZoneDTO> getZones(){
        System.out.println("got zones");
        return zoneService.findAll();
    }

}
