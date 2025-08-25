package ru.cinescope.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponse {
    private Integer id;
    private String name;
    private Integer price;
    private String description;
    private String imageUrl;
    private String location;
    private Boolean published;
    private Float rating;
    private Integer genreId;
    private Instant createdAt;
    private List<Reviews> reviews;
    private Genre genre;
}
