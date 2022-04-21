package app.foodpanda.repository;

import app.foodpanda.model.Admin;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends AbstractRepository<Admin>{

    @Query("SELECT a FROM Admin a WHERE a.username = ?1 ")
    Admin findByUsername(String name);

}
