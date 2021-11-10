package io.murad.modern.ecommerce.dto;

import io.murad.modern.ecommerce.database.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserDto {

    private String username;
    private String password;
    private String email;
    private String fullName;
    private Set<Role> roles;
}
