package app.foodpanda.repository;

import app.foodpanda.model.Customer;
import app.foodpanda.model.Order;
import app.foodpanda.model.Restaurant;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends AbstractRepository<Order> {
    List<Order> findAllByRestaurant(Restaurant restaurant);
    List<Order> findAllByCustomer(Customer customer);


    @Query("SELECT o FROM Order o WHERE o.id_order_table = ?1")
    Order findOrderByMyId(Integer myId);
}
