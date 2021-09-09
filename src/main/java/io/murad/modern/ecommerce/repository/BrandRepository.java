package io.murad.modern.ecommerce.repository;

import io.murad.modern.ecommerce.database.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Brand findByBrandName(String brandName);
}
