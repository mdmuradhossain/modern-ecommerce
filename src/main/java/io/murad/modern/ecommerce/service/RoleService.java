package io.murad.modern.ecommerce.service;

import io.murad.modern.ecommerce.database.model.Role;
import io.murad.modern.ecommerce.exception.ModernEcommerceException;
import io.murad.modern.ecommerce.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;

    @Transactional
    public void addRole(Role role) {
        roleRepository.save(role);
    }

    public Role getRole(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new ModernEcommerceException("Role not found"));
    }

    public void updateRole(Long id, Role role) {
        Role exitingRole = roleRepository.findById(id).orElseThrow(() -> new ModernEcommerceException("Role Not found"));
        exitingRole.setRoleName(role.getRoleName());
        exitingRole.setDescription(role.getDescription());
        roleRepository.save(exitingRole);
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
