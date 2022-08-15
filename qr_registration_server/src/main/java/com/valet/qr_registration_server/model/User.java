package com.valet.qr_registration_server.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("users")
public class User  {
    @Id
    private Long id;
    private String name;
    private String password;
    @Column("job_title")
    private String jobTitle;
    private String phone;
    @Column("organization_id")
    private Organization organization;
    private String email;
    @Column("role_id")
    private Role role;
}