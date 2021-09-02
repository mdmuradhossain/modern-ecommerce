package io.murad.modern.ecommerce.resource;

import io.murad.modern.ecommerce.database.model.Role;
import io.murad.modern.ecommerce.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/roles")
@AllArgsConstructor
public class RoleResource {

    private final RoleService roleService;

    @PostMapping()
    public ResponseEntity<?> createRole(@RequestBody Role role) {
        roleService.addRole(role);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Role> getRole(@PathVariable Long id) {
        Role role = roleService.getRole(id);
        return new ResponseEntity<Role>(role, HttpStatus.OK);
    }
}
