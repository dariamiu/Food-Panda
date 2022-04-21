package app.foodpanda.service;

import app.foodpanda.model.*;
import app.foodpanda.repository.AdminRepository;
import app.foodpanda.repository.RestaurantRepository;
import app.foodpanda.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    ZoneRepository zoneRepository;


    public HashMap<String,Object> addRestaurant(RestaurantDTO restaurantDTO){
        HashMap<String, Object> response = new HashMap<String, Object>();
        System.out.println(restaurantDTO.getName() + " " + restaurantDTO.getLocation());

        Restaurant restaurant = restaurantRepository.findByName(restaurantDTO.getName());
        Admin admin = adminRepository.findByUsername(restaurantDTO.getAdminName());
        Restaurant restaurantWithAdmin = restaurantRepository.findByAdmin(admin);

            try{
                if(restaurantWithAdmin != null){
                    response.put("message", "You already have a restaurant "+ restaurantDTO.getName());
                    response.put("success", false);
                    return response;
                }else if(restaurant != null) {
                    response.put("message", "There already exists a restaurant with the name "+ restaurantDTO.getName());
                    response.put("success", false);
                    return response;
                }
                else {
                    List<Zone> zones = new ArrayList<>();
                    for (String deliveryZone : restaurantDTO.getDeliveryZones()) {
                        System.out.println("del zone : " + deliveryZone + "\n");
                        zones.add(zoneRepository.findByName(deliveryZone));
                    }

                    restaurantRepository.save(new Restaurant(restaurantDTO.getName(), restaurantDTO.getLocation(),zones, admin));
                    Restaurant restaurant1 = restaurantRepository.findByName(restaurantDTO.getName());

                    System.out.println(zones.get(0).getName());
                    System.out.println(zones.get(1).getName());
                    int len = zones.size();
                    for (int i = 0; i < len; i++) {
                        System.out.println("del zone 2 :" + zones.get(i).getName() + "\n");
                        zones.get(i).addRestaurant(restaurant1);
                        zoneRepository.save(zones.get(i));
                    }

                    response.put("message", "Restaurant created!\n You can add products to your menu now!");
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


    public List<RestaurantDTO> findAll(){
        List<Restaurant> restaurants = restaurantRepository.findAll();
        List<RestaurantDTO> restaurantsDTO = new ArrayList<>();

        for (Restaurant restaurant : restaurants) {
            RestaurantDTO restaurantDTO = new RestaurantDTO();
            restaurantDTO.setName(restaurant.getName());
            restaurantDTO.setLocation(restaurant.getLocation());
            restaurantsDTO.add(restaurantDTO);
        }

        return restaurantsDTO;
    }


    public RestaurantDTO findByAdmin(String adminName){
        Admin admin = adminRepository.findByUsername(adminName);
        Restaurant restaurant = restaurantRepository.findByAdmin(admin);
        if(restaurant == null){
            return null;
        }
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setName(restaurant.getName());
        restaurantDTO.setLocation(restaurant.getLocation());
        return restaurantDTO;
    }
}
