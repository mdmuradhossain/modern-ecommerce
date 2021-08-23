package io.murad.modern.ecommerce.repository;

import io.murad.modern.ecommerce.database.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
