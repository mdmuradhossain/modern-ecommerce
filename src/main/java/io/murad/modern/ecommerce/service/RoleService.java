package io.murad.modern.ecommerce.service;

import io.murad.modern.ecommerce.database.model.Role;
import io.murad.modern.ecommerce.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;

    public void addRole() {
        Role role = new Role();
        role.setRoleName("SUPER_ADMIN");
        roleRepository.save(role);
    }

}
