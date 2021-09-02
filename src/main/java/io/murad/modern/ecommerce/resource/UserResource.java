package io.murad.modern.ecommerce.resource;

import io.murad.modern.ecommerce.database.model.User;
import io.murad.modern.ecommerce.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/users")
public class UserResource {

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody User user) {
        userService.addUserOrAdmin(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }
}
