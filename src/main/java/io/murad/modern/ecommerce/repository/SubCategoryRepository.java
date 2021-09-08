package io.murad.modern.ecommerce.repository;

import io.murad.modern.ecommerce.database.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoryRepository extends JpaRepository<SubCategory,Long> {
}
