package com.ApiExamen.ApiExamen.security.service;

import com.ApiExamen.ApiExamen.security.entity.Credential;

public interface CredentialService {

    Credential saveCredential(Credential credential);

    Credential findCredentialByUsername(String username);

}