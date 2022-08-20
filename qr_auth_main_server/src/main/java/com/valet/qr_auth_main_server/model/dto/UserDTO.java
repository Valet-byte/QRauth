package com.valet.qr_auth_main_server.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class UserDTO {
    private String name;
    private String phone;
    @Column("job_title")
    private String jobTitle;
    private String email;
}
