package com.ApiExamen.ApiExamen.security.service;

import com.ApiExamen.ApiExamen.security.repository.CredentialRepository;
import com.ApiExamen.ApiExamen.security.entity.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class CredentialServiceImpl implements CredentialService {

    @Autowired
    CredentialRepository credentialRepository;

    @Override
    public Credential saveCredential(Credential credential) {
        return credentialRepository.save(credential);
    }

    @Override
    public Credential findCredentialByUsername(String username) {
        return credentialRepository.findByUsername(username);
    }
}
