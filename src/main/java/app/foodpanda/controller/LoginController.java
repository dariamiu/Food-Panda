package app.foodpanda.controller;

import app.foodpanda.model.Admin;
import app.foodpanda.model.Customer;
import app.foodpanda.service.AdminService;
import app.foodpanda.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    @Autowired
    CustomerService customerService;

    @Autowired
    AdminService adminService;

    @RequestMapping(value = "/customers/register", method = RequestMethod.POST)
    HashMap<String,Object> newCustomer(@RequestBody Customer customer){
        System.out.println("register ok");
        return customerService.saveCustomer(customer);
    }


    @RequestMapping(value = "/customers/login", method = RequestMethod.POST)
    HashMap<String, Object> loginCustomer(@RequestBody Customer customer){
        System.out.println("login");
        return customerService.loginCustomer(customer);
    }

    @PostMapping("/admins/register")
    HashMap<String, Object> newAdmin(@RequestBody Admin admin){
        System.out.println("register");
        return adminService.saveAdmin(admin);
    }

    @PostMapping("/admins/login")
    HashMap<String, Object> loginAdmin(@RequestBody Admin admin){
        System.out.println("login");
        return adminService.loginAdmin(admin);

    }

}
