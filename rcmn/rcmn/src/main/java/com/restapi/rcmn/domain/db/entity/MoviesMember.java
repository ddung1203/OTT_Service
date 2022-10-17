package com.restapi.rcmn.domain.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MoviesMember {
    @Id
    @GeneratedValue
    private Long moviesMemberIdx;

    @Column(name = "movies_idx")
    private Long moviesIdx;

    @Column(name = "member_idx")
    private Long memberIdx;

    @Column(name = "preference")
    private Byte preference;
}
