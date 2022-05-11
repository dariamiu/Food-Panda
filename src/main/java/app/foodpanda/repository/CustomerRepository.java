package app.foodpanda.repository;

import app.foodpanda.model.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends AbstractRepository<Customer> {

    Customer findByUsername(String username);
}
