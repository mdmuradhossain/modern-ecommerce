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
import java.security.*;
import java.security.cert.CertificateException;
import java.time.Instant;
import java.util.Date;

import static io.jsonwebtoken.Jwts.parser;

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

    private PrivateKey getPrivateKey() {
        try {
            return (PrivateKey) keyStore.getKey("mecommerce", "muradhossain".toCharArray());
        } catch (UnrecoverableKeyException | NoSuchAlgorithmException | KeyStoreException e) {
            throw new ModernEcommerceException("Exception occurred while signWIth Private Key" + e.getMessage());
        }
    }

    private PublicKey getPublicKey() {
        try {
            return keyStore.getCertificate("mcommerce").getPublicKey();
        } catch (KeyStoreException e) {
            throw new ModernEcommerceException("Exception occurred while " + "retrieving public key from keystore " + e.getMessage());
        }
    }

    public boolean validateJwtToken(String jwt) {
        var parser = Jwts.parserBuilder()
                .build();
        parser.setSigningKey(getPrivateKey()).parseClaimsJws(jwt);
//        parser().setSigningKey(getPrivateKey()).parseClaimsJws(jwt);
        return true;
    }
}
