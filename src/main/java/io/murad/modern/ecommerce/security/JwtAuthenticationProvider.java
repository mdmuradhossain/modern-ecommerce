package io.murad.modern.ecommerce.security;

import io.murad.modern.ecommerce.exception.ModernEcommerceException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

@Service
public class JwtAuthenticationProvider {

    private KeyStore keyStore;

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
}
