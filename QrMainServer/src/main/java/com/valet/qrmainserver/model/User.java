package com.valet.qrmainserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.valet.qrmainserver.model.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class User implements UserDetails {
    @Id
    private String id;
    private String name;
    private String password;
    private String jobTitle;
    private String organizationId;
    private String phone;
    @Indexed(unique = true)
    private String email;
    private String role;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(Objects.requireNonNullElse(role, "USER")));
    }
    @Override
    @JsonIgnore
    public String getUsername() {
        return email;
    }
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
    @JsonIgnore
    public UserDTO getDTO(){
        return UserDTO.builder()
                .email(this.email)
                .jobTitle(this.jobTitle)
                .name(this.name)
                .phone(this.phone)
                .build();
    }
}
