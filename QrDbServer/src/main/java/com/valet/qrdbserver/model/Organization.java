package com.valet.qrdbserver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "organization")
public class Organization {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private String creatorId;
    private String payToken;
    private LocalDateTime lastCheckToken;
}
