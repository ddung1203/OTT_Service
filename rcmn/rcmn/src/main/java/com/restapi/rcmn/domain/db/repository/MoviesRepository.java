package com.restapi.rcmn.domain.db.repository;

import com.restapi.rcmn.domain.db.entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviesRepository extends JpaRepository<Movies, Long> {
}
