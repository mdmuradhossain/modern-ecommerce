package io.murad.modern.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableJpaRepositories(basePackages = {"io.murad.modern.ecommerce.repository"})
@EnableConfigurationProperties
public class ModernEcommerceApplication {


    public static void main(String[] args) {
        SpringApplication.run(ModernEcommerceApplication.class, args);
    }


}
