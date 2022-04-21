package app.foodpanda.controller;


import app.foodpanda.model.OrderDTO;
import app.foodpanda.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MakeOrder {

    @Autowired
    OrderService orderService;

    @PostMapping("/orders/make")
    HashMap<String, Object> placeOrder(@RequestBody OrderDTO orderDTO){
        return orderService.placeOrder(orderDTO);
    }
}
