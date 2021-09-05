package io.murad.modern.ecommerce.repository;

import io.murad.modern.ecommerce.database.model.Cart;
import io.murad.modern.ecommerce.database.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Long> {
    List<Cart> findAllByUserOrderByCreatedDateDesc(User user);
}
