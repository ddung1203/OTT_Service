package com.restapi.rcmn.domain.db.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@Table(name = "movies_member")
public class MoviesMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movies_member_idx")
    private Long moviesMemberIdx;

    @ManyToOne
    @JoinColumn(name = "movies_idx")
    private Movies movies;

    @Column
    private Long memberId;

    @Column
    @CreatedDate
    private LocalDateTime regDt;

    @Column
    @LastModifiedDate
    private LocalDateTime updateDt;

}
