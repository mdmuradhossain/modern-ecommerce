package io.murad.modern.ecommerce.repository;

import io.murad.modern.ecommerce.database.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
