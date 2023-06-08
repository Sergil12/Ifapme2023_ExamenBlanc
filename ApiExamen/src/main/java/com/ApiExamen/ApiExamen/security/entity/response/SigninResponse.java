package com.ApiExamen.ApiExamen.security.entity.response;

import com.ApiExamen.ApiExamen.security.entity.Credential;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SigninResponse {
    Credential user;
    TokenResponse token;
}
