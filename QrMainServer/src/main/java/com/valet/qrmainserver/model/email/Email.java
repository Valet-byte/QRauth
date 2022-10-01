package com.valet.qrmainserver.model.email;

import com.valet.qrmainserver.model.action.ActionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Email {
    private String email;
    private String subject;
    private List<String> usernames;
    private String link;
    private ActionType type;

}
