package io.murad.modern.ecommerce.service;

import io.murad.modern.ecommerce.database.model.CustomUserDetails;
import io.murad.modern.ecommerce.database.model.User;
import io.murad.modern.ecommerce.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Supplier<UsernameNotFoundException> s =
//                () -> new UsernameNotFoundException(
//                        "Problem during authentication!");

        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username Not Found " + username));
        return new CustomUserDetails(user);
    }
}
