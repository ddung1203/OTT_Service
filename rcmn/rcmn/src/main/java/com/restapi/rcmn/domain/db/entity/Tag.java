package com.restapi.rcmn.domain.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tag {
    @Id @GeneratedValue
    private Long tagIdx;

    @Column(name = "tag_name")
    private String tagName;
}
