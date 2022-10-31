package com.restapi.rcmn.domain.db.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "s3url")
    private String s3url;
}

