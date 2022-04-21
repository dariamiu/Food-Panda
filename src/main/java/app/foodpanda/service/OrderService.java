package app.foodpanda.service;

import app.foodpanda.model.*;
import app.foodpanda.repository.CustomerRepository;
import app.foodpanda.repository.FoodRepository;
import app.foodpanda.repository.OrderRepository;
import app.foodpanda.repository.RestaurantRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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

    private FoodDTO modelToDTOFood(Food food){
        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setName(food.getName());
        foodDTO.setPrice(food.getPrice());
        return  foodDTO;
    }

    private OrderDTO modelToDTOOrder(Order order){
        List<FoodDTO> foodDTOS = order.getFoods().stream().map(this::modelToDTOFood).collect(Collectors.toList());
        return new OrderDTO(order.getId_order_table(),order.getRestaurant().getName(),
                order.getCustomer().getUsername(),foodDTOS,order.getPrice(),order.getStatus(), order.getDate(),order.getTime());
    }

    public List<OrderDTO> findAllByClient(String customer){
        Customer customer1 = customerRepository.findByUsername(customer);
        return orderRepository.findAllByCustomer(customer1).stream().map(this::modelToDTOOrder).collect(Collectors.toList());
    }

    public List<OrderDTO> findAllByRestaurant(String restaurant){
        Restaurant restaurant1 = restaurantRepository.findByName(restaurant);
        return orderRepository.findAllByRestaurant(restaurant1).stream().map(this::modelToDTOOrder).collect(Collectors.toList());
    }

    public HashMap<String, Object> placeOrder(OrderDTO orderDTO){
        HashMap<String, Object> response = new HashMap<>();

            System.out.println("restuarant " + orderDTO.getRestaurant());
             System.out.println("client " + orderDTO.getClient());
            Customer customer = customerRepository.findByUsername(orderDTO.getClient());
            Restaurant restaurant = restaurantRepository.findByName(orderDTO.getRestaurant());

            List<Food> foods = new ArrayList<>();

            for (FoodDTO food : orderDTO.getFoods()) {
                System.out.println(food.getName());
                Food foodModel = foodRepository.findByNameAndRestaurant(food.getName(),restaurant);
                System.out.println("name: " + foodModel.getName());
                foods.add(foodModel);
            }

            Order newOrder = new Order("PENDING", LocalDate.now(), LocalTime.now(), foods, customer,restaurant,orderDTO.getPrice());
            orderRepository.save(newOrder);


            for(int i = 0; i < foods.size() ; i++){
                foods.get(i).addOrder(newOrder);
                foodRepository.save(foods.get(i));
            }

            response.put("message", "order placed successfully!\nsee status in \"my orders\" section");
            response.put("success",true);


        return response;
    }

    public HashMap<String,Object> updateStatus(StatusDTO statusDTO){
        HashMap<String,Object> response = new HashMap<>();

        try{
            Order order = orderRepository.findOrderByMyId(statusDTO.getId_order());

            order.setStatus(statusDTO.getStatus());

            orderRepository.save(order);
            response.put("message","Status Changed");
            response.put("success", true);

        }catch (Exception e) {
            // TODO: handle exception
            response.put("message", e.getMessage());
            response.put("success",false);
            return response;
        }

        return response;
    }
}
