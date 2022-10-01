package com.valet.qrmainserver.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class UserDTO {
    private String name;
    private String phone;
    private String jobTitle;
    private String organizationId;
    private String email;
}
