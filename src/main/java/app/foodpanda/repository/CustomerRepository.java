package app.foodpanda.repository;

import app.foodpanda.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends AbstractRepository<Customer> {

    Customer findByUsername(String username);
}
