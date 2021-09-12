package io.murad.modern.ecommerce.repository;

import io.murad.modern.ecommerce.database.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findByCountryName(String countryName);
}
