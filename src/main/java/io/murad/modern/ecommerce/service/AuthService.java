package io.murad.modern.ecommerce.service;

import io.murad.modern.ecommerce.config.PasswordEncoderImpl;
import io.murad.modern.ecommerce.database.model.AccountVerificationToken;
import io.murad.modern.ecommerce.database.model.User;
import io.murad.modern.ecommerce.dto.RegisterRequest;
import io.murad.modern.ecommerce.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void register(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmailAddress(registerRequest.getEmail());
        user.setEnable(false);
        userRepository.save(user);

        String token = generateAccountVerificationToken(user);
    }

    public String generateAccountVerificationToken(User user){
        String token = UUID.randomUUID().toString();
        AccountVerificationToken verificationToken = new AccountVerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        return token;
    }

    public void verifyAccount(String token) {

    }
}
