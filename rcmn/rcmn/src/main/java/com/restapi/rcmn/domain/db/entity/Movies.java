package com.restapi.rcmn.domain.db.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Builder
@Table(name = "movies")
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movies_idx")
    private Long movieIdx;

    @Column(name = "movies_title")
    private String moviesTitle;

    @Column(name = "overview", length = 1024)
    private String overview;
}
