package app.foodpanda.controller;

import app.foodpanda.model.Admin;
import app.foodpanda.model.Customer;
import app.foodpanda.service.AdminService;
import app.foodpanda.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    @Autowired
    CustomerService customerService;

    @Autowired
    AdminService adminService;

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/customers/register", method = RequestMethod.POST)
    HashMap<String,Object> newCustomer(@RequestBody Customer customer){
        logger.info("Executing the request for registering client");
        return customerService.saveCustomer(customer);
    }


    @RequestMapping(value = "/customers/login", method = RequestMethod.POST)
    HashMap<String, Object> loginCustomer(@RequestBody Customer customer){
        logger.info("Executing the request for logging in a client");
        return customerService.loginCustomer(customer);
    }

    @PostMapping("/admins/register")
    HashMap<String, Object> newAdmin(@RequestBody Admin admin){
        logger.info("Executing the request for registering admin");
        return adminService.saveAdmin(admin);
    }

    @PostMapping("/admins/login")
    HashMap<String, Object> loginAdmin(@RequestBody Admin admin){
        logger.info("Executing the request for logging in an admin");
        return adminService.loginAdmin(admin);

    }

}
