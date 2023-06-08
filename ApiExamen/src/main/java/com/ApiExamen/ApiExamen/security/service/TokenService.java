package com.ApiExamen.ApiExamen.security.service;

import com.ApiExamen.ApiExamen.security.entity.response.TokenResponse;

public interface TokenService {
    TokenResponse getRefreshToken(String refresh_token);
    TokenResponse getToken(String username, String password);
}
