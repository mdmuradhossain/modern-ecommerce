package io.murad.modern.ecommerce.resource;

import io.murad.modern.ecommerce.database.model.Role;
import io.murad.modern.ecommerce.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @Cacheable(value = "Role",key = "#id")
    public ResponseEntity<Role> getRole(@PathVariable Long id) {
        Role role = roleService.getRole(id);
        return new ResponseEntity<Role>(role, HttpStatus.OK);
    }

    @GetMapping()
    @Cacheable(value = "Roles")
    public ResponseEntity<List<Role>> getRoles() {
        return new ResponseEntity<>(roleService.getRoles(), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> editRole(@PathVariable("id") Long id, @RequestBody Role role) {
        roleService.updateRole(id, role);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> removeRole(@PathVariable("id") Long id) {
        roleService.deleteRole(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
