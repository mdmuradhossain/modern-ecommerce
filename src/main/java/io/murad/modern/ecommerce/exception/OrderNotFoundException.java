package io.murad.modern.ecommerce.exception;

public class OrderNotFoundException extends IllegalArgumentException {

    public OrderNotFoundException(String message){
        super(message);
    }
}
