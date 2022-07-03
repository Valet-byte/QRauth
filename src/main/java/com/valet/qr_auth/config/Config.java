package com.valet.qr_auth.config;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class Config{
    @Bean
    public CacheLoader<Object, Object> cacheLoader() {
        return new CacheLoader<>() {
            @Override
            public Object load(Object o){
                System.out.println("data -> " + o);
                return o;
            }
        };
    }
    @Bean("MyCacheManager")
    public CacheManager cacheManager() {

        return new ConcurrentMapCacheManager() {
            @Override
            protected Cache createConcurrentMapCache(String name) {
                return new ConcurrentMapCache(name, CacheBuilder.newBuilder()
                                .expireAfterWrite(2, TimeUnit.MINUTES)
                                .refreshAfterWrite(2, TimeUnit.MINUTES)
                                .build(cacheLoader()).asMap(),
                        false);
            }
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().httpBasic().and().authorizeRequests()
                .antMatchers("/generatedPoint", "/generateToken", "/getAllRecord", "/changeOrganization"
                , "/changePhone", "/changeName", "/deleteAccount", "/changePassword").hasAnyAuthority("ADMIN", "PROGRAMMER")
                .antMatchers("/login", "/setRecord").authenticated()
                .anyRequest().permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(13);
    }


}
