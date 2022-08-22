package com.valet.qr_auth_main_server.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Config {

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder(12);
    }


    @Bean
    public SecurityWebFilterChain securityWebFilterChain(
            ServerHttpSecurity http) {
        return http.csrf().disable().httpBasic().and().authorizeExchange()
                .pathMatchers("/registration", "/do/**", "/getAllOrganization", "/test").permitAll()
                .pathMatchers("/getToken").hasAnyAuthority("ADMIN", "ORGANIZATION_CREATOR")
                .pathMatchers("/setRecord").hasAuthority("USER")
                .pathMatchers("/addAdmin", "/addToMyCommand").hasAnyAuthority("ORGANIZATION_CREATOR", "ADMIN")
                .anyExchange().authenticated()
                .and().build();
    }

    @Bean
    @LoadBalanced
    public WebClient.Builder builder(){
        return WebClient.builder();
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
