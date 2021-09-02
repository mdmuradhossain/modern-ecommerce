package io.murad.modern.ecommerce.resource;

import io.murad.modern.ecommerce.database.model.User;
import io.murad.modern.ecommerce.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/users")
public class UserResource {

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<?> createUser(User user) {
        userService.addUserOrAdmin(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
