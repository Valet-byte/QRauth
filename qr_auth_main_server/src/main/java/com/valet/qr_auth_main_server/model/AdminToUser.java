package com.valet.qr_auth_main_server.model;

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
@Table("admin_user")
public class AdminToUser {
    @Id private Long id;
    @Column("admin_id")
    private Long admin_id;
    @Column("user_id")
    private Long user_id;
}
