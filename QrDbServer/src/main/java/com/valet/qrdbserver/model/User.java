package com.valet.qrdbserver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class User{
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
}
