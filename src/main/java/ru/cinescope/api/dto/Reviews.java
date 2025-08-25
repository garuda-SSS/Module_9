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
public class Reviews {
    private String userId;
    private String text;
    private Integer rating;
    private Instant createdAt; // Используем Instant для ISO-формата
    private Boolean hidden;
    private ReviewUser user; // Вложенный DTO для пользователя
}
