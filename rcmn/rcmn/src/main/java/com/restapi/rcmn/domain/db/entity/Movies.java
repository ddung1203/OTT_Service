package com.restapi.rcmn.domain.db.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Movies {
    @Id
    @GeneratedValue
    private Long movieIdx;

    @Column(name = "movie_name")
    private String movieName;

    @Column(name = "overview")
    private String overview;
}
