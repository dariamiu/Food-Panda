package app.foodpanda.service;

import app.foodpanda.dto.FoodDTO;
import app.foodpanda.dto.OrderDTO;
import app.foodpanda.dto.StatusDTO;
import app.foodpanda.model.*;
import app.foodpanda.repository.CustomerRepository;
import app.foodpanda.repository.FoodRepository;
import app.foodpanda.repository.OrderRepository;
import app.foodpanda.repository.RestaurantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 Service class containing the methods with the application logic that uses data from the order table
 @author Daria Miu
 */
@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    FoodRepository foodRepository;

    Logger logger = LoggerFactory.getLogger(OrderService.class);

    /**
     * Method that transforms an object Food into an object FoodDTO that only contains Food information
     * relevant for the frontend
     * @param food object of type Food
     * @return object of type FoodDTO
     */
    private FoodDTO modelToDTOFood(Food food){
        logger.debug("Price of food {}, name of food {}, food to be transformed in dto",
                food.getPrice(),
                food.getName());
        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setName(food.getName());
        foodDTO.setPrice(food.getPrice());
        return  foodDTO;
    }

    /**
     * Method that transforms an object Order into an object OrderDTO that only contains Order information
     * relevant for the frontend
     * @param order object of type Order
     * @return object of type OrderDTO
     */
    public OrderDTO modelToDTOOrder(Order order){
        List<FoodDTO> foodDTOS = order.getFoods().stream().map(this::modelToDTOFood).collect(Collectors.toList());
        return new OrderDTO(order.getId_order_table(),order.getRestaurant().getName(),
                order.getCustomer().getUsername(),foodDTOS,order.getPrice(),order.getStatus(),
                order.getDate(),order.getTime());
    }

    /**
     * Method that gets the orders placed by a specific customer
     * @param customer customer's name
     * @return list of OrderDTOs
     */
    public List<OrderDTO> findAllByClient(String customer){
        Customer customer1 = customerRepository.findByUsername(customer);
        return orderRepository.findAllByCustomer(customer1).stream().map(this::modelToDTOOrder).
                collect(Collectors.toList());
    }

    /**
     * Method that returns the orders placed at a restaurant
     * @param restaurant the name of the restaurant
     * @return list of OrderDTOs
     */
    public List<OrderDTO> findAllByRestaurant(String restaurant){
        Restaurant restaurant1 = restaurantRepository.findByName(restaurant);
        return orderRepository.findAllByRestaurant(restaurant1).stream().map(this::modelToDTOOrder).
                collect(Collectors.toList());
    }

    /**
     * Method that saves an order in the database in the table order_table
     * @param orderDTO an object of type OrderDTO that will be converted into an object Order
     * @return the response for the front in a hashmap structure with 2 entries withe the keys "message" that has as
     * value a string and "success" that has as value a bool depending on the success of the operation.
     */
    public HashMap<String, Object> placeOrder(OrderDTO orderDTO){

        HashMap<String, Object> response = new HashMap<>();

        if (orderDTO.getFoods().isEmpty()){
            logger.warn("The food list for the order is empty");
        }

        Customer customer = customerRepository.findByUsername(orderDTO.getClient());
        Restaurant restaurant = restaurantRepository.findByName(orderDTO.getRestaurant());
        List<Food> foods = new ArrayList<>();

        for (FoodDTO food : orderDTO.getFoods()) {
                //System.out.println(food.getName());
                Food foodModel = foodRepository.findByNameAndRestaurant(food.getName(),restaurant);
                //System.out.println("name: " + foodModel.getName());
                foods.add(foodModel);
        }

        Order newOrder = new Order("PENDING",
                LocalDate.now(),
                LocalTime.now(),
                foods,
                customer,
                restaurant,
                orderDTO.getPrice());
        orderRepository.save(newOrder);

        for (int i = 0; i < foods.size() ; i++){
                foods.get(i).addOrder(newOrder);
                foodRepository.save(foods.get(i));
        }

        response.put("message", "order placed successfully!\nsee status in \"my orders\" section");
        response.put("success",true);

        logger.info("New order was placed at the restaurant : {}, by the client :{}",
                orderDTO.getRestaurant(),
                orderDTO.getClient());
        return response;
    }

    /**
     * Method to update the status of order in the database when the admin changes it
     * @param statusDTO object of type StatusDTO containing info about the new status
     * @return the response for the front in a hashmap structure with 2 entries withe the keys "message" that has as
     * value a string and "success" that has as value a bool depending on the success of the operation.
     */
    public HashMap<String,Object> updateStatus(StatusDTO statusDTO){

        HashMap<String,Object> response = new HashMap<>();
        logger.info("Changing status for order : {}, the new status will be : {}",
                statusDTO.getId_order(),
                statusDTO.getStatus());

        try {
            Order order = orderRepository.findOrderByMyId(statusDTO.getId_order());
            order.setStatus(statusDTO.getStatus());
            orderRepository.save(order);
            response.put("message","Status Changed");
            response.put("success", true);

        } catch (Exception e) {
            response.put("message", e.getMessage());
            response.put("success",false);
            logger.error(e.getMessage());
            return response;
        }

        return response;
    }
}
