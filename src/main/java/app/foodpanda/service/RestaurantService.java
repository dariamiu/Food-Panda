package app.foodpanda.service;

import app.foodpanda.model.*;
import app.foodpanda.repository.AdminRepository;
import app.foodpanda.repository.RestaurantRepository;
import app.foodpanda.repository.ZoneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.foodpanda.dto.RestaurantDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 Service class containing the methods with the application logic that uses data from the restaurant table
 @author Daria Miu
 */
@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    ZoneRepository zoneRepository;


    Logger logger = LoggerFactory.getLogger(RestaurantService.class);

    /**
     * Method to save a new restaurant in the database
     * @param restaurantDTO an object of type RestaurantDTO that will be converted into an object Restaurant than saved
     * in the restaurant table
     * @return the response for the front in a hashmap structure with 2 entries withe the keys "message" that has as
     * value a string and "success" that has as value a bool depending on the success of the operation.
     */
    public HashMap<String,Object> addRestaurant(RestaurantDTO restaurantDTO){

        HashMap<String, Object> response = new HashMap<String, Object>();

        Restaurant restaurant = restaurantRepository.findByName(restaurantDTO.getName());
        Admin admin = adminRepository.findByUsername(restaurantDTO.getAdminName());
        Restaurant restaurantWithAdmin = restaurantRepository.findByAdmin(admin);

            try{
                if (restaurantWithAdmin != null){
                    response.put("message", "You already have a restaurant "+ restaurantDTO.getName());
                    response.put("success", false);
                    logger.info("Restaurant {} will not be created because the user {} already has a restaurant",
                            restaurantDTO.getName(), restaurantDTO.getAdminName());
                    return response;
                } else if (restaurant != null) {
                    response.put("message", "There already exists a restaurant with the name "+ restaurantDTO.getName());
                    response.put("success", false);
                    logger.warn("Restaurant with name {} will not be created because there already exists a restaurant " +
                            "with that name", restaurantDTO.getName());
                    return response;
                }
                else {
                    List<Zone> zones = new ArrayList<>();
                    for (String deliveryZone : restaurantDTO.getDeliveryZones()) {
                        //System.out.println("del zone : " + deliveryZone + "\n");
                        zones.add(zoneRepository.findByName(deliveryZone));
                    }

                    restaurantRepository.save(new Restaurant(restaurantDTO.getName(), restaurantDTO.getLocation(),zones, admin));
                    Restaurant restaurant1 = restaurantRepository.findByName(restaurantDTO.getName());

                    System.out.println(zones.get(0).getName());
                    System.out.println(zones.get(1).getName());
                    int len = zones.size();
                    for (int i = 0; i < len; i++) {
                        //System.out.println("del zone 2 :" + zones.get(i).getName() + "\n");
                        zones.get(i).addRestaurant(restaurant1);
                        zoneRepository.save(zones.get(i));
                    }
                    response.put("message", "Restaurant created!\n You can add products to your menu now!");
                    response.put("success", true);
                    logger.info("Restaurant with name {} was created by admin {}",
                            restaurantDTO.getName(),
                            restaurantDTO.getAdminName());
                    return response;
                }
                } catch (Exception e) {
                    response.put("message", e.getMessage());
                    response.put("success",false);
                    logger.error(e.getMessage());
                    return response;
                }
    }


    /**
     * Method to find all the restaurants in the database
     * @return list of RestaurantDTO objects, RestaurantDTO object contains only the information relevant to be display
     * in the fronted from a Restaurant object
     */
    public List<RestaurantDTO> findAll(){

        List<Restaurant> restaurants = restaurantRepository.findAll();
        if(restaurants.isEmpty()){
            logger.warn("There are no restaurants in the database");
        }
        List<RestaurantDTO> restaurantsDTO = new ArrayList<>();

        for (Restaurant restaurant : restaurants) {
            RestaurantDTO restaurantDTO = new RestaurantDTO();
            restaurantDTO.setName(restaurant.getName());
            restaurantDTO.setLocation(restaurant.getLocation());
            restaurantsDTO.add(restaurantDTO);
        }

        return restaurantsDTO;
    }


    /**
     * Method to find the restaurant of an admin
     * @param adminName name of admin
     * @return object of type RestaurantDTO
     */
    public RestaurantDTO findByAdmin(String adminName){

        Admin admin = adminRepository.findByUsername(adminName);
        Restaurant restaurant = restaurantRepository.findByAdmin(admin);
        if(restaurant == null){
            logger.warn("The admin {} does not have a restaurant", adminName);
            return null;
        }
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setName(restaurant.getName());
        restaurantDTO.setLocation(restaurant.getLocation());
        restaurantDTO.setAdminName(adminName);
        return restaurantDTO;
    }
}
