package com.restapi.member.domain.db.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Builder
@Table(name = "member")
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberIdx;

    @Column
    private String authId;

    @Column
    private String password;

}
