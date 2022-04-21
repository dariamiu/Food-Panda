package app.foodpanda.service;

import app.foodpanda.model.Customer;
import app.foodpanda.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public HashMap<String,Object> saveCustomer(Customer customer){
        HashMap<String, Object> response = new HashMap<String, Object>();
        try {

            Customer validCustomer = customerRepository.findByUsername(customer.getUsername());

            if(validCustomer != null) {
                response.put("message", "The username "+ customer.getUsername() +" is taken ");
                response.put("success", false);
                return response;
            }
            else {
                String encodedPassword = BCrypt.hashpw(customer.getPassword(), BCrypt.gensalt());
                customerRepository.save(new Customer(customer.getUsername(),encodedPassword));
                response.put("message", "Successful save");
                response.put("success", true);
                return response;
            }


        } catch (Exception e) {
            // TODO: handle exception
            response.put("message", e.getMessage());
            response.put("success",false);
            return response;
        }

        //////////////////////////////////////////////////
      /* Customer customerFromDB = customerRepository.findByUsername(customer.getUsername());

        if(customerFromDB != null){
            System.out.println("Username taken");
            return null;
        }

        String encodedPassword = BCrypt.hashpw(customer.getPassword(), BCrypt.gensalt());

        return customerRepository.save(new Customer(customer.getUsername(),encodedPassword));*/

    }

    public HashMap<String, Object> loginCustomer(Customer customer){

        Customer customerFromDB = customerRepository.findByUsername(customer.getUsername());
        HashMap<String, Object> response = new HashMap<String, Object>();
        try {

            if(customerFromDB == null) {
                response.put("message", "No user with this username");
                response.put("success", false);
                return response;
            }
            else if (!BCrypt.checkpw(customer.getPassword(),customerFromDB.getPassword())) {
                response.put("message", "Wrong password");
                response.put("success", false);
                return response;
            }else{
                response.put("message", "Successful login");
                response.put("success", true);
                return response;
            }

        } catch (Exception e) {
            // TODO: handle exception
            response.put("message", e.getMessage());
            response.put("success",false);
            return response;
        }

        ///////////////
        /*if(customerFromDB == null){
            System.out.println("wrong username");
           return null;
        }else if (!BCrypt.checkpw(customer.getPassword(),customerFromDB.getPassword())){
            System.out.println("wrong password");
            return null;
        }
        return customer;*/
    }
}
