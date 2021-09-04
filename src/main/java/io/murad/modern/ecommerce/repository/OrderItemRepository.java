package io.murad.modern.ecommerce.repository;

import io.murad.modern.ecommerce.database.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
