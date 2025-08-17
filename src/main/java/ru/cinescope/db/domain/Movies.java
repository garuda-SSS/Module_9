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
    private int id;
    @Column
    private String name;
    @Column
    private int price;
    @Column
    private String description;
    @Column
    private String image_url;
    @Column
    private String location;
    @Column
    private boolean published;
    @Column
    private float rating;
    @Column
    private int genre_id;
    @Column
    private LocalDateTime created_at;
}
