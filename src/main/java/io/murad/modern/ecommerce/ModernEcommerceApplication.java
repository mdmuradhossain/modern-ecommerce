package io.murad.modern.ecommerce;

import io.murad.modern.ecommerce.database.model.User;
import io.murad.modern.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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
