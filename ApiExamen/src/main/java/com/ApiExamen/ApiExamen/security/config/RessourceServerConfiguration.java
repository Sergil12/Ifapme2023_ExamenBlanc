package com.ApiExamen.ApiExamen.security.config;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.bind.annotation.RestController;

@EnableResourceServer
@RestController
public class RessourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.authorizeRequests().antMatchers("/login", "/account/signup", "/account/signin","/account/refresh","/oauth/token","/v2/api-docs","/swagger-ui.html").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
    }

}
