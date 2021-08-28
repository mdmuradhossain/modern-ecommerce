package io.murad.modern.ecommerce.service;

import io.murad.modern.ecommerce.config.PasswordEncoderImpl;
import io.murad.modern.ecommerce.database.model.AccountVerificationToken;
import io.murad.modern.ecommerce.database.model.NotificationEmail;
import io.murad.modern.ecommerce.database.model.User;
import io.murad.modern.ecommerce.dto.RegisterRequest;
import io.murad.modern.ecommerce.exception.ModernEcommerceException;
import io.murad.modern.ecommerce.repository.UserRepository;
import io.murad.modern.ecommerce.repository.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class AuthService {

    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;
    private final Environment env;
    @Value("${spring.application.name}")
    private final String appName;

    @Transactional
    public void register(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmailAddress(registerRequest.getEmail());
        user.setEnable(false);
        userRepository.save(user);

        String token = generateAccountVerificationToken(user);
        String emailBody = "Thank you for signing up to " + appName + ". Please click on the below url to activate your account: " + "http://localhost:8080/auth/accountVerification/" + token;
        mailService.sendMail(new NotificationEmail("Please activate your Account",
                user.getEmailAddress(), emailBody));

    }

    public String generateAccountVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        AccountVerificationToken verificationToken = new AccountVerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);
        return token;
    }

    public void verifyAccount(String token) {
        Optional<AccountVerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        if (token.equals(verificationToken.get())) {
            getUserAndEnableAccount(verificationToken.get());
        }else {
            throw new ModernEcommerceException("Verification failed");
        }
    }

    private void getUserAndEnableAccount(AccountVerificationToken verificationToken) {
        String username = verificationToken.getUser().getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ModernEcommerceException("User not Found with " + username));
        user.setEnable(true);
        userRepository.save(user);
    }

}
