package app.foodpanda.service;

import app.foodpanda.model.Customer;
import app.foodpanda.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import java.util.HashMap;


/**
 Service class containing the methods with the application logic that uses data from the customer table
 @author Daria Miu
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    Logger logger = LoggerFactory.getLogger(CustomerService.class);

    /**
     * Method to save an customer to the database
     * @param customer an object of type Customer to be saved in the database in the table customer
     * @return the response for the front in a hashmap structure with 2 entries withe the keys "message" that has as
     * value a string and "success" that has as value a bool depending on the success of the operation.
     */
    public HashMap<String,Object> saveCustomer(Customer customer){

        HashMap<String, Object> response = new HashMap<String, Object>();
        try {
            Customer validCustomer = customerRepository.findByUsername(customer.getUsername());
            if (validCustomer != null) {
                response.put("message", "The username "+ customer.getUsername() +" is taken ");
                response.put("success", false);
                return response;
            } else {
                String encodedPassword = BCrypt.hashpw(customer.getPassword(), BCrypt.gensalt());
                customerRepository.save(new Customer(customer.getUsername(),encodedPassword));
                response.put("message", "Successful save");
                response.put("success", true);
                logger.info("A new customer has been created with the username : {}",customer.getUsername());
                return response;
            }


        } catch (Exception e) {
            response.put("message", e.getMessage());
            response.put("success",false);
            logger.error(e.getMessage());
            return response;
        }

    }

    /**
     * Method to login customer
     * @param customer an object of type Customer
     * @return the response for the front in a hashmap structure with 2 entries withe the keys "message" that has as
     *value a string and "success" that has as value a bool depending on the success of the operation.
     */
    public HashMap<String, Object> loginCustomer(Customer customer){

        Customer customerFromDB = customerRepository.findByUsername(customer.getUsername());
        HashMap<String, Object> response = new HashMap<String, Object>();
        try {

            if (customerFromDB == null) {
                response.put("message", "No user with this username");
                response.put("success", false);
                return response;
            }
            else if (!BCrypt.checkpw(customer.getPassword(),customerFromDB.getPassword())) {
                response.put("message", "Wrong password");
                response.put("success", false);
                return response;
            } else {
                response.put("message", "Successful login");
                response.put("success", true);
                logger.info("Admin with username {} logged in", customer.getUsername());
                return response;
            }

        } catch (Exception e) {
            response.put("message", e.getMessage());
            response.put("success",false);
            logger.error(e.getMessage());
            return response;
        }

    }
}
