package com.restapi.rcmn.domain.db.repository;

import com.restapi.rcmn.domain.db.entity.MoviesMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MoviesMemberRepository extends JpaRepository<MoviesMember, Long> {
    @Query(value = "select mm from MoviesMember mm join fetch mm.movies where mm.memberId = :memberId")
    List<MoviesMember> findByMemberId(@Param("memberId") long memberId);
}
