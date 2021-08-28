package io.murad.modern.ecommerce.repository;


import io.murad.modern.ecommerce.database.model.AccountVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<AccountVerificationToken,Long> {
    Optional<AccountVerificationToken> findByToken(String token);
}
