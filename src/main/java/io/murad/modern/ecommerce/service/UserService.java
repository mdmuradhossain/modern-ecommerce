package io.murad.modern.ecommerce.service;

import io.murad.modern.ecommerce.database.model.NotificationEmail;
import io.murad.modern.ecommerce.database.model.Role;
import io.murad.modern.ecommerce.database.model.User;
import io.murad.modern.ecommerce.dto.AdminUserDto;
import io.murad.modern.ecommerce.repository.RoleRepository;
import io.murad.modern.ecommerce.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;
    private final MailService mailService;

    @Transactional
    public void addUserOrAdmin(AdminUserDto adminUserDto) {

        User user = new User();
        if (userRepository.findByUsername(adminUserDto.getUsername()).isPresent()) {
            log.info("Username already exists");
        } else {
            user.setUsername(adminUserDto.getUsername());
        }
        user.setPassword(passwordEncoder.encode(adminUserDto.getPassword()));
        user.setEmailAddress(adminUserDto.getEmail());
        if (adminUserDto.getRoles() != null) {
            Set<Role> roles = adminUserDto
                    .getRoles()
                    .stream()
                    .map((role -> roleRepository.findByRoleName(role.getRoleName())))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());
            user.setRoles(roles);
        }
        user.setEnable(true);
        userRepository.save(user);
//        String token = authService.generateAccountVerificationToken(user);
//        String emailBody = "Thank you for signing up Please click on the below url to activate your account: " + "http://localhost:8080/auth/accountVerification/" + token;
//        mailService.sendMail(new NotificationEmail("Please activate your Account",
//                user.getEmailAddress(), emailBody));

    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
        log.info("User deleted...");
    }
}
