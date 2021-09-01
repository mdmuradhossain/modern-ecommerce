package io.murad.modern.ecommerce.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
//import io.murad.modern.ecommerce.database.model.CustomUserDetails;
import io.murad.modern.ecommerce.exception.ModernEcommerceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.time.Instant;
import java.util.Date;

import static io.jsonwebtoken.Jwts.parser;

@Service
@Slf4j
public class JwtAuthenticationProvider {

    private KeyStore keyStore;

    @Value("${jwt.expiration.time}")
    private Long jwtExpirationInMillis;

    @PostConstruct
    public void init() {
        try {
            keyStore = KeyStore.getInstance("JKS");
//            FileInputStream jksFile = new FileInputStream("src/main/resources/modern_ecommerce.jks");
            InputStream resourceStream = getClass().getResourceAsStream("/modern_ecommerce.jks");
            keyStore.load(resourceStream, "muradhossain".toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException | IOException | CertificateException e) {
            e.printStackTrace();
            throw new ModernEcommerceException("Exception occurred while loading keystore..." + e.getMessage());
        }
    }

    public String generateJwtToken(Authentication authentication) {
//        User principal = (User) authentication.getPrincipal();
//        io.murad.modern.ecommerce.database.model.User principal = (io.murad.modern.ecommerce.database.model.User) authentication.getPrincipal();
        org.springframework.security.core.userdetails.User principal = (User) authentication.getPrincipal();
//        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
//        UserDetails principal = (UserDetails) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .signWith(getPrivateKey())
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
                .compact();
    }

    private PrivateKey getPrivateKey() {
        try {
            return (PrivateKey) keyStore.getKey("mcommerce", "muradhossain".toCharArray());
        } catch (UnrecoverableKeyException | NoSuchAlgorithmException | KeyStoreException e) {
            throw new ModernEcommerceException("Exception occurred while signWith Private Key" + e.getMessage());
        }
    }

    private PublicKey getPublicKey() {
        try {
            return keyStore.getCertificate("mcommerce").getPublicKey();
        } catch (KeyStoreException e) {
            throw new ModernEcommerceException("Exception occurred while retrieving public key from keystore " + e.getMessage());
        }
    }

    public boolean validateJwtToken(String jwt) {
        try {
            Jwts.parserBuilder().setSigningKey(getPublicKey()).build().parseClaimsJws(jwt);
            return true;
        } catch (JwtException e) {
            log.warn(e.getMessage());
        }
        return false;
//        parser().setSigningKey(getPublicKey()).parseClaimsJws(jwt);
//        Jwts.parserBuilder()
//                .setSigningKey(getPublicKey())
//                .build()
//                .parseClaimsJws(jwt);
//        Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(jwt);
    }

    public String getUsernameFromJwt(String token) {
        Claims claims = parser()
                .setSigningKey(getPublicKey())
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();

    }

    public Long getJwtExpirationInMillis() {
        return jwtExpirationInMillis;
    }
}
