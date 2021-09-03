package io.murad.modern.ecommerce.repository;

import io.murad.modern.ecommerce.database.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority,Long> {
}
