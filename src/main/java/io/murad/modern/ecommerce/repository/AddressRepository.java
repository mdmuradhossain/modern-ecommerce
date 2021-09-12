package io.murad.modern.ecommerce.repository;

import io.murad.modern.ecommerce.database.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository <Address,Long> {
}
