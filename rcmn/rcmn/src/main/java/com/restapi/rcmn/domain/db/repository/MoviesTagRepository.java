package com.restapi.rcmn.domain.db.repository;

import com.restapi.rcmn.domain.db.entity.MoviesTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviesTagRepository extends JpaRepository<MoviesTag, Long> {
}
