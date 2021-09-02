package io.murad.modern.ecommerce.service;

import io.murad.modern.ecommerce.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
}
