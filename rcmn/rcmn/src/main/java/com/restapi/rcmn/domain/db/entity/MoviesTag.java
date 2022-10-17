package com.restapi.rcmn.domain.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MoviesTag {
    @Id @GeneratedValue
    private Long moviesTagIdx;

    @Column(name = "tag_idx")
    private Long tagIdx;

    @Column(name = "movies_tag")
    private Long moviesTag;
}
