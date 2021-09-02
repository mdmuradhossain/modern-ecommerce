package io.murad.modern.ecommerce.resource;

import io.murad.modern.ecommerce.database.model.Role;
import io.murad.modern.ecommerce.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/roles")
@AllArgsConstructor
public class RoleResource {

    private final RoleService roleService;

    @PostMapping()
    public ResponseEntity<?> createRole(@RequestBody Role role){
        roleService.addRole(role);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
