package io.murad.modern.ecommerce.repository;


import io.murad.modern.ecommerce.database.model.AccountVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<AccountVerificationToken,Long> {
}
