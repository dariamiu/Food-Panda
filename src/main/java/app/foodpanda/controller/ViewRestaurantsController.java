package app.foodpanda.controller;


import app.foodpanda.dto.RestaurantDTO;
import app.foodpanda.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(ViewRestaurantsController.class);

    @GetMapping("/restaurants/view")
    public List<RestaurantDTO> viewAll(){
        logger.debug("Executing request for getting the restaurants");
        return restaurantService.findAll();
    }


}
