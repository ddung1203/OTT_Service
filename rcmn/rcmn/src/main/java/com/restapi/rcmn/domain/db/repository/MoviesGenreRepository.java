package com.restapi.rcmn.domain.db.repository;

import com.restapi.rcmn.domain.db.entity.MoviesGenre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviesGenreRepository extends JpaRepository<MoviesGenre, Long> {
}
