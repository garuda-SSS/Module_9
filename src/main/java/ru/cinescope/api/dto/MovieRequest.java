package ru.cinescope.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieRequest {
    private String name;
    private String imageUrl;
    private Integer price;
    private String description;
    private String location;
    private Boolean published;
    private Integer genreId;
}
