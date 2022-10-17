package com.restapi.rcmn.domain.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Genre {
    @Id @GeneratedValue
    private Long genreIdx;

    @Column(name = "genre")
    private String genre;
}
