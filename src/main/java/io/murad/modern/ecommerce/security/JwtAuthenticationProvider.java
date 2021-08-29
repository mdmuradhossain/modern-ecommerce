package io.murad.modern.ecommerce.security;

import io.jsonwebtoken.Jwts;
import io.murad.modern.ecommerce.exception.ModernEcommerceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtAuthenticationProvider {

    private KeyStore keyStore;

    @Value("900000")
    private Long jwtExpirationInMillis;

    @PostConstruct
    public void init() {
        try {
            keyStore = KeyStore.getInstance("JKS");
            FileInputStream jksFile = new FileInputStream("src/main/resources/modern_ecommerce");
            keyStore.load(jksFile, "muradhossain".toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException | IOException | CertificateException e) {
            e.printStackTrace();
            throw new ModernEcommerceException("Exception occurred while loading keystore..." + e.getMessage());
        }
    }

    public String generateJwtToken(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(principal.getUsername())
                .signWith(getPrivateKey())
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
                .compact();
    }
}
