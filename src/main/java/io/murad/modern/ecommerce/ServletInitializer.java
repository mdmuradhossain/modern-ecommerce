package io.murad.modern.ecommerce;

import io.murad.modern.ecommerce.exception.ModernEcommerceException;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ModernEcommerceException.class);
    }
}
