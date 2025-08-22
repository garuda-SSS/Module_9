package ru.cinescope.db.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "movies")
public class Movies {
    @Id
    @Column
    private Integer id;
    @Column
    private String name;
    @Column
    private Integer price;
    @Column
    private String description;
    @Column(name = "image_url")
    private String imageUrl;
    @Column
    private String location;
    @Column
    private Boolean published;
    @Column
    private Float rating;
    @Column(name = "genre_id")
    private Integer genreId;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
