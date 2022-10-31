package com.restapi.rcmn.domain.db.repository;

import com.restapi.rcmn.domain.db.entity.Genre;
import com.restapi.rcmn.domain.db.entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
