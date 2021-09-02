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

    public void addRole(Role role) {
        roleRepository.save(role);
    }

    public Role getRole(Long id){
        return roleRepository.findById(id).orElseThrow(()->new ModernEcommerceException("Role not found"));
    }
}
