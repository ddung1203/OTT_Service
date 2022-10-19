package com.restapi.rcmn.domain.db.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Builder
@Table(name = "movies_tag")
public class MoviesTag {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_tag_idx")
    private Long moviesTagIdx;

    @JoinColumn(name = "tag_idx")
    @ManyToOne(targetEntity = Tag.class, fetch = FetchType.LAZY)
    private Tag tag;

    @JoinColumn(name = "movies_idx")
    @ManyToOne(targetEntity = Movies.class, fetch = FetchType.LAZY)
    private Movies movies;
}
