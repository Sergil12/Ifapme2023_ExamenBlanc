package com.ApiExamen.ApiExamen.security.controller;

import com.ApiExamen.ApiExamen.Entity.Account;
import com.ApiExamen.ApiExamen.Entity.ApiResponse;
import com.ApiExamen.ApiExamen.Entity.Payload.AccountPayload.AccountCreatePayload;
import com.ApiExamen.ApiExamen.Entity.Payload.AccountPayload.AccountUpdatePayload;
import com.ApiExamen.ApiExamen.Entity.Payload.StudentPayload.StudentCreatePayload;
import com.ApiExamen.ApiExamen.Entity.Student;
import com.ApiExamen.ApiExamen.Service.AccountService;
import com.ApiExamen.ApiExamen.Service.StudentService;
import com.ApiExamen.ApiExamen.security.entity.Credential;
import com.ApiExamen.ApiExamen.security.entity.payload.RefreshTokenRequest;
import com.ApiExamen.ApiExamen.security.entity.payload.SigninRequest;
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
    @Autowired
    StudentService studentService;
    @Autowired
    AccountService accountService;

    @GetMapping("/me")
    public ApiResponse get(final Principal principal) {
        return new ApiResponse(true, credentialService.findCredentialByUsername(principal.getName()), null);
    }

    @PostMapping("/signin")
    public ApiResponse signin(@RequestBody SigninRequest request) {
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
                    Student student = studentService.create(new StudentCreatePayload(request.getFirstname(), request.getLastname()));
                    Account account = accountService.create(new AccountCreatePayload(request.getUsername(), student));
                    Credential credential = credentialService.saveCredential(new Credential.Builder()
                            .setUsername(request.getUsername())
                            .setPassword(encoder.encode(request.getPassword()))
                            .setActif(true)
                            .setAccount(account)
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
