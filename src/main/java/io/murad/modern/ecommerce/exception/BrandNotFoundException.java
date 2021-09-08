package io.murad.modern.ecommerce.exception;

public class BrandNotFoundException extends RuntimeException {

    public BrandNotFoundException(String message) {
        super(message);
    }
}
