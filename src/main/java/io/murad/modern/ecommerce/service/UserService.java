package io.murad.modern.ecommerce.service;

import io.murad.modern.ecommerce.database.model.CustomUserDetails;
import io.murad.modern.ecommerce.database.model.User;
import io.murad.modern.ecommerce.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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

//        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),new ArrayList<>());
    }
}
