package app.foodpanda.controller;

import app.foodpanda.model.OrderDTO;
import app.foodpanda.model.StatusDTO;
import app.foodpanda.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ViewOrdersController {

    @Autowired
    OrderService orderService;

    @GetMapping("/orders-client/{client}")
    List<OrderDTO> getOrdersClient(@PathVariable String client){
        return orderService.findAllByClient(client);
    }

    @GetMapping("/orders-restaurant/{restaurant}")
    List<OrderDTO> getOrdersRestaurant(@PathVariable String restaurant){
        return orderService.findAllByRestaurant(restaurant);
    }

    @PutMapping("/order/update/{id}/{status}")
    HashMap<String,Object> updateStatus(@PathVariable Integer id, @PathVariable String status){
        System.out.println(id);
        return orderService.updateStatus(new StatusDTO(id,status));
    }
}
