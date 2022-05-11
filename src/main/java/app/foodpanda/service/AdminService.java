package app.foodpanda.service;

import app.foodpanda.model.Admin;
import app.foodpanda.repository.AdminRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 Service class containing the methods with the application logic that uses data from the admin table
 @author Daria Miu
 */
@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    Logger logger = LoggerFactory.getLogger(AdminService.class);

    /**
     * Method to save an admin to the database
     * @param admin an object of type Admin to be saved in the database in the table admin
     * @return the response for the front in a hashmap structure with 2 entries withe the keys "message" that has as
     * value a string and "success" that has as value a bool depending on the success of the operation.
     */
    public HashMap<String, Object> saveAdmin(Admin admin){

        HashMap<String, Object> response = new HashMap<String, Object>();
        try {
            Admin validAdmin = adminRepository.findByUsername(admin.getUsername());
            if (validAdmin != null) {
                response.put("message", "The username "+ admin.getUsername() +" is taken ");
                response.put("success", false);
                return response;
            } else {
                String encodedPassword = BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt());
                adminRepository.save(new Admin(admin.getUsername(),encodedPassword));
                response.put("message", "Successful save");
                response.put("success", true);
                logger.info("A new admin has been created with the username : {}",admin.getUsername());
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
     * Method to login an admin
     * @param admin an object of type Admin
     * @return the response for the front in a hashmap structure with 2 entries withe the keys "message" that has as
     *value a string and "success" that has as value a bool depending on the success of the operation.
     */
    public HashMap<String, Object> loginAdmin(Admin admin){

        Admin adminFromDB = adminRepository.findByUsername(admin.getUsername());
        HashMap<String, Object> response = new HashMap<String, Object>();
        try {
            if (adminFromDB == null) {
                response.put("message", "No user with this username");
                response.put("success", false);
                return response;
            }
            else if (!BCrypt.checkpw(admin.getPassword(),adminFromDB.getPassword())) {
                response.put("message", "Wrong password");
                response.put("success", false);
                return response;
            } else {
                response.put("message", "Successful login");
                response.put("success", true);
                logger.info("Admin with username {} logged in", admin.getUsername());
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
