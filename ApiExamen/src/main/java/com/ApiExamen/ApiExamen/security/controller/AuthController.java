package com.ApiExamen.ApiExamen.security.controller;

import com.ApiExamen.ApiExamen.Entity.ApiResponse;
import com.ApiExamen.ApiExamen.security.entity.Credential;
import com.ApiExamen.ApiExamen.security.entity.payload.RefreshTokenRequest;
import com.ApiExamen.ApiExamen.security.entity.payload.SignupRequest;
import com.ApiExamen.ApiExamen.security.repository.CredentialRepository;
import com.ApiExamen.ApiExamen.security.service.CredentialService;
import com.ApiExamen.ApiExamen.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;

@CrossOrigin(origins = "*", maxAge = 3600)
@EnableResourceServer
@RestController
@RequestMapping("/account")
public class AuthController {
    @Autowired
    CredentialService credentialService;
    @Autowired
    TokenService tokenService;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    CredentialRepository credentialRepository;

    @GetMapping("/me")
    public ApiResponse get(final Principal principal) {
        return new ApiResponse(true, credentialService.findCredentialByUsername(principal.getName()), null);
    }

    @PostMapping("/signin")
    public ApiResponse signin(@RequestBody SignupRequest request) {
        ApiResponse result = request.isValid();
        if (result.isSuccess()) {
            Credential credential = credentialService.findCredentialByUsername(request.getUsername());
            if (credential != null && encoder.matches(request.getPassword(), credential.getPassword())) {
                HashMap<String, Object> hmap = new HashMap<String, Object>();
                hmap.put("user", credential);
                hmap.put("token", this.tokenService.getToken(request.getUsername(), request.getPassword()));
                return new ApiResponse(true, hmap, null);
            } else {
                return new ApiResponse(false, null, "api.signin.bad-credentials");
            }
        } else {
            return result;
        }
    }

    @PostMapping("/refresh")
    public ApiResponse refresh(@RequestBody RefreshTokenRequest refresh) {
        try {
            return new ApiResponse(true, tokenService.getRefreshToken(refresh.getRefresh()), null);
        } catch (Exception e) {
            return new ApiResponse(false, null, e.getMessage());
        }
    }

    @PostMapping("/signup")
    public ApiResponse signup(@RequestBody SignupRequest request) {
        ApiResponse result = request.isValid();
        if (result.isSuccess()) {
            if (credentialRepository.existsByUsername(request.getUsername())) {
                return new ApiResponse(false, null, "api.signup.email-exist");
            } else {
                try {
                    Credential credential = credentialService.saveCredential(new Credential.Builder()
                            .setUsername(request.getUsername())
                            .setPassword(encoder.encode(request.getPassword()))
                            .setActif(true)
                            .build());
                    return new ApiResponse(true, credential, null);
                } catch (Exception e) {
                    e.printStackTrace();
                    return new ApiResponse(false, null, "api.signup.database-insert-error");
                }
            }
        } else {
            return result;
        }
    }

}
