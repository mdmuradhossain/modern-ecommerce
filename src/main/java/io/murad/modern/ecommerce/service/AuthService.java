package io.murad.modern.ecommerce.service;

import io.murad.modern.ecommerce.config.PasswordEncoderImpl;
import io.murad.modern.ecommerce.database.model.AccountVerificationToken;
import io.murad.modern.ecommerce.database.model.NotificationEmail;
import io.murad.modern.ecommerce.database.model.Role;
import io.murad.modern.ecommerce.database.model.User;
import io.murad.modern.ecommerce.dto.AuthenticationRequest;
import io.murad.modern.ecommerce.dto.AuthenticationResponse;
import io.murad.modern.ecommerce.dto.RefreshTokenRequest;
import io.murad.modern.ecommerce.dto.RegisterRequest;
import io.murad.modern.ecommerce.exception.ModernEcommerceException;
import io.murad.modern.ecommerce.repository.RoleRepository;
import io.murad.modern.ecommerce.repository.UserRepository;
import io.murad.modern.ecommerce.repository.VerificationTokenRepository;
import io.murad.modern.ecommerce.security.JwtAuthenticationProvider;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
@Transactional
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MailService mailService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Value("${spring.application.name}")
    private String appName;

    @Transactional
    public void register(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmailAddress(registerRequest.getEmail());
        user.setEnable(false);
        Set<Role> roles = new HashSet<>();
        roleRepository.findByRoleName("ROLE_USER").ifPresent(roles::add);
        user.setRoles(roles);
        userRepository.save(user);
        log.info("User Saved..."+user.getUsername());
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
        verificationToken.orElseThrow(()-> new ModernEcommerceException("Verification Failed"));
        getUserAndEnableAccount(verificationToken.get());
    }

    @Transactional
    public void getUserAndEnableAccount(AccountVerificationToken verificationToken) {
        String username = verificationToken.getUser().getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ModernEcommerceException("User not Found with " + username));
        user.setEnable(true);
        userRepository.save(user);
    }

    public AuthenticationResponse signIn(AuthenticationRequest authenticationRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtAuthenticationProvider.generateJwtToken(authenticate);
        log.debug(token);
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwtAuthenticationProvider.getJwtExpirationInMillis()))
                .username(authenticationRequest.getUsername())
                .build();
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtAuthenticationProvider.generateTokenWithUserName(refreshTokenRequest.getUsername());
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtAuthenticationProvider.getJwtExpirationInMillis()))
                .username(refreshTokenRequest.getUsername())
                .build();
    }
}
