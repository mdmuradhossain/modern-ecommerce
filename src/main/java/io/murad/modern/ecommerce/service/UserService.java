package io.murad.modern.ecommerce.service;

import io.murad.modern.ecommerce.database.model.CustomUserDetails;
import io.murad.modern.ecommerce.database.model.User;
import io.murad.modern.ecommerce.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Supplier<UsernameNotFoundException> s =
//                () -> new UsernameNotFoundException(
//                        "Problem during authentication!");

        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username Not Found " + username));
        return new CustomUserDetails(user);
    }
}
