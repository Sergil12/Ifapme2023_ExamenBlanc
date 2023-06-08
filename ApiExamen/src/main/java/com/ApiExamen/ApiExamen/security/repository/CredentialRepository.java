package com.ApiExamen.ApiExamen.security.repository;

import com.ApiExamen.ApiExamen.security.entity.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, Long> {
    Credential findByUsername(String username);

    boolean existsByUsername(String username);
}
