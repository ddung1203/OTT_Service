package com.restapi.rcmn.domain.db.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Builder
@Table(name = "movies_genre")
public class MoviesGenre {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_genre")
    private Long moviesGenre;

    @ManyToOne(targetEntity = Movies.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "movies_idx")
    private Movies movies;

    @ManyToOne(targetEntity = Genre.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_idx")
    private Genre genre;
}
