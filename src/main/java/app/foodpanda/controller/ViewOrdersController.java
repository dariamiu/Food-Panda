package app.foodpanda.controller;

import app.foodpanda.dto.OrderDTO;
import app.foodpanda.dto.StatusDTO;
import app.foodpanda.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ViewOrdersController {

    @Autowired
    OrderService orderService;

    Logger logger = LoggerFactory.getLogger(ViewOrdersController.class);

    @GetMapping("/orders-client/{client}")
    List<OrderDTO> getOrdersClient(@PathVariable String client){
        logger.debug("Executing request for getting the orders of client {}", client);
        return orderService.findAllByClient(client);
    }

    @GetMapping("/orders-restaurant/{restaurant}")
    List<OrderDTO> getOrdersRestaurant(@PathVariable String restaurant){
        logger.debug("Executing request for getting the orders of restaurant {}", restaurant);
        return orderService.findAllByRestaurant(restaurant);
    }

    @PutMapping("/order/update/{id}/{status}")
    HashMap<String,Object> updateStatus(@PathVariable Integer id, @PathVariable String status){
        logger.debug("Executing request for putting the status {} for order {}", status, id);
        return orderService.updateStatus(new StatusDTO(id,status));
    }
}
