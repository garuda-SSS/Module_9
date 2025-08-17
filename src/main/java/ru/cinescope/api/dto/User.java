package ru.cinescope.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private String email;
    private String fullName;
    private Instant createdAt;
    private String[] roles;
    private boolean verified;
    private boolean banned;
}
