package com.valet.qr_auth_admin.model.DTO;

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
public class UserDTO {
    @Id
    private Long id;
    private String name;
    @Column("job_title")
    private String jobTitle;
    private String phone;
}
