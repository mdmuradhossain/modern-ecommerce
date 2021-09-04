package io.murad.modern.ecommerce.repository;

import io.murad.modern.ecommerce.database.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
