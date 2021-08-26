package io.murad.modern.ecommerce.database.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

//    @Enumerated(EnumType.STRING)
//    private EncryptionAlgorithm algorithm;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Authority> authorities;
}
