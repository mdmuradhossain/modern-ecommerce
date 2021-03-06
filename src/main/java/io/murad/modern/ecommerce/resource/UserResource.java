package io.murad.modern.ecommerce.resource;

import io.murad.modern.ecommerce.database.model.User;
import io.murad.modern.ecommerce.dto.AdminUserDto;
import io.murad.modern.ecommerce.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/users")
public class UserResource {

    private final UserService userService;

    @PostMapping(path = "/add",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> createUser(@RequestBody AdminUserDto adminUserDto) {
        userService.addUserOrAdmin(adminUserDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    @Cacheable(value = "User",key = "#id")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }


    @GetMapping()
    @Cacheable(value = "Users")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> editUser(@PathVariable("id") Long id, @RequestBody AdminUserDto adminUserDto) {
        userService.updateUserOrAdmin(id, adminUserDto);
        return new ResponseEntity<>("User Updated", HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> removeUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("User Removed.", HttpStatus.OK);
    }
}
