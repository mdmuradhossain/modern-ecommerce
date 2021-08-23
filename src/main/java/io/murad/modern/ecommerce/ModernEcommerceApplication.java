package io.murad.modern.ecommerce;

import io.murad.modern.ecommerce.database.model.User;
import io.murad.modern.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ModernEcommerceApplication implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(ModernEcommerceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        User.builder()
//                .username("murad")
//                .password("admin")
//                .authority("read")
//                .build();
        User u = new User();
        u.setUsername("murad");
        u.setPassword("admin");
        u.setAuthority("read");
        userRepository.save(u);
    }

}
