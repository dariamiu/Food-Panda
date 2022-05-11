package app.foodpanda.controller;


import app.foodpanda.dto.OrderDTO;
import app.foodpanda.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MakeOrderController {

    @Autowired
    OrderService orderService;

    Logger logger = LoggerFactory.getLogger(MakeOrderController.class);

    @PostMapping("/orders/make")
    HashMap<String, Object> placeOrder(@RequestBody OrderDTO orderDTO){
        logger.info("Executing request for making order");
        return orderService.placeOrder(orderDTO);
    }
}
