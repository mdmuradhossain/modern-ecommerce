package io.murad.modern.ecommerce.repository;

import io.murad.modern.ecommerce.database.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
