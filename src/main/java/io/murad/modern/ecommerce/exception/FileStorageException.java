package io.murad.modern.ecommerce.exception;

import java.io.IOException;

public class FileStorageException extends Throwable {

    public FileStorageException(String message){
        super(message);
    }
    public FileStorageException(String message, Throwable cause) {
        super(message,cause);
    }
}
