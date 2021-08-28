package io.murad.modern.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"io.murad.modern.ecommerce.repository"})
public class ModernEcommerceApplication {


    public static void main(String[] args) {
        SpringApplication.run(ModernEcommerceApplication.class, args);
    }


}
