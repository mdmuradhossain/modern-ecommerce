package io.murad.modern.ecommerce.exception;

import javax.validation.constraints.NotEmpty;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(@NotEmpty String message) {
        super(message);
    }
}
