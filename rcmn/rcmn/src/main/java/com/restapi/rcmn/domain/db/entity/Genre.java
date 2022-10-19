package com.restapi.rcmn.domain.db.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Builder
@Table(name = "genre")
public class Genre {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_idx")
    private Long genreIdx;

    @Column(name = "genre")
    private String genre;
}
