package io.murad.modern.ecommerce.repository;

import io.murad.modern.ecommerce.database.model.Order;
import io.murad.modern.ecommerce.database.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByUserOrderByCreatedDateDesc(User user);
}
