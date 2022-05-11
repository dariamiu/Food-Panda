package app.foodpanda.controller;

import app.foodpanda.dto.RestaurantDTO;
import app.foodpanda.dto.ZoneDTO;
import app.foodpanda.service.RestaurantService;
import app.foodpanda.service.ZoneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(AddRestaurantController.class);

    @PostMapping("/restaurants/add")
    HashMap<String, Object> addRestaurant(@RequestBody RestaurantDTO restaurant){
        logger.info("Executing the post request for adding restaurant");
        return restaurantService.addRestaurant(restaurant);
    }

    @GetMapping("/zones")
    List<ZoneDTO> getZones(){
        logger.info("Executing the get request for getting the zones");
        return zoneService.findAll();
    }

}
