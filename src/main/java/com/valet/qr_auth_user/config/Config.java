package com.valet.qr_auth_user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableAsync
public class Config {

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder(12);
    }


    @Bean
    public SecurityWebFilterChain securityWebFilterChain(
            ServerHttpSecurity http) {
        return http.csrf().disable().httpBasic().and().authorizeExchange()
                .pathMatchers("/registration", "/do/**").permitAll()
                .anyExchange().authenticated()
                .and().build();
    }
}
