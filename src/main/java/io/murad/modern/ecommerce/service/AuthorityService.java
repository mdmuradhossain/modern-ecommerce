package io.murad.modern.ecommerce.service;

import io.murad.modern.ecommerce.database.model.Authority;
import io.murad.modern.ecommerce.repository.AuthorityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service
@Transactional
@AllArgsConstructor
public class AuthorityService {
    private final AuthorityRepository authorityRepository;

    public void saveAuthority() {
        Authority authorityWrite = new Authority();
        authorityWrite.setName("WRITE_AUTHORITY");
        Authority authorityRead = new Authority();
        authorityRead.setName("READ_AUTHORITY");
        Authority authorityUpdate = new Authority();
        authorityUpdate.setName("UPDATE_AUTHORITY");
        Authority authorityDelete = new Authority();
        authorityDelete.setName("DELETE_AUTHORITY");
        authorityRepository.save(authorityWrite);
        authorityRepository.save(authorityRead);
        authorityRepository.save(authorityUpdate);
        authorityRepository.save(authorityDelete);
    }
}
