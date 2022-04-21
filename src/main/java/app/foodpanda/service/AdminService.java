package app.foodpanda.service;

import app.foodpanda.model.Admin;
import app.foodpanda.model.Customer;
import app.foodpanda.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    public HashMap<String, Object> saveAdmin(Admin admin){

        HashMap<String, Object> response = new HashMap<String, Object>();
        try {

            Admin validAdmin = adminRepository.findByUsername(admin.getUsername());

            if(validAdmin != null) {
                response.put("message", "The username "+ admin.getUsername() +" is taken ");
                response.put("success", false);
                return response;
            }
            else {
                String encodedPassword = BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt());
                adminRepository.save(new Admin(admin.getUsername(),encodedPassword));
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

    }

    public HashMap<String, Object> loginAdmin(Admin admin){

        Admin adminFromDB = adminRepository.findByUsername(admin.getUsername());
        HashMap<String, Object> response = new HashMap<String, Object>();
        try {
            if(adminFromDB == null) {
                response.put("message", "No user with this username");
                response.put("success", false);
                return response;
            }
            else if (!BCrypt.checkpw(admin.getPassword(),adminFromDB.getPassword())) {
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

    }
}
